package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.model.TipoProducto;
import co.com.unibague.pedidos.response.BaseResponse;
import co.com.unibague.pedidos.service.impl.ITipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class TipoProductoController
{
    @Autowired
    private ITipoProductoService tipoProductoService;

    @PostMapping(value = "tipoProducto", headers = "Accept=application/json")
    public ResponseEntity<?> crear(@RequestBody TipoProducto tipoProducto) {
        try {
            tipoProductoService.crear(tipoProducto);
            return  new ResponseEntity<>(tipoProducto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "tipoProducto/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> actualizar(@PathVariable long id, @RequestBody TipoProducto tipoEmpleado) {
        try {
            tipoProductoService.actualizar(id, tipoEmpleado);
            return new ResponseEntity<>(tipoEmpleado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "tipoProducto", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        return new ResponseEntity<>(tipoProductoService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping(value = "tipoProducto/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(tipoProductoService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "tipoProducto/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(tipoProductoService.eliminar(id))
                    .mensaje("Tipo Empleado Eliminado")
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }


}
