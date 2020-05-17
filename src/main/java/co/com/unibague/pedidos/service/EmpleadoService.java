package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.dto.EmpleadoDTO;
import co.com.unibague.pedidos.dto.GuardarEmpleadoDTO;
import co.com.unibague.pedidos.model.TipoDocumento;
import co.com.unibague.pedidos.model.TipoEmpleado;
import co.com.unibague.pedidos.model.Usuario;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IEmpleadoService;
import co.com.unibague.pedidos.model.Empleado;
import co.com.unibague.pedidos.repository.EmpleadoRepository;
import co.com.unibague.pedidos.service.impl.ITipoDocumentoService;
import co.com.unibague.pedidos.service.impl.ITipoEmpleadoService;
import co.com.unibague.pedidos.service.impl.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("empleadoService")
public class EmpleadoService implements IEmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;


    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private ITipoEmpleadoService tipoEmpleadoService;
    @Autowired
    private ITipoDocumentoService tipoDocumentoService;

    @Override
    public Empleado crear(GuardarEmpleadoDTO guardarEmpleado) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion, YaExisteEntidadExcepcion {
        Empleado empleado = guardarEmpleado.getEmpleado().covertirEmpleado();
        Usuario usuarioPorId = usuarioService.buscarPorId(guardarEmpleado.getUsuarioId());
        empleado.setUsuarioId(usuarioPorId);
        TipoEmpleado tipoEmpleadoPorId = tipoEmpleadoService.buscarPorId(guardarEmpleado.getTipoEmpleado());
        empleado.setTipoEmpleado(tipoEmpleadoPorId);
        TipoDocumento tipoDocumentoPorId = tipoDocumentoService.buscarPorId(guardarEmpleado.getTipoDocumento());
        empleado.setTipoDocumento(tipoDocumentoPorId);
        if (!empleado.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        }
        if (empleadoRepository.findByNumeroDocumentoAndTipoDocumento_Id(empleado.getNumeroDocumento(), guardarEmpleado.getTipoDocumento()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un empleado con ese numero y tipo de documento");
        } else {
            empleado.setActivo(true);
            empleado.setFechaCreacion(new Date());
            empleado.setFechaActualizacion(new Date());
            return empleadoRepository.save(empleado);
        }
    }

    @Override
    public Empleado actualizar(Long id, EmpleadoDTO empleadoDTO) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion {
        Empleado empleado = empleadoDTO.covertirEmpleado();
        if (!empleado.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (!empleadoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un pedido con ese id");
        } else if (!empleadoRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El empleado que se desea actualizar esta inactivo");
        }
        else {
            Empleado empleadoBuscado = buscarPorId(id);
            //empleadoBuscado.setCorreo(empleado.getCorreo());
            empleadoBuscado.setDireccion(empleado.getDireccion());
            empleadoBuscado.setNombre(empleado.getNombre());
            empleadoBuscado.setTelefono(empleado.getTelefono());
            empleadoBuscado.setRh(empleado.getRh());
            empleadoBuscado.setSexo(empleado.getSexo());
            //empleadoBuscado.setNumeroDocumento(empleado.getNumeroDocumento());
            empleadoBuscado.setFechaActualizacion(new Date());
            return empleadoRepository.save(empleadoBuscado);
        }
    }


    @Override
    public boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        boolean resultado = false;
        Empleado empleadoPorId = buscarPorId(id);
        if (!empleadoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe empleado con ese id");
        } else if (!empleadoRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El empleado que se desea eliminar esta inactivo");
        } else if (empleadoPorId != null) {
            empleadoPorId.setActivo(false);
            empleadoPorId.setFechaActualizacion(new Date());
            empleadoRepository.save(empleadoPorId);
            resultado = true;
        }
        return resultado;
    }


    @Override
    public Empleado buscarPorId(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<Empleado> empleadoPorId = empleadoRepository.findById(id);
        if (!empleadoPorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un empleado con ese id");
        }
        Empleado empleadoBuscado = empleadoPorId.get();
        if (!empleadoBuscado.isActivo()) {
            throw new EntidadInactivaExcepcion("El empleado se encuentra inactivo");
        } else {
            return empleadoBuscado;
        }
    }


}
