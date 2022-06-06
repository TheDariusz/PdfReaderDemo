package com.thedariusz.pdfreaderdemo;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.net.ssl.HttpsURLConnection;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PdfBoxReader implements PdfReader {

    @Override
    public String getText(String fileName) throws IOException {
        PDDocument pdDocument = PDDocument.load(getInputStream(fileName));
        PDFTextStripper pdfStripper = new PDFTextStripper();
        return pdfStripper.getText(pdDocument);
    }

    @Override
    public List<BufferedImage> getImages(String fileName) throws IOException {
        PDDocument pdDocument = PDDocument.load(getInputStream(fileName));
        List<BufferedImage> images = new ArrayList<>();
        Iterator<PDPage> pageIterator = pdDocument.getPages().iterator();
        while (pageIterator.hasNext()) {
            PDResources resources = pageIterator.next().getResources();
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

    private InputStream getInputStream(String pdfUri) throws IOException {
        URL myurl = new URL(pdfUri);
        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
        return con.getInputStream();
    }
}
