package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.dto.TipoDocumentoDTO;
import co.com.unibague.pedidos.dto.RespuestaBaseDTO;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.ITipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1")
public class DocumentoController {

    @Autowired
    private ITipoDocumentoService documentoService;


    @PostMapping(value = "documento", headers = "Accept=application/json")
    public ResponseEntity<?> crear(@RequestBody TipoDocumentoDTO documento) {
        try {
            return new ResponseEntity<>(documentoService.crear(documento.covertirTipoDocumento()), HttpStatus.CREATED);
        } catch (YaExisteEntidadExcepcion | DataIncorrectaExcepcion | EntidadInactivaExcepcion exception) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(exception.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.CONFLICT);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.NOT_FOUND.value())
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "documento/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> actualizar(@PathVariable long id, @RequestBody TipoDocumentoDTO documento) {
        try {
            return new ResponseEntity<>(documentoService.actualizar(id, documento.covertirTipoDocumento()), HttpStatus.OK);
        } catch (EntidadInactivaExcepcion entidadInactivaExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(entidadInactivaExcepcion.getMessage())
                    .build(), HttpStatus.CONFLICT);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.NOT_FOUND.value())
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.NOT_FOUND);
        } catch (DataIncorrectaExcepcion dataIncorrectaExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(dataIncorrectaExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "documento", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        try {
            return new ResponseEntity<>(documentoService.listarTodos(), HttpStatus.OK);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.NOT_FOUND.value())
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "documento/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(documentoService.buscarPorId(id), HttpStatus.OK);
        } catch (EntidadInactivaExcepcion entidadInactivaExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(entidadInactivaExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.CONFLICT);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.NOT_FOUND.value())
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "documento/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            boolean documentoEliminado = documentoService.eliminar(id);
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .mensaje("TipoDocumento eliminado")
                    .codigoEstado(HttpStatus.OK.value())
                    .isCorrecto(documentoEliminado)
                    .build(), HttpStatus.OK);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.NOT_FOUND.value())
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.NOT_FOUND);
        } catch (EntidadInactivaExcepcion entidadInactivaExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(entidadInactivaExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.CONFLICT);
        }
    }

}
