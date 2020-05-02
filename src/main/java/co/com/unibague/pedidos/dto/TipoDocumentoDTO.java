package co.com.unibague.pedidos.dto;

import co.com.unibague.pedidos.enums.RH;
import co.com.unibague.pedidos.enums.Sexo;
import co.com.unibague.pedidos.model.TipoDocumento;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumentoDTO {

    private Long id = 0L;
    private String descripcionDocumento;
    private boolean isActivo;
    private Date fechaCreacion = new Date();
    private Date fechaActualizacion = new Date();

    public TipoDocumento covertirTipoDocumento() {
        return TipoDocumento.builder()
                .id(id)
                .descripcionDocumento(descripcionDocumento)
                .isActivo(isActivo)
                .fechaCreacion(fechaCreacion)
                .fechaActualizacion(fechaActualizacion)
                .build();
    }
}
