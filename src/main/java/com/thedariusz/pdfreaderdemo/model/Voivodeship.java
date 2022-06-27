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

    public static Optional<Voivodeship> isInString(String text) {
        String textToLowerCase = text.toLowerCase();
        return Arrays.stream(values())
                .filter(voivodeship -> textToLowerCase.contains(voivodeship.label))
                .findFirst();
    }
}
