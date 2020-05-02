package co.com.unibague.pedidos.dto;

import co.com.unibague.pedidos.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO
{
    private Long id;
    private Date fechaPedido;
    private boolean isActivo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public Pedido covertirPedido() {
        return Pedido.builder()
                .id(id)
                .fechaPedido(fechaPedido)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }
}
