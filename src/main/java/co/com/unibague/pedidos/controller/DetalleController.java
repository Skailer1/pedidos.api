package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.model.DetallePedido;
import co.com.unibague.pedidos.response.BaseResponse;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class DetalleController
{

    @Autowired
    private IDetalleService detalleService;

    @PostMapping(value = "detallePedido", headers = "Accept=application/json")
    public ResponseEntity<?> crear(@RequestBody DetallePedido detalle) {
        try {
            detalleService.crear(detalle);
            return  new ResponseEntity<>(detalle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "detallePedido/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> actualizar(@PathVariable long id, @RequestBody DetallePedido detalle) {
        try {
            detalleService.actualizar(id, detalle);
            return new ResponseEntity<>(detalle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "detallePedido", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        try {
            return new ResponseEntity<>(detalleService.listarTodos(), HttpStatus.OK);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            noExisteEntidadExcepcion.printStackTrace();
        }
    }

    @GetMapping(value = "detallePedido/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(detalleService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "detallePedido/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(detalleService.eliminar(id))
                    .mensaje("Detalle eliminado")
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "detallePedido", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarTodos() {
        try {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(detalleService.eliminarTodos())
                    .mensaje("Detalles eliminados")
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "detallePedido/total", headers = "Accept=application/json")
    public ResponseEntity<?> obetenerTotal(@RequestParam("id") Long id) {
        return new ResponseEntity<>(BaseResponse.builder()
                .mensaje(detalleService.obtenerTotal(id).toString())
                .isCorrecto(true)
                .build(), HttpStatus.OK);
    }

}
