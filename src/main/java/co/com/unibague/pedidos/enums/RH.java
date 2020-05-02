package co.com.unibague.pedidos.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Tuple;

public enum RH
{
    @JsonProperty("-")
    VACIO("-"),
    @JsonProperty("A+")
    APOSITIVO("A+"),
    @JsonProperty("A-")
    ANEGATIVO("A-"),
    @JsonProperty("B+")
    BPOSITIVO("B+"),
    @JsonProperty("B-")
    BNEGATIVO("B-"),
    @JsonProperty("AB+")
    ABPOSITIVO("AB+"),
    @JsonProperty("AB-")
    ABNEGATIVO("AB-"),
    @JsonProperty("O+")
    OPOSITIVO("O+"),
    @JsonProperty("O-")
    ONEGATIVO("O-");

    @Getter
    private String name;

    RH(String name) {
        this.name = name;
    }



}
