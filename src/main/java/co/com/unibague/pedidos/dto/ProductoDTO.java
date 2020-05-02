package co.com.unibague.pedidos.dto;

import co.com.unibague.pedidos.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDTO
{
    private Long id;
    private int cantidadEnStock;
    private double costo;
    private Date fechaCreación;
    private Date fechaActualizacion;
    private String nombreProducto;
    private double iva;
    private Boolean isActivo;

    public Producto covertirProducto() {
        return Producto.builder()
                .id(id)
                .cantidadEnStock(cantidadEnStock)
                .costo(costo)
                .nombreProducto(nombreProducto)
                .iva(iva)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }
}
