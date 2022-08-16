package com.thedariusz.pdfreaderdemo.model;

import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Counties {
    private static final Pattern ALERT_COUNTIES = Pattern.compile("([A-ZĄĆĘŁŃÓŚŻŹa-ząćęłńóśżź][A-ZĄĆĘŁŃÓŚŻŹa-ząćęłńóśżź \\-\\r\\n]+)\\((\\d+Cou)\\)");

    public final Map<String, Integer> value;
    
    public Counties(String rawText) {
        this.value = getCounties(rawText);
    }

    private Map<String, Integer> getCounties(String text) {
        return ALERT_COUNTIES.matcher(text)
                .results()
                .collect(Collectors.toMap(matchResult -> matchResult.group(1), this::convertToInt));
    }

    private Integer convertToInt(MatchResult matchResult) {
        try {
            return Integer.parseInt(matchResult.group(2));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
