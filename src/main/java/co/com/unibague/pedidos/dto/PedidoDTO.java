package co.com.unibague.pedidos.dto;

import co.com.unibague.pedidos.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {
    private Long id;
    private Date fechaPedido = new Date();
    private boolean isActivo = true;
    private Date fechaCreacion = new Date();
    private Date fechaActualizacion = new Date();

    public Pedido covertirPedido() {
        return Pedido.builder()
                .id(id)
                .fechaPedido(fechaPedido)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }
}
