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

    private long tipoDocumento;
    private long usuarioId;
    private long tipoEmpleado;
    private EmpleadoDTO empleado;

}
