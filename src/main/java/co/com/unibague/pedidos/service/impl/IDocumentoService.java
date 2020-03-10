package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.model.Documento;

public interface IDocumentoService
{
    void crear(Documento documento) throws Exception;

    void actualizar(Integer id, Documento documento) throws Exception;

    boolean eliminar(Integer id) throws Exception;

    boolean eliminarTodos();

    Iterable<Documento> listarTodos();

    Documento buscarPorId(Integer id) throws Exception;

}
