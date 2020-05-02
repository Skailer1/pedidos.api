package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.model.DetallePedido;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

public interface IDetalleService
{
    void crear(DetallePedido detalle) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

    void actualizar(Long id, DetallePedido detalle) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

    boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;

    Iterable<DetallePedido> listarTodos() throws NoExisteEntidadExcepcion;

    DetallePedido buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;


}
