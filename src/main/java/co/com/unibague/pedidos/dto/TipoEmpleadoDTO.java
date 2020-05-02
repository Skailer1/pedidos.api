package co.com.unibague.pedidos.dto;

import co.com.unibague.pedidos.model.TipoEmpleado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoEmpleadoDTO {

    private Long id;
    private String nombre;
    private boolean isActivo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public static TipoEmpleado covertirTipoEmpleado(TipoEmpleadoDTO tipoEmpleadoDTO) {
        return TipoEmpleado.builder()
                .nombre(tipoEmpleadoDTO.nombre)
                .fechaCreacion(tipoEmpleadoDTO.fechaCreacion)
                .fechaActualizacion(tipoEmpleadoDTO.fechaCreacion)
                .isActivo(tipoEmpleadoDTO.isActivo)
                .build();

    }
}
