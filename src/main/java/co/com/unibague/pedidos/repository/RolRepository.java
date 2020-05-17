package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RolRepository extends CrudRepository<Rol, Long>
{
    Optional<Rol> findById (Long id);
}
