package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.DetallePedido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DetalleRepository extends CrudRepository<DetallePedido, Long>
{
        Optional<DetallePedido> findById (Long id);

        List<DetallePedido> findByActivo(boolean isActivo);


}
