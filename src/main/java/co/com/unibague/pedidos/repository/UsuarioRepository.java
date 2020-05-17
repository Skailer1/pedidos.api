package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>
{
    Optional<Usuario> findById (Long id);
}
