package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.DetallePedido;
import co.com.unibague.pedidos.model.DetallePedidoPK;
import co.com.unibague.pedidos.model.Pedido;
import co.com.unibague.pedidos.model.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DetalleRepository extends CrudRepository<DetallePedido, Long> {

    Optional<DetallePedido> findByFechaCreacion(Date fechaCreacion);

    Optional<DetallePedido> findByProductoAndPedido(Producto producto, Pedido pedido);

// hmm es que aqui es una llave compuesta no se como hacer el findBy



}
