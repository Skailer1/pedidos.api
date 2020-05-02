package co.com.unibague.pedidos.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum DescripcionEstado
{
    @JsonProperty("-")
    VACIO("-"),
    @JsonProperty("CREADO")
    CREADO("CREADO"),
    @JsonProperty("EN COCINA")
    EN_COCINA("EN COCINA"),
    @JsonProperty("EN PREPARACION")
    EN_PREPARACION("EN PREPARACION"),
    @JsonProperty("ENTREGADO")
    ENTREGRADO("ENTREGADO");

    @Getter
    private String name;

    DescripcionEstado(String name) {
        this.name = name;
    }
}
