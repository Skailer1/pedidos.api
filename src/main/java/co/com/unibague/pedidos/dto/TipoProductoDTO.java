package co.com.unibague.pedidos.dto;


import co.com.unibague.pedidos.model.TipoProducto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoProductoDTO
{
    private Long id;
    private String descripcion;
    private boolean isActivo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public TipoProducto covertirTipoProducto() {
        return TipoProducto.builder()
                .id(id)
                .descripcion(descripcion)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }
}
