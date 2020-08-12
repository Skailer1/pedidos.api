package co.com.unibague.pedidos.dto;


import co.com.unibague.pedidos.model.DetallePedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetallePedidoDTO {
    private Long id;
    private int cantidad;
    private double valorUnitario;
    private double total;
    private boolean isActivo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public DetallePedido covertirDetalle() {
        return DetallePedido.builder()
                .id(id)
                .cantidad(cantidad)
                .valorUnitario(valorUnitario)
                .total(total)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }


}
