package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.Mesa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MesaRepository extends CrudRepository<Mesa, Long>
{
    Optional<Mesa> findById (Long id);

    List<Mesa> findAll();
}
