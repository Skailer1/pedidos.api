package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.TipoEmpleado;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TipoEmpleadoRepository extends CrudRepository<TipoEmpleado, Long>
{
    Optional<TipoEmpleado> findById (Long id);
    List<TipoEmpleado> findAll();
}


