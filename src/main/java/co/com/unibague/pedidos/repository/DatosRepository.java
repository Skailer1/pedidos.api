package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.Datos;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DatosRepository extends CrudRepository<Datos, Long>
{
    Optional<Datos> findById (Long id);
    List<Datos> findByActivo(boolean isActivo);


}
