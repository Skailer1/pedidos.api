package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.dto.GuardarDetalleDTO;
import co.com.unibague.pedidos.model.DetallePedido;
import co.com.unibague.pedidos.model.DetallePedidoPK;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

public interface IDetalleService
{
 //   DetallePedido crear(GuardarDetalleDTO detallePedido) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

  //  DetallePedido actualizar(DetallePedidoPK detallePedidoPK, DetallePedido detalle) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

    boolean eliminar(DetallePedidoPK detallePedidoPK) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;

    DetallePedido buscarPorId(DetallePedidoPK detallePedidoPK) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;


}
