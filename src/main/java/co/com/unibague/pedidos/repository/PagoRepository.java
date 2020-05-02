package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.Pago;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PagoRepository extends CrudRepository<Pago, Long>
{
    Optional<Pago> findById (Long id);

    List<Pago> findByActivo(boolean isActivo);

}
