package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.DetallePedido;
import co.com.unibague.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends CrudRepository<Pedido, Long>
{
    Optional<Pedido> findById (Long id);

    Optional<Pedido> findByFechaPedido(Date fecha);

  /*  @Query("SELECT u from DetallePedido d INNER JOIN d.pedido u WHERE u.isActivo = true ")
    List<Pedido> findAllDetallesPedido();*/

    @Query("SELECT u FROM DetallePedido u WHERE u.pedido.id = :pedidoIdP")
    List<DetallePedido> findAllByPedido(Long pedidoIdP);

    @Query("SELECT u FROM Pedido u WHERE u.isActivo = true")
    List<Pedido> findAllByActivo();

   // List<Pedido> findByActivoIsTrue(boolean isActivo );
}





























