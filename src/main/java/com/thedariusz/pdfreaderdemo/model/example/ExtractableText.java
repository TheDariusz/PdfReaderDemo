package com.thedariusz.pdfreaderdemo.model.example;

import java.util.regex.Pattern;

public interface ExtractableText {
    String NOT_FOUND = "";
    
    default String extractText(Pattern pattern, String rawText) {
        return pattern.matcher(rawText)
                .results()
                .map(matchResult -> matchResult.group(1))
                .findFirst()
                .orElse(NOT_FOUND);
    }
}