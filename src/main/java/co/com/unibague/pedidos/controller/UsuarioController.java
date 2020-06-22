package co.com.unibague.pedidos.controller;

import co.com.unibague.pedidos.config.JwtTokenUtil;
import co.com.unibague.pedidos.config.JwtUserDetailsService;
import co.com.unibague.pedidos.config.TokenResponse;
import co.com.unibague.pedidos.config.UserRequest;
import co.com.unibague.pedidos.dto.CambiarContraseniaDTO;
import co.com.unibague.pedidos.dto.RespuestaBaseDTO;
import co.com.unibague.pedidos.dto.UsuarioDTO;
import co.com.unibague.pedidos.model.Usuario;
import co.com.unibague.pedidos.response.BaseResponse;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(value = "usuario", headers = "Accept=application/json")
    public ResponseEntity<?> crear(@RequestBody UsuarioDTO usuario) {
        try {
            return new ResponseEntity<>(usuarioService.crear(usuario.covertirUsuario()), HttpStatus.CREATED);
        } catch (YaExisteEntidadExcepcion | DataIncorrectaExcepcion exception) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(exception.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "usuario/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> actualizar(@PathVariable long id, @RequestBody UsuarioDTO usuario) {
        try {
            return new ResponseEntity<>(usuarioService.actualizar(id, usuario.covertirUsuario()), HttpStatus.OK);
        } catch (EntidadInactivaExcepcion entidadInactivaExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(entidadInactivaExcepcion.getMessage())
                    .build(), HttpStatus.CONFLICT);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.NOT_FOUND.value())
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.NOT_FOUND);
        } catch (DataIncorrectaExcepcion dataIncorrectaExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(dataIncorrectaExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping(value = "usuario/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(usuarioService.buscarPorId(id), HttpStatus.OK);
        } catch (EntidadInactivaExcepcion entidadInactivaExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(entidadInactivaExcepcion.getMessage())
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

    @PatchMapping(value = "usuario/{email}", headers = "Accept=application/json")
    public ResponseEntity<?> changePassword(@PathVariable String email, @RequestBody CambiarContraseniaDTO cambiarContraseniaDTO) {
        try {
            usuarioService.cambiarContrasenia(email, cambiarContraseniaDTO);
            return new ResponseEntity<>(cambiarContraseniaDTO, HttpStatus.OK);
        } catch (NoExisteEntidadExcepcion e) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.NOT_FOUND.value())
                    .mensaje(e.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.NOT_FOUND);
        } catch (EntidadInactivaExcepcion | DataIncorrectaExcepcion e) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(e.getMessage())
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "usuario/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            boolean usuarioEliminado = usuarioService.eliminar(id);
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .mensaje("usario eliminado")
                    .codigoEstado(HttpStatus.OK.value())
                    .isCorrecto(usuarioEliminado)
                    .build(), HttpStatus.OK);
        } catch (NoExisteEntidadExcepcion noExisteEntidadExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.NOT_FOUND.value())
                    .mensaje(noExisteEntidadExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.NOT_FOUND);
        } catch (EntidadInactivaExcepcion entidadInactivaExcepcion) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(entidadInactivaExcepcion.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "usuario/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserRequest authenticationRequest) {
        try {
            if (usuarioService.validarEmailContrasenia(authenticationRequest.getUserName(), authenticationRequest.getPassWord())) {
                authenticate(authenticationRequest.getUserName(), "password");
                final UserDetails userDetails = userDetailsService
                        .loadUserByUsername(authenticationRequest.getUserName());
                final String token = jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(new TokenResponse(token));
            }else{
                return new ResponseEntity<>(RespuestaBaseDTO.builder()
                        .codigoEstado(HttpStatus.CONFLICT.value())
                        .mensaje("Hubo un error, intenta mas tarde.")
                        .isCorrecto(false)
                        .build(), HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(RespuestaBaseDTO.builder()
                    .codigoEstado(HttpStatus.CONFLICT.value())
                    .mensaje(e.getMessage())
                    .isCorrecto(false)
                    .build(), HttpStatus.CONFLICT);
        }
    }






    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
