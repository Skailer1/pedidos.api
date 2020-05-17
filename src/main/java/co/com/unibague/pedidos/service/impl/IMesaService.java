package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.model.Mesa;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;

import java.util.List;

public interface IMesaService
{
    List<Mesa> listarTodos() throws NoExisteEntidadExcepcion;

    Mesa buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;
}
