package co.com.unibague.pedidos.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum DescripcionEstado
{
    @JsonProperty("-")
    VACIO("-"),
    @JsonProperty("CREADO")
    CREADO("CREADO"),
    @JsonProperty("COCINA")
    EN_COCINA("COCINA"),
    @JsonProperty("PREPARACION")
    EN_PREPARACION("PREPARACION"),
    @JsonProperty("ENTREGADO")
    ENTREGRADO("ENTREGADO");

    @Getter
    private String name;

    DescripcionEstado(String name) {
        this.name = name;
    }
}
