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
public class DetallePedidoDTO

{
    private Long id;
    private int cantidad;
    private double valorUnitario;
    private double total;
    /* private ProductoDTO producto;
    private PedidoDTO pedido;*/
    private boolean isActivo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public DetallePedido covertirDatos() {
        return DetallePedido.builder()
                .cantidad(cantidad)
                .valorUnitario(valorUnitario)
                .total(total)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }




}
