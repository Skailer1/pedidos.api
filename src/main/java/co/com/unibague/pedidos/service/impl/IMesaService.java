package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.model.Mesa;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;

public interface IMesaService
{
    Iterable<Mesa> listarTodos() throws NoExisteEntidadExcepcion;
}
