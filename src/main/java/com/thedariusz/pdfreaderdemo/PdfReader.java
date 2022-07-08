package com.thedariusz.pdfreaderdemo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface PdfReader {

    String extractContentAsText(InputStream inputStream) throws IOException;

    List<BufferedImage> getImages(InputStream inputStream) throws IOException;
}
