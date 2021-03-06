package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.TipoDocumento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TipoDocumentoRepository extends CrudRepository<TipoDocumento, Long> {

    Optional<TipoDocumento> findById(Long id);

   // List<TipoDocumento> findByName(String name);


    List<TipoDocumento> findAll();
}
