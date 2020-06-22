package co.com.unibague.pedidos.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardarPedidoDTO {
    private Long mesaId;
    private Long empleadoId;
    private Long estadoId;
    private PedidoDTO pedido = new PedidoDTO();

}
