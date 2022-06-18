package com.thedariusz.pdfreaderdemo.model;

import java.time.LocalDateTime;
import java.util.List;

public record ImgwMeteoWarning(
        int number,
        String voivodeship,
        LocalDateTime published,
        List<ImgwLocalMeteoWarning> localMeteoWarnings
) {
}
