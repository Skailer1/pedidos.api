package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.TipoProducto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TipoProductoRepository extends CrudRepository<TipoProducto, Long>
{
    Optional<TipoProducto> findById (Long id);

    List<TipoProducto> findAll();
}
