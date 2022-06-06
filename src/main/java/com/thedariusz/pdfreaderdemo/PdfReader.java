package com.thedariusz.pdfreaderdemo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface PdfReader {

    String getText(String fileName) throws IOException;

    List<BufferedImage> getImages(String fileName) throws IOException;

}
