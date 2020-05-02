package co.com.unibague.pedidos.dto;


import co.com.unibague.pedidos.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolDTO
{
    private Long id;
    private String nombreRol;
    private String descripcion;
    private boolean isActivo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public Rol covertirRol() {
        return Rol.builder()
                .id(id)
                .nombreRol(nombreRol)
                .descripcion(descripcion)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }
}
