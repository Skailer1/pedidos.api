package co.com.unibague.pedidos.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum TipoEmpleadoEnum
{
    @JsonProperty("-")
    VACIO("-"),
    @JsonProperty("MESERO")
    MESERO("MESERO"),
    @JsonProperty("COCINERO")
    COCINERO("COCINERO"),
    @JsonProperty("CAJA")
    CAJA("CAJA");

    @Getter
    private String name;

    TipoEmpleadoEnum(String name) {
        this.name = name;
    }
}
