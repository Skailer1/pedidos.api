package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.model.Usuario;
import co.com.unibague.pedidos.repository.UsuarioRepository;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("usuarioService")
public class UsuarioService implements IUsuarioService
{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario crear(Usuario usuario) throws YaExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!usuario.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
         } else if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un ususario con ese id");
        } else {
            usuario.setFechaCreacion(new Date());
            usuario.setFechaActualizacion(new Date());
            usuario.setActivo(true);
            return usuarioRepository.save(usuario);
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
            usuarioBuscado.setContrasenia(usuario.getContrasenia());
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
    public List<Usuario> listarTodos() throws NoExisteEntidadExcepcion {
        List<Usuario> usuarios = usuarioRepository.findByActivo(true);
        if (usuarios.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay usuarios registrados");
        } else {
            return usuarios;
        }
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


}
