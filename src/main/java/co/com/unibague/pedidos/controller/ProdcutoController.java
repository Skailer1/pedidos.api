package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.model.Producto;
import co.com.unibague.pedidos.response.BaseResponse;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IPedidoService;
import co.com.unibague.pedidos.service.impl.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class ProdcutoController
{
    @Autowired
    private IProductoService productoService;

    @PostMapping(value = "producto", headers = "Accept=application/json")
    public ResponseEntity<?> crear(@RequestBody Producto producto) {
        try {
            productoService.crear(producto);
            return  new ResponseEntity<>(producto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "producto/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> actualizar(@PathVariable long id, @RequestBody Producto producto) {
        try {
            productoService.actualizar(id, producto);
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "producto", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        try {
            return new ResponseEntity<>(productoService.listarTodos(), HttpStatus.OK);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "producto/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(productoService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "rol/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(productoService.eliminar(id))
                    .mensaje("Rol eliminado")
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "producto", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarTodos() {
        try {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(productoService.eliminarTodos())
                    .mensaje("Productos eliminados")
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

}
