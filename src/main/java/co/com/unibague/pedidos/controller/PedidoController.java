package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.model.Pedido;
import co.com.unibague.pedidos.response.BaseResponse;
import co.com.unibague.pedidos.service.impl.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class PedidoController
{
    @Autowired
    private IPedidoService pedidoService;

    @PostMapping(value = "pedido", headers = "Accept=application/json")
    public ResponseEntity<?> crear(@RequestBody Pedido pedido) {
        try {
            pedidoService.crear(pedido);
            return  new ResponseEntity<>(pedido, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "pedido/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> actualizar(@PathVariable long id, @RequestBody Pedido pedido) {
        try {
            pedidoService.actualizar(id, pedido);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "pedido", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        return new ResponseEntity<>(pedidoService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping(value = "pedido/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(pedidoService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "pedido/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(pedidoService.eliminar(id))
                    .mensaje("Pedido eliminado")
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }



}
