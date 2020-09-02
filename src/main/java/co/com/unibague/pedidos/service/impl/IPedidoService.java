package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.dto.GuardarPedidoDTO;
import co.com.unibague.pedidos.model.Pedido;
import co.com.unibague.pedidos.model.Producto;
import co.com.unibague.pedidos.service.exception.*;

import java.util.List;

public interface IPedidoService
{
    Pedido crear(GuardarPedidoDTO guardarPedido) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion,  DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

   // Pedido actualizar(Long id, Pedido pedido) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

    List<Pedido> listarTodos() throws NoExisteEntidadExcepcion;

   // List<Pedido> listarTodosDetalles() throws NoExisteEntidadExcepcion;

    String listarPorPedido(Long pedidoIdP) throws NoExisteEntidadExcepcion;

    // Map<Product, Integer> getProductsInCart();

    //  Double totalPedido(Date fecha);

    boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;

    double darTotal();

    void disponibilidadProducto() throws NoExistenUnidadesDisponiblesExcepcion;

    void añadirProductos(Producto producto);

    void eliminarProducto(Producto producto);

    Pedido buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;
}
