package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.dto.EstadoDTO;
import co.com.unibague.pedidos.model.EstadoPedido;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

import java.util.List;

public interface IEstadoService

{
    //EstadoPedido crear(EstadoPedido estado) throws   DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

    EstadoPedido actualizar(Long id, EstadoDTO estado) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

  //  boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;

    List<EstadoPedido> listarTodos() throws NoExisteEntidadExcepcion;


    EstadoPedido buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;
}
