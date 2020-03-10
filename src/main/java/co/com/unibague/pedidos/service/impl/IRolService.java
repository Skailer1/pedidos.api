package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.model.Rol;

public interface IRolService
{
    void crear(Rol rol) throws Exception;

    void actualizar(Integer id, Rol rol) throws Exception;

    boolean eliminar(Integer id) throws Exception;

    boolean eliminarTodos();

    Iterable<Rol> listarTodos();

    Rol buscarPorId(Integer id) throws Exception;
}
