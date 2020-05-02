package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.service.impl.IMesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class MesaController
{
    @Autowired
    private IMesaService mesaService;

    @GetMapping(value = "mesa", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        return new ResponseEntity<>(mesaService.listarTodos(), HttpStatus.OK);
    }
}
