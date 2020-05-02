package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.model.Usuario;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

public interface IUsuarioService
{
    void crear(Usuario usuario) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

    void actualizar(Long id, Usuario usuario) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

    boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;

    Iterable<Usuario> listarTodos() throws NoExisteEntidadExcepcion;;

    Usuario buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;
}
