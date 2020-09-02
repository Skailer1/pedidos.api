package co.com.unibague.pedidos.dto;

import co.com.unibague.pedidos.model.enums.DescripcionEstado;
import co.com.unibague.pedidos.model.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoDTO
{
    private Long id;
    private DescripcionEstado descripcionEstado;
    private boolean isActivo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public EstadoPedido covertirEstado() {
        return EstadoPedido.builder()
                .id(id)
                .descripcionEstado(descripcionEstado)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }

}
