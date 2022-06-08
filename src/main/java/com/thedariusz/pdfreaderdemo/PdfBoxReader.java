package com.thedariusz.pdfreaderdemo;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfBoxReader implements PdfReader {

    @Override
    public String getText(InputStream inputStream) throws IOException {
        PDDocument pdDocument = PDDocument.load(inputStream);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        return pdfStripper.getText(pdDocument);
    }

    @Override
    public List<BufferedImage> getImages(InputStream inputStream) throws IOException {
        List<BufferedImage> images = new ArrayList<>();
        PDDocument pdDocument = PDDocument.load(inputStream);

        for (PDPage pdPage : pdDocument.getPages()) {
            PDResources resources = pdPage.getResources();
            for (COSName c : resources.getXObjectNames()) {
                PDXObject obj = resources.getXObject(c);
                if (obj instanceof PDImageXObject pdImageXObject) {
                    BufferedImage image = pdImageXObject.getImage();
                    images.add(image);
                }
            }
        }
        return images;
    }

}
