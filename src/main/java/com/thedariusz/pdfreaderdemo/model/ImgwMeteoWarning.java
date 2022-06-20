package com.thedariusz.pdfreaderdemo.model;

import java.time.LocalDateTime;
import java.util.List;

public record ImgwMeteoWarning(
        int number,
        Voivodeship voivodeship,
        LocalDateTime published,
        List<ImgwLocalMeteoWarning> localMeteoWarnings
) {
}
