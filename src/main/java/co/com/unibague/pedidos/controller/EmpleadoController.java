package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.dto.EmpleadoDTO;
import co.com.unibague.pedidos.dto.GuardarEmpleadoDTO;
import co.com.unibague.pedidos.dto.RespuestaBaseDTO;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IEmpleadoService;
import co.com.unibague.pedidos.util.response.BaseResponse;
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
    public ResponseEntity<?> crear(@RequestBody GuardarEmpleadoDTO empleado) {
        try {
            return new ResponseEntity<>(empleadoService.crear(empleado), HttpStatus.CREATED);
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

    @PutMapping(value = "empleado/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody EmpleadoDTO empleado) {
        try {
            return new ResponseEntity<>(empleadoService.actualizar(id, empleado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
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


}
