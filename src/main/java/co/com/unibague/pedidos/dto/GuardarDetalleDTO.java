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

    private Long productoId;
    private Long pedidoId;
    private DetallePedidoDTO detalle;
}
