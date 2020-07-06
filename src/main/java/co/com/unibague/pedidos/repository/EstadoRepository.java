package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.enums.DescripcionEstado;
import co.com.unibague.pedidos.model.EstadoPedido;

import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

public interface EstadoRepository extends CrudRepository<EstadoPedido, Long> {

    Optional<EstadoPedido> findById(Long id);



//    Optional<EstadoPedido> findByDescripcion(DescripcionEstado descripcion);
}
