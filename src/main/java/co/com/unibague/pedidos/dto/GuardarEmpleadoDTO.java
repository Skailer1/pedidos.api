package co.com.unibague.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardarEmpleadoDTO {

    private long tipoDocumentoId;
    private long usuarioId;
    private long tipoEmpleadoId;
    private EmpleadoDTO empleado;

}
