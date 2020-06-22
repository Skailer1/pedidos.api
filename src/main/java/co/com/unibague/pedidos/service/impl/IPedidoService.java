package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.dto.GuardarPedidoDTO;
import co.com.unibague.pedidos.model.Pedido;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

import java.util.List;

public interface IPedidoService
{
    Pedido crear(GuardarPedidoDTO guardarPedido) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion,  DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

   // Pedido actualizar(Long id, Pedido pedido) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

    List<Pedido> listarTodos() throws NoExisteEntidadExcepcion;


    boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;

    Pedido buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;
}
