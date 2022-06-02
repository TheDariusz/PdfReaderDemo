package com.thedariusz.pdfreaderdemo;

import java.io.IOException;
import java.util.List;

public interface HtmlParser {

    List<String> getTextWithPatternFromHrefs(String url, String pattern) throws IOException;
}
