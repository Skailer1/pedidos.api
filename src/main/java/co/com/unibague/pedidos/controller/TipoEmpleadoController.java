package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.model.TipoEmpleado;
import co.com.unibague.pedidos.response.BaseResponse;
import co.com.unibague.pedidos.service.impl.ITipoEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class TipoEmpleadoController
{
    @Autowired
    private ITipoEmpleadoService tipoEmpleadoService;

    @PostMapping(value = "tipoEmpleado", headers = "Accept=application/json")
    public ResponseEntity<?> crear(@RequestBody TipoEmpleado tipoEmpleado) {
        try {
            tipoEmpleadoService.crear(tipoEmpleado);
            return  new ResponseEntity<>(tipoEmpleado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "tipoEmpleado/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> actualizar(@PathVariable long id, @RequestBody TipoEmpleado tipoEmpleado) {
        try {
            tipoEmpleadoService.actualizar(id, tipoEmpleado);
            return new ResponseEntity<>(tipoEmpleado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "tipoEmpleado", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        return new ResponseEntity<>(tipoEmpleadoService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping(value = "tipoEmpleado/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(tipoEmpleadoService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "tipoEmpleado/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(tipoEmpleadoService.eliminar(id))
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
