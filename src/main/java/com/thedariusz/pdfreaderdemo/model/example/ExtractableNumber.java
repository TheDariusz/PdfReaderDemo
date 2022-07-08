package com.thedariusz.pdfreaderdemo.model.example;

import java.util.regex.Pattern;

public interface ExtractableNumber {
    int NOT_FOUND = -1;
    
    default int extract(Pattern pattern, String rawText) {
        return pattern.matcher(rawText)
                .results()
                .map(matchResult -> matchResult.group(1))
                .map(Integer::parseInt)
                .findFirst()
                .orElse(NOT_FOUND);
    }
    
}