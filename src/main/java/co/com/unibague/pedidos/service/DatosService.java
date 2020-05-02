package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.model.Datos;
import co.com.unibague.pedidos.repository.DatosRepository;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IDatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("datosService")
public class DatosService implements IDatosService

{
    @Autowired
    private DatosRepository datosRepository;

    @Override
    public List<Datos> listarTodos() throws NoExisteEntidadExcepcion {
        List<Datos> datosP = datosRepository.findByActivo(true);
        if (datosP.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay tipoDocumentos registrados");
        } else {
            return datosP;
        }
    }

    @Override
    public Datos buscarPorId(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<Datos> datoPorId = datosRepository.findById(id);
        if (!datoPorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un tipoDocumento con ese id");
        }
        Datos datosPBuscado = datoPorId.get();
        if (!datosPBuscado.isActivo()) {
            throw new EntidadInactivaExcepcion("El tipoDocumento se encuentra inactivo");
        } else {
            return datosPBuscado;
        }
    }
}
