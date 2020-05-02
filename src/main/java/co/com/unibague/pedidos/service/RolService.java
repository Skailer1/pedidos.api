package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.model.Rol;
import co.com.unibague.pedidos.repository.RolRepository;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("rolService")
public class RolService implements IRolService
{
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol crear(Rol rol) throws YaExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!rol.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
       } else if (rolRepository.findById(rol.getId()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un rol con ese id");
        } else {
            rol.setFechaCreacion(new Date());
            rol.setFechaActualizacion(new Date());
            rol.setActivo(true);
            return rolRepository.save(rol);
        }
    }

    @Override
    public Rol actualizar(Long id, Rol rol) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!rol.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (!rolRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un rol con ese id");
        } else if (!rolRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El rol que se desea actualizar esta inactivo");
        } else {
            Rol rolBuscado = buscarPorId(id);
            rolBuscado.setNombreRol(rol.getNombreRol());
            rolBuscado.setDescripcion(rol.getDescripcion());
            rolBuscado.setFechaActualizacion(new Date());
            return rolRepository.save(rolBuscado);
        }
    }

    @Override
    public boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        boolean resultado = false;
        Rol rolPorId = buscarPorId(id);
        if (!rolRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un rol con ese id");
        } else if (!rolRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El rol que se desea eliminar esta inactivo");
        } else if (rolPorId != null) {
            rolPorId.setActivo(false);
            rolPorId.setFechaActualizacion(new Date());
            rolRepository.save(rolPorId);
            resultado = true;
        }
        return resultado;
    }

    @Override
    public List<Rol> listarTodos() throws NoExisteEntidadExcepcion {
        List<Rol> roles = rolRepository.findByActivo(true);
        if (roles.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay roles registrados");
        } else {
            return roles;
        }
    }

    @Override
    public Rol buscarPorId(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<Rol> rolPorId = rolRepository.findById(id);
        if (!rolPorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un tipoDocumento con ese id");
        }
        Rol rolBuscado = rolPorId.get();
        if (!rolBuscado.isActivo()) {
            throw new EntidadInactivaExcepcion("El tipoDocumento se encuentra inactivo");
        } else {
            return rolBuscado;
        }
    }
}
