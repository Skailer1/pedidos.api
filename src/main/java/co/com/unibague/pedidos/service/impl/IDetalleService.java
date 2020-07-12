package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.dto.GuardarDetalleDTO;
import co.com.unibague.pedidos.model.DetallePedido;
import co.com.unibague.pedidos.model.DetallePedidoPK;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

import java.util.List;

public interface IDetalleService
{
    DetallePedido crear(GuardarDetalleDTO detallePedido) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

  //  DetallePedido actualizar(DetallePedidoPK detallePedidoPK, DetallePedido detalle) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

   boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;

    List<DetallePedido> listarTodos() throws NoExisteEntidadExcepcion;


    DetallePedido buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;


}
