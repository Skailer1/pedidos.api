package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.dto.CambiarContraseniaDTO;
import co.com.unibague.pedidos.model.Usuario;
import co.com.unibague.pedidos.repository.UsuarioRepository;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IUsuarioService;
import co.com.unibague.pedidos.util.ValidacionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("usuarioService")
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Usuario crear(Usuario usuario) throws YaExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!usuario.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un ususario con ese correo");
        } else {
            usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
            usuario.setFechaCreacion(new Date());
            usuario.setFechaActualizacion(new Date());
            usuario.setActivo(true);
            return usuarioRepository.save(usuario);
        }
    }

    public List<Usuario> listarTodos() throws NoExisteEntidadExcepcion {
        List<Usuario> pedidos = (List<Usuario>) usuarioRepository.findAll();
        if (pedidos.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay pedidos registrados");
        }
        else {
            return pedidos;
        }
    }


    @Override
    public Usuario actualizar(Long id, Usuario usuario) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!usuario.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (!usuarioRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe usuario con ese id");
        } else if (!usuarioRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El usuario que se desea actualizar esta inactivo");
        } else {
            Usuario usuarioBuscado = buscarPorId(id);
            usuarioBuscado.setCorreo(usuario.getCorreo());
            usuarioBuscado.setNombreUsuario(usuario.getNombreUsuario());
            usuarioBuscado.setFechaActualizacion(new Date());
            return usuarioRepository.save(usuarioBuscado);
        }
    }

    @Override
    public boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        boolean resultado = false;
        Usuario usuarioPorId = buscarPorId(id);
        if (!usuarioRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un usuario con ese id");
        } else if (!usuarioRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El usuario que se desea eliminar esta inactivo");
        } else if (usuarioPorId != null) {
            usuarioPorId.setActivo(false);
            usuarioPorId.setFechaActualizacion(new Date());
            usuarioRepository.save(usuarioPorId);
            resultado = true;
        }
        return resultado;
    }


    @Override
    public Usuario buscarPorId(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<Usuario> usuarioPorId = usuarioRepository.findById(id);
        if (!usuarioPorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un usuario con ese id");
        }
        Usuario usuarioBuscado = usuarioPorId.get();
        if (!usuarioBuscado.isActivo()) {
            throw new EntidadInactivaExcepcion("El ususario se encuentra inactivo");
        } else {
            return usuarioBuscado;
        }
    }

    @Override
    public Usuario findByCorreo(String correo) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion,
            DataIncorrectaExcepcion {
        if (correo == null) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (!ValidacionUtil.isCorreoValido(correo)) {
            throw new DataIncorrectaExcepcion("El email enviado no es valido");
        } else if (!usuarioRepository.findByCorreo(correo).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un usuario con ese email");
        } else if (!usuarioRepository.findByCorreo(correo).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El usuario con este email se encuentra inactivo actualmente");
        } else {
            return usuarioRepository.findByCorreo(correo).get();
        }
    }

    @Override
    public boolean validarEmailContrasenia(String email, String contrasenia) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion, DataIncorrectaExcepcion {
        Optional<Usuario> usuarioByEmail = usuarioRepository.findByCorreo(email);
         if (!usuarioByEmail.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un usuario con ese email");
        } else if (!usuarioByEmail.get().isActivo()) {
            throw new EntidadInactivaExcepcion("EL cajero con este email se encuentra inactivo actualmente");
        } else {
            if (!usuarioRepository.findByCorreoAndContrasenia(email, contrasenia).isPresent()) {
                throw new DataIncorrectaExcepcion("Verifique la contraseña");
            }
                else {
                return true;
            }
        }
    }

    @Override
    public Usuario cambiarContrasenia(String correo, CambiarContraseniaDTO cambiarContrasenia) throws NoExisteEntidadExcepcion,
            EntidadInactivaExcepcion, DataIncorrectaExcepcion {

        Usuario actualizarUsuario = findByCorreo(correo);

        if (cambiarContrasenia.getContraseniaaActual() == null
                || cambiarContrasenia.getContraseniaNueva() == null) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (!actualizarUsuario.getContrasenia().equals(cambiarContrasenia.getContraseniaNueva())) {
            throw new DataIncorrectaExcepcion("La contraseña actual es incorrecta");
        } else if (cambiarContrasenia.getContraseniaaActual().equals(cambiarContrasenia.getContraseniaNueva())) {
            throw new DataIncorrectaExcepcion("La contraseña actual es igual a la contraseña nueva");
        } else {
            actualizarUsuario.setContrasenia(cambiarContrasenia.getContraseniaNueva());
            actualizarUsuario.setFechaActualizacion(new Date());
            return usuarioRepository.save(actualizarUsuario);
        }
    }


}

