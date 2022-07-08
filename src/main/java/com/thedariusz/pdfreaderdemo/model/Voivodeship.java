package com.thedariusz.pdfreaderdemo.model;

import java.util.Arrays;
import java.util.Optional;

public enum Voivodeship {
    DS("dolnośląskie"),
    KP("kujawsko-pomorskie"),
    LU("lubelskie"),
    LB("lubuskie"),
    LD("łódzkie"),
    MA("małopolskie"),
    MZ("mazowieckie"),
    OP("opolskie"),
    PK("podkarpackie"),
    PD("podlaskie"),
    PM("pomorskie"),
    SL("śląskie"),
    SK("świętokrzyskie"),
    WN("warmińsko-mazurskie"),
    WP("wielkopolskie"),
    ZP("zachodniopomorskie"),
    UNDEFINED("undefined");

    public final String label;

    Voivodeship(String label) {
        this.label = label;
    }

    public static Voivodeship fromText(String text) {
        return Arrays.stream(values())
                .filter(value -> text.toLowerCase().contains(value.label))
                .findFirst()
                .orElse(Voivodeship.UNDEFINED);
    }

}
