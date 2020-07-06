package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.dto.RespuestaBaseDTO;
import co.com.unibague.pedidos.dto.EstadoDTO;
import co.com.unibague.pedidos.model.EstadoPedido;
import co.com.unibague.pedidos.response.BaseResponse;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("v1")
public class EstadoController
{
    @Autowired
    private IEstadoService estadoService;

    @PostMapping(value = "estado", headers = "Accept=application/json")
    public ResponseEntity<?> crear(@RequestBody EstadoDTO estado) {
        try {
            return new ResponseEntity<>(estadoService.crear(estado.covertirEstado()), HttpStatus.CREATED);
        } catch (/*YaExisteEntidadExcepcion |*/ DataIncorrectaExcepcion exception ) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(exception.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.CONFLICT);

        }
    }

    @PutMapping(value = "estado/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody EstadoDTO estado) {
        try {
            return new ResponseEntity<>(estadoService.actualizar(id, estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }






    @GetMapping(value = "estado/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(estadoService.buscarPorId(id), HttpStatus.OK);
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

    @GetMapping(value = "estado", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        try {
            return new ResponseEntity<>(estadoService.listarTodos(), HttpStatus.OK);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.NOT_FOUND.value())
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }
}
