package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.service.impl.IEmpleadoService;
import co.com.unibague.pedidos.model.Empleado;
import co.com.unibague.pedidos.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")


public class EmpleadoController
{
    @Autowired
    private IEmpleadoService empleadoService;

    @PostMapping(value = "empleado", headers = "Accept=application/json")
    public ResponseEntity<?> crear(@RequestBody Empleado empleado) {
        try {
            empleadoService.crear(empleado);
            return new ResponseEntity<>(empleado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "empleado/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        try {
            empleadoService.actualizar(id, empleado);
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "empleado", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        return new ResponseEntity<>(empleadoService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping(value = "empleado/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(empleadoService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "empleado/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(empleadoService.eliminar(id))
                    .mensaje("Empleado eliminado")
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "empleado", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarTodos() {
        try {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(empleadoService.eliminarTodos())
                    .mensaje("Empleados eliminados")
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

}
