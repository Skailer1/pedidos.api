package co.com.unibague.pedidos.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardarPagoDTO
{
  //  private Long pedidoId;
    private Long empleadoId;
    private Long datosId;
    private PagoDTO pago;
}
