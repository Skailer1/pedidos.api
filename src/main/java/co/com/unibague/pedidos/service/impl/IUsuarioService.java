package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.model.Usuario;

public interface IUsuarioService
{
    void crear(Usuario usuario) throws Exception;

    void actualizar(Integer id, Usuario usuario) throws Exception;

    boolean eliminar(Integer id) throws Exception;

    boolean eliminarTodos();

    Iterable<Usuario> listarTodos();

    Usuario buscarPorId(Integer id) throws Exception;
}
