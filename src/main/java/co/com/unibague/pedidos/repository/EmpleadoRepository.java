package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.Empleado;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long>
{
    Optional<Empleado> findById (Long id);

    Optional<Empleado> findByNumeroDocumento(long numeroDocumento);

}
