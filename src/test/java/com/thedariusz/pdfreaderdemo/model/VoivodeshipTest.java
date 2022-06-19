package com.thedariusz.pdfreaderdemo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoivodeshipTest {

    @Test
    void shouldReturnNameOfMazowieckie() {
        String teryt = "MZ";

        assertSame(Voivodeship.MZ, Voivodeship.valueOf(teryt));
        System.out.println(Voivodeship.MZ.label);

    }

    @Test
    void shouldFindDolnoslaskie() {
        String testAlertText = """
                Zasięg ostrzeżeń w województwie
                WOJEWÓDZTWO DOLNOŚLĄSKIE
                OSTRZEŻENIA METEOROLOGICZNE ZBIORCZO NR 82
                WYKAZ OBOWIĄZUJĄCYCH OSTRZEŻEŃ
                o godz. 12:42 dnia 18.06.2022
                """;

        Voivodeship searchedVoivodeship = Voivodeship.isInString(testAlertText.toLowerCase());

        assertSame(Voivodeship.DS, searchedVoivodeship);
    }

    @Test
    void shouldNotFindAnyVoivodeship() {
        String testAlertText = """
                Zasięg ostrzeżeń w województwie
                WOJEWÓDZTWO
                OSTRZEŻENIA METEOROLOGICZNE ZBIORCZO NR 82
                WYKAZ OBOWIĄZUJĄCYCH OSTRZEŻEŃ
                o godz. 12:42 dnia 18.06.2022
                """;

        Voivodeship searchedVoivodeship = Voivodeship.isInString(testAlertText.toLowerCase());

        assertNull(searchedVoivodeship);
    }

}