package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.dto.GuardarPedidoDTO;
import co.com.unibague.pedidos.dto.RespuestaBaseDTO;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IPedidoService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class PedidoController {
    @Autowired
    private IPedidoService pedidoService;



    @PostMapping(value = "pedido", headers = "Accept=application/json")
    public ResponseEntity<?> crear(@RequestBody GuardarPedidoDTO pedido) {
        try {
            return new ResponseEntity<>(pedidoService.crear(pedido), HttpStatus.CREATED);
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


    @PostMapping(value = "pedido/detalle/{pedidoIdP}", headers = "Accept=application/json")
    public ResponseEntity<?> listarPorPedido(@PathVariable Long pedidoIdP) {
        try {
            return new ResponseEntity<>(pedidoService.listarPorPedido(pedidoIdP), HttpStatus.OK);

        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.NOT_FOUND.value())
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }



    /* @PutMapping(value = "pedido/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> actualizar(@PathVariable long id, @RequestBody PedidoDTO pedido) {
        try {
            return new ResponseEntity<>(pedidoService.actualizar(id, pedido.covertirPedido()), HttpStatus.OK);
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

    */
   @GetMapping(value = "pedido", headers = "Accept=application/json")
   public ResponseEntity<?> listarTodos() {
       try {
           return new ResponseEntity<>(pedidoService.listarTodos(), HttpStatus.OK);
       } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
           return new ResponseEntity<>(RespuestaBaseDTO.builder()
                   .codigoEstado(HttpStatus.NOT_FOUND.value())
                   .mensaje(noExisteEntidadExcepcion.getMessage())
                   .isCorrecto(false)
                   .build(), HttpStatus.NOT_FOUND);
       }
   }

  /*  @GetMapping(value = "pedido/detalles", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodosDetalles() {
        try {
            return new ResponseEntity<>(pedidoService.listarTodosDetalles(), HttpStatus.OK);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.NOT_FOUND.value())
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }
*/

    @GetMapping(value = "pedido/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(pedidoService.buscarPorId(id), HttpStatus.OK);
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

    @DeleteMapping(value = "pedido/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            boolean pedidoEliminado = pedidoService.eliminar(id);
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


}
