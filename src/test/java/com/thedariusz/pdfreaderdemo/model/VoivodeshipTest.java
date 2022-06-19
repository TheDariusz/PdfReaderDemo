package com.thedariusz.pdfreaderdemo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoivodeshipTest {

    @Test
    public void shouldReturnNameOfMazowieckie() {
        String teryt = "MZ";

        assertSame(Voivodeship.MZ, Voivodeship.valueOf(teryt));
        System.out.println(Voivodeship.MZ.label);

    }

}