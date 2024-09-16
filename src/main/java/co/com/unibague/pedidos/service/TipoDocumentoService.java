package co.com.unibague.pedidos.service;


import co.com.unibague.pedidos.model.TipoDocumento;
import co.com.unibague.pedidos.repository.TipoDocumentoRepository;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.ITipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("tipodocumentoService")
public class TipoDocumentoService implements ITipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

  /*  @Override
    public TipoDocumento crear(TipoDocumento tipoDocumento) throws YaExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!tipoDocumento.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        /*} else if (tipoDocumentoRepository.findByNumeroDocumentoAndTipoDocumento(tipoDocumento.getId(), tipoDocumento.getDescripcionDocumento()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un tipoDocumento con ese numero de tipoDocumento y tipo de tipoDocumento"); */
   /*     } else if (tipoDocumentoRepository.findById(tipoDocumento.getId()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un tipoDocumento con ese id");
        } else {
            tipoDocumento.setFechaCreacion(new Date());
            tipoDocumento.setFechaActualizacion(new Date());
            tipoDocumento.setActivo(true);
            return tipoDocumentoRepository.save(tipoDocumento);
        }
    }

    @Override
    public TipoDocumento actualizar(Long id, TipoDocumento tipoDocumento) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!tipoDocumento.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else {
            TipoDocumento tipoDocumentoBuscado = buscarPorId(id);
            tipoDocumentoBuscado.setDescripcionDocumento(tipoDocumento.getDescripcionDocumento());
            tipoDocumentoBuscado.setFechaActualizacion(new Date());
            return tipoDocumentoRepository.save(tipoDocumentoBuscado);
        }
    }

    @Override
    public boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        boolean resultado = false;
        TipoDocumento tipoDocumentoPorId = buscarPorId(id);
        if (tipoDocumentoPorId != null) {
            tipoDocumentoPorId.setActivo(false);
            tipoDocumentoPorId.setFechaActualizacion(new Date());
            tipoDocumentoRepository.save(tipoDocumentoPorId);
            resultado = true;
        }
        return resultado;
    }
*/
    @Override
    public List<TipoDocumento> listarTodos() throws NoExisteEntidadExcepcion {
        List<TipoDocumento> tipoDocumentos = tipoDocumentoRepository.findAll();
        if (tipoDocumentos.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay tipo documentos registrados");
        } else {
            return tipoDocumentos;
        }
    }

    @Override
    public TipoDocumento buscarPorId(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<TipoDocumento> documentoPorId = tipoDocumentoRepository.findById(id);
        if (!documentoPorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un tipoDocumento con ese id");
        }
        TipoDocumento tipoDocumentoBuscado = documentoPorId.get();
        if (!tipoDocumentoBuscado.isActivo()) {
            throw new EntidadInactivaExcepcion("El tipoDocumento se encuentra inactivo");
        } else {
            return tipoDocumentoBuscado;
        }
    }



}
