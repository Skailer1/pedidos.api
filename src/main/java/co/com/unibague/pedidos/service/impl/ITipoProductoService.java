package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.model.TipoEmpleado;
import co.com.unibague.pedidos.model.TipoProducto;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

import java.util.List;

public interface ITipoProductoService
{
   // TipoProducto crear(TipoProducto TipoProducto) throws  DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

    //TipoProducto actualizar(Long id, TipoProducto TpProducto) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

    //boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;

    List<TipoProducto> listarTodos()throws NoExisteEntidadExcepcion;

    TipoProducto buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;
}
