package com.thedariusz.pdfreaderdemo;

import java.awt.image.BufferedImage;
import java.util.List;

public interface PdfReader {

    String getText();

    List<BufferedImage> getImages();

}
