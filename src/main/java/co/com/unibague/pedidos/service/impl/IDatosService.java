package co.com.unibague.pedidos.service.impl;


import co.com.unibague.pedidos.model.Datos;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

public interface IDatosService
{


    Iterable<Datos> listarTodos() throws NoExisteEntidadExcepcion;

    Datos buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;
}
