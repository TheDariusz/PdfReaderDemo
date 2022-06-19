package com.thedariusz.pdfreaderdemo.model;

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
    ZP("zachodniopomorskie");

    public final String label;

    Voivodeship(String label) {
        this.label = label;
    }

    public static Voivodeship isInString(String text) {
        String textToLowerCase = text.toLowerCase();
        for (Voivodeship voivodeship : values()) {
            if (textToLowerCase.contains(voivodeship.label)) {
                return voivodeship;
            }
        }
        return null;
    }
}
