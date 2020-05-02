package co.com.unibague.pedidos.dto;
import co.com.unibague.pedidos.model.Datos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatosDTO
{
    private long id;
    private String nombreEmpresa;
    private long nit;
    private String razonSocial;
    private boolean isActivo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public Datos covertirDatos() {
        return Datos.builder()
                .id(id)
                .nombreEmpresa(nombreEmpresa)
                .nit(nit)
                .razonSocial(razonSocial)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }
}
