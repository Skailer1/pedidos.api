package co.com.unibague.pedidos.dto;

import co.com.unibague.pedidos.model.Mesa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MesaDTO
{
    private Long id;
    private String nombreMesa;
    private boolean isActivo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public Mesa covertirMesa() {
        return Mesa.builder()
                .id(id)
                .nombreMesa(nombreMesa)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }
}
