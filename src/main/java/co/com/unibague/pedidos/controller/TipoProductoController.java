package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.util.response.BaseResponse;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
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


    @GetMapping(value = "tipoProducto", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        try {
            return new ResponseEntity<>(tipoProductoService.listarTodos(), HttpStatus.OK);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }


}
