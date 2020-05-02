package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.model.EstadoPedido;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

public interface IEstadoService

{
    void crear(EstadoPedido estado) throws   DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

    void actualizar(Long id, EstadoPedido estado) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

  //  boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;

    Iterable<EstadoPedido> listarTodos()throws NoExisteEntidadExcepcion;

    EstadoPedido buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;
}
