package com.thedariusz.pdfreaderdemo;

import java.awt.image.BufferedImage;
import java.util.List;

public record PdfWrapper (
        String text, List<BufferedImage> images){
}
