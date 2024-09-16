package co.com.unibague.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardarDetalleDTO
{

    private Long producto;
    private Long pedido;
    private DetallePedidoDTO detalle;
}
