package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.enums.TipoImpuesto;
import co.com.unibague.pedidos.model.TipoEmpleado;

public interface ITipoEmpleado
{
    void crear(TipoEmpleado TpEmpleado) throws Exception;

    void actualizar(Long id, TipoEmpleado TpEmpleado) throws Exception;

    boolean eliminar(Long id) throws Exception;

    Iterable<TipoEmpleado> listarTodos();

    TipoEmpleado buscarPorId(Long id) throws Exception;
}
