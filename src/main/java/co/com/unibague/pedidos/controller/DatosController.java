package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.dto.RespuestaBaseDTO;
import co.com.unibague.pedidos.util.response.BaseResponse;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IDatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class DatosController
{

    @Autowired
    private IDatosService datosService;


    @GetMapping(value = "datos", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        try {
            return new ResponseEntity<>(datosService.listarTodos(), HttpStatus.OK);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.NOT_FOUND.value())
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value = "datos/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(datosService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

}
