package com.thedariusz.pdfreaderdemo.model;

import java.time.LocalDateTime;
import java.util.List;

public record ImgwMeteoAlert(
        int number,
        Voivodeship voivodeship,
        LocalDateTime published,
        List<ImgwLocalMeteoAlert> localMeteoWarnings
) {
}
