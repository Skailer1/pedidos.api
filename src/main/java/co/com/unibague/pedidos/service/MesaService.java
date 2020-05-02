package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.model.Mesa;
import co.com.unibague.pedidos.repository.MesaRepository;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IMesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("mesaService")
public class MesaService implements IMesaService
{
    @Autowired
    private MesaRepository mesaRepository;

    @Override
    public List<Mesa> listarTodos() throws NoExisteEntidadExcepcion {
        List<Mesa> tipoEstados = mesaRepository.findByActivo(true);
        if (tipoEstados.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay tipoDocumentos registrados");
        } else {
            return tipoEstados;
        }
    }



}
