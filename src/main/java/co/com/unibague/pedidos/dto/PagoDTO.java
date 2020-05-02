package co.com.unibague.pedidos.dto;
import co.com.unibague.pedidos.enums.TipoPago;
import co.com.unibague.pedidos.model.Pago;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagoDTO
{
    private long pedidoId;
    private TipoPago tipoPago;
    private double total;
    private boolean isActivo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public Pago covertirPago() {
        return Pago.builder()
                .pedidoId(pedidoId)
                .tipoPago(tipoPago)
                .totalPago(total)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }
}