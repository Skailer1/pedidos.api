package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.Pedido;
import co.com.unibague.pedidos.model.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends CrudRepository<Producto, Long>
{
    Optional<Producto> findById (Long id);

 //   List<Producto> findAllById(Long id);

    List<Producto> findAll();
}
