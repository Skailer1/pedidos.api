package co.com.unibague.pedidos.repository;

import co.com.unibague.pedidos.model.Empleado;
import co.com.unibague.pedidos.response.BaseResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long>
{
    Optional<Empleado> findById (Long id);

    Optional<Empleado> findByNumeroDocumentoAndTipoDocumento_Id(long numeroDocumento, long tipoDocumento);

}
