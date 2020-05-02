package co.com.unibague.pedidos.dto;

import co.com.unibague.pedidos.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;
    private String nombreUsuario;
    private String correo;
    private String contrasenia;
    private boolean isActivo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public Usuario covertirUsuario() {
        return Usuario.builder()
                .id(id)
                .nombreUsuario(nombreUsuario)
                .correo(correo)
                .contrasenia(contrasenia)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }
}
