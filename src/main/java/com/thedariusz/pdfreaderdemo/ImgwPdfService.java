package com.thedariusz.pdfreaderdemo;

import java.awt.image.BufferedImage;
import java.util.List;

public class ImgwPdfService implements PdfReader {
    private static final String CURRENT_ALERTS_IMGW_URL="https://danepubliczne.imgw.pl/data/current/ost_meteo/";

    @Override
    public String getText() {
        return null;
    }

    @Override
    public List<BufferedImage> getImages() {
        return null;
    }
}
