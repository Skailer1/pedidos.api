package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.response.BaseResponse;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.ITipoEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class    TipoEmpleadoController {
    @Autowired
    private ITipoEmpleadoService tipoEmpleadoService;

    @GetMapping(value = "tipoEmpleado", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        try {
            return new ResponseEntity<>(tipoEmpleadoService.listarTodos(), HttpStatus.OK);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

}
