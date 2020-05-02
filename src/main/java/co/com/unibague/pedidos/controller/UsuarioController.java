package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.model.Usuario;
import co.com.unibague.pedidos.response.BaseResponse;
import co.com.unibague.pedidos.service.impl.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class UsuarioController
{

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping(value = "usuario", headers = "Accept=application/json")
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
        try {
            usuarioService.crear(usuario);
            return  new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "usuario/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> actualizar(@PathVariable long id, @RequestBody Usuario usuario) {
        try {
            usuarioService.actualizar(id, usuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "usuario", headers = "Accept=application/json")
    public ResponseEntity<?> listarTodos() {
        return new ResponseEntity<>(usuarioService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping(value = "usuario/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(usuarioService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "usuario/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(usuarioService.eliminar(id))
                    .mensaje("Usuario eliminado")
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "usuario", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarTodos() {
        try {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(usuarioService.eliminarTodos())
                    .mensaje("Usuarios eliminados")
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.builder()
                    .isCorrecto(false)
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }


}
