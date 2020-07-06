package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.dto.GuardarDetalleDTO;
import co.com.unibague.pedidos.dto.RespuestaBaseDTO;
import co.com.unibague.pedidos.model.DetallePedidoPK;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class DetalleController
{
    @Autowired
    private IDetalleService detalleService;


    @PostMapping(value = "detalle", headers = "Accept=application/json")
    public ResponseEntity<?> crear(@RequestBody GuardarDetalleDTO detalle) {
        try {
            return new ResponseEntity<>(detalleService.crear(detalle), HttpStatus.CREATED);
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




 /*   @GetMapping(value = "detalle/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable DetallePedidoPK id) {
        try {
            return new ResponseEntity<>(detalleService.buscarPorId(id), HttpStatus.OK);
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

    @DeleteMapping(value = "detalle/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarPorId(@PathVariable DetallePedidoPK detallePedidoPK) {
        try {
            boolean pedidoEliminado = detalleService.eliminar(detallePedidoPK);
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .mensaje("pedido eliminado")
                    .codigoEstado(HttpStatus.OK.value())
                    .isCorrecto(pedidoEliminado)
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

  */
}
