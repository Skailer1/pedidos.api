package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.dto.GuardarPagoDTO;
import co.com.unibague.pedidos.model.Datos;
import co.com.unibague.pedidos.model.Empleado;
import co.com.unibague.pedidos.model.Pago;
import co.com.unibague.pedidos.model.Pedido;
import co.com.unibague.pedidos.repository.PagoRepository;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IDatosService;
import co.com.unibague.pedidos.service.impl.IEmpleadoService;
import co.com.unibague.pedidos.service.impl.IPagoService;
import co.com.unibague.pedidos.service.impl.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service("pagoService")
public class PagoService implements IPagoService {

        @Autowired
        private PagoRepository pagoRepository;
        @Autowired
        private IEmpleadoService empleadoService;
        @Autowired
        private IPedidoService pedidoService;
        @Autowired
        private IDatosService datosService;



    @Override
    public Pago crear(GuardarPagoDTO guardarPago) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, YaExisteEntidadExcepcion, DataIncorrectaExcepcion {
        Pago pago = guardarPago.getPago().covertirPago();
        Empleado empleadoPorId = empleadoService.buscarPorId(guardarPago.getEmpleadoId());
        pago.setEmpleadoId(empleadoPorId);
        Pedido pedidoPorId = pedidoService.buscarPorId(guardarPago.getPedidoId());
        pago.setPedidoId(pedidoPorId);
        Datos datosPorId = datosService.buscarPorId(guardarPago.getDatosId()) ;
        pago.setDatosId(datosPorId);
        if (!pago.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
              } else if (pagoRepository.findById(pago.getId()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un pago con ese id");
        } else {
            pago.setFechaCreacion(new Date());
            pago.setFechaActualizacion(new Date());
            pago.setActivo(true);
            return pagoRepository.save(pago);
        }
    }
//el id de pago es el de pedido pselo amigo
    @Override
    public Pago actualizar(Long id, Pago pago) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!pago.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (!pagoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe estado con ese id");
        } else if (!pagoRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El estado que se desea actualizar esta inactivo");
        } else {
            Pago pagoBuscado = buscarPorId(id);
            pagoBuscado.setTotalPago(pago.getTotalPago());
            pagoBuscado.setTipoPago(pago.getTipoPago());
            pagoBuscado.setFechaActualizacion(new Date());
            return pagoRepository.save(pagoBuscado);
        }
    }


    @Override
    public boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        boolean resultado = false;
        Pago pagoPorId = buscarPorId(id);
         if (!pagoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe pago con ese id");
        } else if (!pagoRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El pago que se desea eliminar esta inactivo");
        } else if (pagoPorId != null) {
            pagoPorId.setActivo(false);
            pagoPorId.setFechaActualizacion(new Date());
            pagoRepository.save(pagoPorId);
            resultado = true;
        }
        return resultado;
    }


    @Override
    public Pago buscarPorId(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<Pago> pagoPorId = pagoRepository.findById(id);
        if (!pagoPorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un tipoDocumento con ese id");
        }
        Pago pagoBuscado = pagoPorId.get();
        if (!pagoBuscado.isActivo()) {
            throw new EntidadInactivaExcepcion("El tipoDocumento se encuentra inactivo");
        } else {
            return pagoBuscado;
        }
    }


}
