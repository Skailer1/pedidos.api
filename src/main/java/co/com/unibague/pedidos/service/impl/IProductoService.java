package co.com.unibague.pedidos.service.impl;


import co.com.unibague.pedidos.dto.GuardarProductoDTO;
import co.com.unibague.pedidos.model.Producto;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

import java.util.List;

public interface IProductoService
{
    //Producto crear(GuardarProductoDTO guardarProducto) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

    //Producto actualizar(Long id, Producto producto) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

    //boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;  por que los otros si funcionan con el iterable amigo ?

    List<Producto> listarTodos()throws NoExisteEntidadExcepcion;

    Producto buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;
}
