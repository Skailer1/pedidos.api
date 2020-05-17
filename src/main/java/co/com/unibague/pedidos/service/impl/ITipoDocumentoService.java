package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.model.TipoDocumento;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

import java.util.List;

public interface ITipoDocumentoService {

    TipoDocumento crear(TipoDocumento tipoDocumento) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

    TipoDocumento actualizar(Long id, TipoDocumento tipoDocumento) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

    boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;

    List<TipoDocumento> listarTodos() throws NoExisteEntidadExcepcion;

    TipoDocumento buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;

}
