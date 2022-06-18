package com.thedariusz.pdfreaderdemo;

import org.junit.jupiter.api.Test;

public class TextToModelMapper {

    private final static String sampleAlert = "Zasięg ostrzeżeń w województwie\n" +
            "WOJEWÓDZTWO DOLNOŚLĄSKIE\n" +
            "OSTRZEŻENIA METEOROLOGICZNE ZBIORCZO NR 82\n" +
            "WYKAZ OBOWIĄZUJĄCYCH OSTRZEŻEŃ\n" +
            "o godz. 12:42 dnia 18.06.2022\n" +
            "Zjawisko/Stopień zagrożenia Upał/2 ZMIANA\n" +
            "Obszar (w nawiasie numer\n" +
            "ostrzeżenia dla powiatu)\n" +
            "powiaty: milicki(33), oleśnicki(35)\n" +
            "Ważność od godz. 12:36 dnia 18.06.2022 do godz. 20:00 dnia 19.06.2022\n" +
            "Prawdopodobieństwo 80%\n" +
            "Przebieg Prognozuje się upały. Temperatura maksymalna w dzień od 30°C do 34°C. Temperatura\n" +
            "minimalna w nocy od 16°C do 18°C.\n" +
            "SMS IMGW-PIB OSTRZEGA: UPAŁ/2 dolnośląskie (2 powiaty) od 12:36/18.06 do 20:00/19.06.2022\n" +
            "temp. maks 34 st, temp min 18 st. Dotyczy powiatów: milicki i oleśnicki.\n" +
            "RSO Woj. dolnośląskie (2 powiaty), IMGW-PIB wydał ostrzeżenie drugiego stopnia o upałach\n" +
            "Uwagi Brak.\n" +
            "Zjawisko/Stopień zagrożenia Upał/2 ZMIANA\n" +
            "Obszar (w nawiasie numer\n" +
            "ostrzeżenia dla powiatu)\n" +
            "powiaty: oławski(36), strzeliński(36), ząbkowicki(46)\n" +
            "Ważność od godz. 12:36 dnia 18.06.2022 do godz. 20:00 dnia 19.06.2022\n" +
            "Prawdopodobieństwo 80%\n" +
            "Instytut Meteorologii i Gospodarki Wodnej - Państwowy Instytut Badawczy\n" +
            "Centralne Biuro Prognoz Meteorologicznych - Wydział we Wrocławiu\n" +
            "51-616 Wrocław ul. Parkowa 30\n" +
            "tel: 071-320-01-50, fax: 071-348-73-37\n" +
            "email: meteo.wroclaw@imgw.pl\n" +
            "www: www.imgw.pl\n" +
            "strona 1 z 3\n" +
            "Przebieg Prognozuje się upały. Temperatura maksymalna w dzień od 30°C do 33°C, lokalnie w niedzielę\n" +
            "do 34°C. Temperatura minimalna w nocy od 15°C do 18°C.\n" +
            "SMS IMGW-PIB OSTRZEGA: UPAŁ/2 dolnośląskie (3 powiaty) od 12:36/18.06 do 20:00/19.06.2022\n" +
            "temp. maks 34 st, temp min 18 st. Dotyczy powiatów: oławski, strzeliński i ząbkowicki.\n" +
            "RSO Woj. dolnośląskie (3 powiaty), IMGW-PIB wydał ostrzeżenie drugiego stopnia o upałach\n" +
            "Uwagi Brak.\n" +
            "Zjawisko/Stopień zagrożenia Upał/1\n" +
            "Obszar (w nawiasie numer\n" +
            "ostrzeżenia dla powiatu)\n" +
            "powiaty: Jelenia Góra(56), kamiennogórski(55), karkonoski(56), Wałbrzych(46), wałbrzyski(53)\n" +
            "Ważność od godz. 14:00 dnia 18.06.2022 do godz. 20:00 dnia 19.06.2022\n" +
            "Prawdopodobieństwo 80%\n" +
            "Przebieg Prognozuje się upał. Temperatura maksymalna w dzień od 30°C do 32°C. Temperatura\n" +
            "minimalna w nocy od 14°C do 17°C.\n" +
            "SMS IMGW-PIB OSTRZEGA: UPAŁ/1 dolnośląskie (5 powiatów) od 14:00/18.06 do\n" +
            "20:00/19.06.2022 temp. maks 32 st, temp min 17 st. Dotyczy powiatów: Jelenia Góra,\n" +
            "kamiennogórski, karkonoski, Wałbrzych i wałbrzyski.\n" +
            "RSO Woj. dolnośląskie (5 powiatów), IMGW-PIB wydał ostrzeżenie pierwszego stopnia o upałach\n" +
            "Uwagi Brak.\n" +
            "Zjawisko/Stopień zagrożenia Upał/2\n" +
            "Obszar (w nawiasie numer\n" +
            "ostrzeżenia dla powiatu)\n" +
            "powiaty: bolesławiecki(36), dzierżoniowski(40), głogowski(33), górowski(34), jaworski(39),\n" +
            "Legnica(37), legnicki(37), lubański(50), lubiński(36), lwówecki(51), polkowicki(35), średzki(35),\n" +
            "świdnicki(38), trzebnicki(34), wołowski(34), Wrocław(36), wrocławski(36), zgorzelecki(37),\n" +
            "złotoryjski(38)\n" +
            "Ważność od godz. 12:00 dnia 18.06.2022 do godz. 20:00 dnia 19.06.2022\n" +
            "Prawdopodobieństwo 80%\n" +
            "Przebieg Prognozuje się upały. Temperatura maksymalna w dzień od 30°C do 33°C, lokalnie w niedzielę\n" +
            "do 34°C. Temperatura minimalna w nocy od 18°C do 20°C.\n" +
            "SMS IMGW-PIB OSTRZEGA: UPAŁ/2 dolnośląskie (19 powiatów) od 12:00/18.06 do\n" +
            "20:00/19.06.2022 temp. maks 30-34 st, temp min 18-20 st. Dotyczy powiatów: bolesławiecki,\n" +
            "dzierżoniowski, głogowski, górowski, jaworski, Legnica, legnicki, lubański, lubiński, lwówecki,\n" +
            "polkowicki, średzki, świdnicki, trzebnicki, wołowski, Wrocław, wrocławski, zgorzelecki\n" +
            "i złotoryjski.\n" +
            "RSO Woj. dolnośląskie (19 powiatów), IMGW-PIB wydał ostrzeżenie drugiego stopnia o upałach\n" +
            "Uwagi Ostrzeżenie może być kontynuowane.\n" +
            "Instytut Meteorologii i Gospodarki Wodnej - Państwowy Instytut Badawczy\n" +
            "Centralne Biuro Prognoz Meteorologicznych - Wydział we Wrocławiu\n" +
            "51-616 Wrocław ul. Parkowa 30\n" +
            "tel: 071-320-01-50, fax: 071-348-73-37\n" +
            "email: meteo.wroclaw@imgw.pl\n" +
            "www: www.imgw.pl\n" +
            "strona 2 z 3\n" +
            "  Opracowanie niniejsze i jego format, jako przedmiot prawa autorskiego podlega ochronie prawnej, zgodnie z przepisami ustawy\n" +
            "z dnia 4 lutego 1994r o prawie autorskim i prawach pokrewnych (dz. U. z 2006 r. Nr 90, poz. 631 z późn. zm.).\n" +
            "  Wszelkie dalsze udostępnianie, rozpowszechnianie (przedruk, kopiowanie, wiadomość sms) jest dozwolone wyłącznie w formie\n" +
            "dosłownej z bezwzględnym wskazaniem źródła informacji tj. IMGW-PIB.\n" +
            "Instytut Meteorologii i Gospodarki Wodnej - Państwowy Instytut Badawczy\n" +
            "Centralne Biuro Prognoz Meteorologicznych - Wydział we Wrocławiu\n" +
            "51-616 Wrocław ul. Parkowa 30\n" +
            "tel: 071-320-01-50, fax: 071-348-73-37\n" +
            "email: meteo.wroclaw@imgw.pl\n" +
            "www: www.imgw.pl\n" +
            "strona 3 z 3\n";

    @Test
    public void testingStringUtilsUsage() {
        String[] split = sampleAlert.split("Zjawisko/Stopień zagrożenia ");
        for (String element : split) {
            String[] lines = element.split("\n");
            System.out.println(lines.toString());
        }
        System.out.println(split.toString());
    }
}
