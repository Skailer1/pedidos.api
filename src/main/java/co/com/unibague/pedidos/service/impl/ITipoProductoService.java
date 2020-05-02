package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.model.TipoEmpleado;
import co.com.unibague.pedidos.model.TipoProducto;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

public interface ITipoProductoService
{
    void crear(TipoProducto TipoProducto) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

    void actualizar(Long id, TipoProducto TpProducto) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

    boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;

    Iterable<TipoProducto> listarTodos()throws NoExisteEntidadExcepcion;

    TipoProducto buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;
}
