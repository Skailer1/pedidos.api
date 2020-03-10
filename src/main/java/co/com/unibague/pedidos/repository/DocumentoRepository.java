package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.Documento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IDocumentoRepository extends CrudRepository<Documento, Integer>
{
    Optional<Documento> findById (Integer id);
    
}
