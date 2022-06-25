package com.thedariusz.pdfreaderdemo;

import com.thedariusz.pdfreaderdemo.model.ImgwMeteoWarning;
import com.thedariusz.pdfreaderdemo.model.Voivodeship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImgwAlertMapperTest {

    private final static String sampleAlert = """
            Zasięg ostrzeżeń w województwie
            WOJEWÓDZTWO DOLNOŚLĄSKIE
            OSTRZEŻENIA METEOROLOGICZNE ZBIORCZO NR 82
            WYKAZ OBOWIĄZUJĄCYCH OSTRZEŻEŃ
            o godz. 12:42 dnia 18.06.2022
            Zjawisko/Stopień zagrożenia Upał z deszczem/2 ZMIANA
            Obszar (w nawiasie numer
            ostrzeżenia dla powiatu)
            powiaty: milicki(33), oleśnicki(35)
            Ważność od godz. 12:36 dnia 18.06.2022 do godz. 20:00 dnia 19.06.2022
            Prawdopodobieństwo 80%
            Przebieg Prognozuje się upały. Temperatura maksymalna w dzień od 30°C do 34°C. Temperatura
            minimalna w nocy od 16°C do 18°C.
            SMS IMGW-PIB OSTRZEGA: UPAŁ/2 dolnośląskie (2 powiaty) od 12:36/18.06 do 20:00/19.06.2022
            temp. maks 34 st, temp min 18 st. Dotyczy powiatów: milicki i oleśnicki.
            RSO Woj. dolnośląskie (2 powiaty), IMGW-PIB wydał ostrzeżenie drugiego stopnia o upałach
            Uwagi Brak.
            Zjawisko/Stopień zagrożenia Upał/2 ZMIANA
            Obszar (w nawiasie numer
            ostrzeżenia dla powiatu)
            powiaty: oławski(36), strzeliński(36), ząbkowicki(46)
            Ważność od godz. 12:36 dnia 18.06.2022 do godz. 20:00 dnia 19.06.2022
            Prawdopodobieństwo 80%
            Instytut Meteorologii i Gospodarki Wodnej - Państwowy Instytut Badawczy
            Centralne Biuro Prognoz Meteorologicznych - Wydział we Wrocławiu
            51-616 Wrocław ul. Parkowa 30
            tel: 071-320-01-50, fax: 071-348-73-37
            email: meteo.wroclaw@imgw.pl
            www: www.imgw.pl
            strona 1 z 3
            Przebieg Prognozuje się upały. Temperatura maksymalna w dzień od 30°C do 33°C, lokalnie w niedzielę
            do 34°C. Temperatura minimalna w nocy od 15°C do 18°C.
            SMS IMGW-PIB OSTRZEGA: UPAŁ/2 dolnośląskie (3 powiaty) od 12:36/18.06 do 20:00/19.06.2022
            temp. maks 34 st, temp min 18 st. Dotyczy powiatów: oławski, strzeliński i ząbkowicki.
            RSO Woj. dolnośląskie (3 powiaty), IMGW-PIB wydał ostrzeżenie drugiego stopnia o upałach
            Uwagi Brak.
            Zjawisko/Stopień zagrożenia Upał/1
            Obszar (w nawiasie numer
            ostrzeżenia dla powiatu)
            powiaty: Jelenia Góra(56), kamiennogórski(55), karkonoski(56), Wałbrzych(46), wałbrzyski(53)
            Ważność od godz. 14:00 dnia 18.06.2022 do godz. 20:00 dnia 19.06.2022
            Prawdopodobieństwo 80%
            Przebieg Prognozuje się upał. Temperatura maksymalna w dzień od 30°C do 32°C. Temperatura
            minimalna w nocy od 14°C do 17°C.
            SMS IMGW-PIB OSTRZEGA: UPAŁ/1 dolnośląskie (5 powiatów) od 14:00/18.06 do
            20:00/19.06.2022 temp. maks 32 st, temp min 17 st. Dotyczy powiatów: Jelenia Góra,
            kamiennogórski, karkonoski, Wałbrzych i wałbrzyski.
            RSO Woj. dolnośląskie (5 powiatów), IMGW-PIB wydał ostrzeżenie pierwszego stopnia o upałach
            Uwagi Brak.
            Zjawisko/Stopień zagrożenia Upał/2
            Obszar (w nawiasie numer
            ostrzeżenia dla powiatu)
            powiaty: bolesławiecki(36), dzierżoniowski(40), głogowski(33), górowski(34), jaworski(39),
            Legnica(37), legnicki(37), lubański(50), lubiński(36), lwówecki(51), polkowicki(35), średzki(35),
            świdnicki(38), trzebnicki(34), wołowski(34), Wrocław(36), wrocławski(36), zgorzelecki(37),
            złotoryjski(38)
            Ważność od godz. 12:00 dnia 18.06.2022 do godz. 20:00 dnia 19.06.2022
            Prawdopodobieństwo 80%
            Przebieg Prognozuje się upały. Temperatura maksymalna w dzień od 30°C do 33°C, lokalnie w niedzielę
            do 34°C. Temperatura minimalna w nocy od 18°C do 20°C.
            SMS IMGW-PIB OSTRZEGA: UPAŁ/2 dolnośląskie (19 powiatów) od 12:00/18.06 do
            20:00/19.06.2022 temp. maks 30-34 st, temp min 18-20 st. Dotyczy powiatów: bolesławiecki,
            dzierżoniowski, głogowski, górowski, jaworski, Legnica, legnicki, lubański, lubiński, lwówecki,
            polkowicki, średzki, świdnicki, trzebnicki, wołowski, Wrocław, wrocławski, zgorzelecki
            i złotoryjski.
            RSO Woj. dolnośląskie (19 powiatów), IMGW-PIB wydał ostrzeżenie drugiego stopnia o upałach
            Uwagi Ostrzeżenie może być kontynuowane.
            Instytut Meteorologii i Gospodarki Wodnej - Państwowy Instytut Badawczy
            Centralne Biuro Prognoz Meteorologicznych - Wydział we Wrocławiu
            51-616 Wrocław ul. Parkowa 30
            tel: 071-320-01-50, fax: 071-348-73-37
            email: meteo.wroclaw@imgw.pl
            www: www.imgw.pl
            strona 2 z 3
              Opracowanie niniejsze i jego format, jako przedmiot prawa autorskiego podlega ochronie prawnej, zgodnie z przepisami ustawy
            z dnia 4 lutego 1994r o prawie autorskim i prawach pokrewnych (dz. U. z 2006 r. Nr 90, poz. 631 z późn. zm.).
              Wszelkie dalsze udostępnianie, rozpowszechnianie (przedruk, kopiowanie, wiadomość sms) jest dozwolone wyłącznie w formie
            dosłownej z bezwzględnym wskazaniem źródła informacji tj. IMGW-PIB.
            Instytut Meteorologii i Gospodarki Wodnej - Państwowy Instytut Badawczy
            Centralne Biuro Prognoz Meteorologicznych - Wydział we Wrocławiu
            51-616 Wrocław ul. Parkowa 30
            tel: 071-320-01-50, fax: 071-348-73-37
            email: meteo.wroclaw@imgw.pl
            www: www.imgw.pl
            strona 3 z 3
            """;

    @Test
    void checkingModelMapping() {
        ImgwAlertMapper imgwAlertMapper = new ImgwAlertMapper();
        ImgwMeteoWarning imgwMeteoWarning = imgwAlertMapper.toModel(sampleAlert);
        assertFalse(imgwMeteoWarning.localMeteoWarnings().isEmpty());
        assertTrue(imgwMeteoWarning.number()>0);
        assertEquals(Voivodeship.DS, imgwMeteoWarning.voivodeship());
        assertEquals("2022-06-18T12:42", imgwMeteoWarning.published().toString());

    }
}