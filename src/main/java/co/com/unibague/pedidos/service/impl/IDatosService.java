package co.com.unibague.pedidos.service.impl;


import co.com.unibague.pedidos.model.Datos;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

import java.util.List;

public interface IDatosService
{
   List<Datos> listarTodos() throws NoExisteEntidadExcepcion;


   Datos buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;
}
