package com.thedariusz.pdfreaderdemo;

import com.thedariusz.pdfreaderdemo.model.ImgwMeteoAlert;
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
            Zjawisko/Stopień zagrożenia Upał/2
            Obszar (w nawiasie numer
            ostrzeżenia dla powiatu)
            powiaty: będziński(54), bieruńsko-lędziński(49), Bytom(50), Chorzów(49), Częstochowa(54),
            częstochowski(54), Dąbrowa Górnicza(54), Gliwice(50), gliwicki(51), Jastrzębie-Zdrój(50),
            Jaworzno(51), Katowice(49), kłobucki(53), lubliniecki(53), mikołowski(49), Mysłowice(50),
            myszkowski(53), Piekary Śląskie(51), pszczyński(50), raciborski(49), Ruda Śląska(49),
            rybnicki(49), Rybnik(49), Siemianowice Śląskie(50), Sosnowiec(52), Świętochłowice(49),
            tarnogórski(53), Tychy(49), wodzisławski(50), Zabrze(49), zawierciański(54), Żory(49)
            Instytut Meteorologii i Gospodarki Wodnej - Państwowy Instytut Badawczy
            Biuro Prognoz Meteorologicznych w Krakowie
            30-215 Kraków ul. Piotra Borowego 14
            tel: 12-6398150, fax: 12-4251973
            email: meteo.krakow@imgw.pl
            www: www.imgw.pl
            strona 1 z 2
            Ważność od godz. 12:00 dnia 25.06.2022 do godz. 20:00 dnia 28.06.2022
            Prawdopodobieństwo 80%
            Przebieg Prognozuje się upał. Temperatura maksymalna w dzień od 29°C do 32°C, w poniedziałek (27.06)
            i wtorek (28.06) miejscami do 34°C. Temperatura minimalna w nocy 25/26.06 od 13°C do 17°C,
            a w kolejne od 16°C do 20°C.
            SMS IMGW-PIB OSTRZEGA: UPAŁ/2 śląskie (32 powiatów) od 12:00/25.06 do 20:00/28.06.2022
            temp. maks 29-32st., lok.34st, temp min 17-20st.,lok.13-17st. Dotyczy powiatów: będziński,
            bieruńsko-lędziński, Bytom, Chorzów, Częstochowa, częstochowski, Dąbrowa Górnicza,
            Gliwice, gliwicki, Jastrzębie-Zdrój, Jaworzno, Katowice, kłobucki, lubliniecki, mikołowski,
            Mysłowice, myszkowski, Piekary Śląskie, pszczyński, raciborski, Ruda Śląska, rybnicki, Rybnik,
            Siemianowice Śląskie, Sosnowiec, Świętochłowice, tarnogórski, Tychy, wodzisławski, Zabrze,
            zawierciański i Żory.
            RSO Woj. śląskie (32 powiatów), IMGW-PIB wydał ostrzeżenie drugiego stopnia o upałach
            Uwagi Ostrzeżenie może być kontynuowane.
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

    private final static String sampleAlert2 = """
            Zasięg ostrzeżeń w województwie
            WOJEWÓDZTWO MAZOWIECKIE
            OSTRZEŻENIA METEOROLOGICZNE ZBIORCZO NR 125
            WYKAZ OBOWIĄZUJĄCYCH OSTRZEŻEŃ
            o godz. 05:42 dnia 02.07.2022
            Zjawisko/Stopień zagrożenia Burze z gradem/2 ODWOŁANIE
            Obszar (w nawiasie numer
            ostrzeżenia dla powiatu)
            powiaty: gostyniński(49), Płock(48), płocki(49), sierpecki(47)
            Czas odwołania godz. 05:15 dnia 02.07.2022
            Przyczyna Zjawisko zakończyło się
            SMS IMGW-PIB INFORMUJE: BURZE Z GRADEM/- mazowieckie (4 powiaty) 05:15/02.07.2022
            ZJAWISKO ZAKOŃCZYŁO SIĘ. Dotyczy powiatów: gostyniński, Płock, płocki i sierpecki.
            RSO Woj. mazowieckie (0 powiatów), IMGW-PIB odwołał ostrzeżenie drugiego stopnia o burzach
            z gradem
            Uwagi Brak
            Zjawisko/Stopień zagrożenia Burze z gradem/1
            Obszar (w nawiasie numer
            ostrzeżenia dla powiatu)
            powiaty: łosicki(51), ostrołęcki(52), Ostrołęka(51), ostrowski(54), Siedlce(53), siedlecki(53),
            sokołowski(56), węgrowski(55)
            Ważność od godz. 08:00 dnia 02.07.2022 do godz. 18:00 dnia 02.07.2022
            Prawdopodobieństwo 80%
            Przebieg Prognozowane są burze, którym miejscami będą towarzyszyć silne opady deszczu od 20 mm do
            30 mm oraz porywy wiatru do 85 km/h. Miejscami grad.
            Instytut Meteorologii i Gospodarki Wodnej - Państwowy Instytut Badawczy
            Centralne Biuro Prognoz Meteorologicznych - Wydział w Warszawie
            01-673 Warszawa ul. Podleśna 61
            tel: 22 5694151, kom. 781774153, fax: 022-5694151
            email: meteo.warszawa@imgw.pl
            www: www.imgw.pl
            strona 1 z 3
            SMS IMGW-PIB OSTRZEGA: BURZE Z GRADEM/1 mazowieckie (8 powiatów) od 08:00/02.07 do
            18:00/02.07.2022 deszcz 30 mm, grad, porywy 85 km/h. Dotyczy powiatów: łosicki, ostrołęcki,
            Ostrołęka, ostrowski, Siedlce, siedlecki, sokołowski i węgrowski.
            RSO Woj. mazowieckie (8 powiatów), IMGW-PIB wydał ostrzeżenie pierwszego stopnia o burzach
            z gradem
            Uwagi Brak.
            Zjawisko/Stopień zagrożenia Burze z gradem/1 ZMIANA
            Obszar (w nawiasie numer
            ostrzeżenia dla powiatu)
            powiaty: białobrzeski(55), grodziski(53), grójecki(55), lipski(55), płoński(46), przysuski(56),
            Radom(56), radomski(56), sochaczewski(46), szydłowiecki(57), zwoleński(56), żyrardowski(55)
            Ważność od godz. 04:52 dnia 02.07.2022 do godz. 08:00 dnia 02.07.2022
            Prawdopodobieństwo 75%
            Przebieg Prognozowane są burze, którym miejscami będą towarzyszyć silne opady deszczu do 25 mm oraz
            porywy wiatru do 70 km/h. Miejscami grad.
            SMS IMGW-PIB OSTRZEGA: BURZE Z GRADEM/1 mazowieckie (12 powiatów) od 04:52/02.07
            do 08:00/02.07.2022 deszcz 25 mm, grad, porywy 70 km/h. Dotyczy powiatów: białobrzeski,
            grodziski, grójecki, lipski, płoński, przysuski, Radom, radomski, sochaczewski, szydłowiecki,
            zwoleński i żyrardowski.
            RSO Woj. mazowieckie (12 powiatów), IMGW-PIB wydał ostrzeżenie pierwszego stopnia o burzach
            z gradem
            Uwagi Wydłużenie ważności, obniżenie stopnia zagrożenia.
            Zjawisko/Stopień zagrożenia Burze z gradem/2
            Obszar (w nawiasie numer
            ostrzeżenia dla powiatu)
            powiaty: ciechanowski(46), garwoliński(55), kozienicki(55), legionowski(53), łosicki(50),
            makowski(48), miński(53), mławski(47), nowodworski(52), ostrołęcki(51), Ostrołęka(50),
            ostrowski(53), otwocki(52), piaseczyński(52), pruszkowski(51), przasnyski(49), pułtuski(51),
            Siedlce(52), siedlecki(52), sokołowski(55), Warszawa(51), warszawski zachodni(50),
            węgrowski(54), wołomiński(54), wyszkowski(51), żuromiński(47)
            Ważność od godz. 21:00 dnia 01.07.2022 do godz. 08:00 dnia 02.07.2022
            Prawdopodobieństwo 75%
            Przebieg Prognozowane są burze, którym miejscami będą towarzyszyć silne opady deszczu do 30 mm,
            lokalnie do 45 mm, oraz porywy wiatru do 90 km/h, lokalnie do 100 km/h. Miejscami grad.
            SMS IMGW-PIB OSTRZEGA: BURZE Z GRADEM/2 mazowieckie (26 powiatów) od 21:00/01.07
            do 08:00/02.07.2022 deszcz 45 mm, grad, porywy 90-100 km/h. Dotyczy powiatów:
            ciechanowski, garwoliński, kozienicki, legionowski, łosicki, makowski, miński, mławski,
            nowodworski, ostrołęcki, Ostrołęka, ostrowski, otwocki, piaseczyński, pruszkowski, przasnyski,
            pułtuski, Siedlce, siedlecki, sokołowski, Warszawa, warszawski zachodni, węgrowski,
            wołomiński, wyszkowski i żuromiński.
            Instytut Meteorologii i Gospodarki Wodnej - Państwowy Instytut Badawczy
            Centralne Biuro Prognoz Meteorologicznych - Wydział w Warszawie
            01-673 Warszawa ul. Podleśna 61
            tel: 22 5694151, kom. 781774153, fax: 022-5694151
            email: meteo.warszawa@imgw.pl
            www: www.imgw.pl
            strona 2 z 3
            RSO Woj. mazowieckie (26 powiatów), IMGW-PIB wydał ostrzeżenie drugiego stopnia o burzach
            z gradem
            Uwagi Brak.
              Opracowanie niniejsze i jego format, jako przedmiot prawa autorskiego podlega ochronie prawnej, zgodnie z przepisami ustawy
            z dnia 4 lutego 1994r o prawie autorskim i prawach pokrewnych (dz. U. z 2006 r. Nr 90, poz. 631 z późn. zm.).
              Wszelkie dalsze udostępnianie, rozpowszechnianie (przedruk, kopiowanie, wiadomość sms) jest dozwolone wyłącznie w formie
            dosłownej z bezwzględnym wskazaniem źródła informacji tj. IMGW-PIB.
            Instytut Meteorologii i Gospodarki Wodnej - Państwowy Instytut Badawczy
            Centralne Biuro Prognoz Meteorologicznych - Wydział w Warszawie
            01-673 Warszawa ul. Podleśna 61
            tel: 22 5694151, kom. 781774153, fax: 022-5694151
            email: meteo.warszawa@imgw.pl
            www: www.imgw.pl
            strona 3 z 3
            """;

    @Test
    void checkingModelMapping() {
        ImgwAlertMapper imgwAlertMapper = new ImgwAlertMapper();
        ImgwMeteoAlert imgwMeteoAlert = imgwAlertMapper.toModel(sampleAlert2);
        assertFalse(imgwMeteoAlert.localMeteoWarnings().isEmpty());
        assertTrue(imgwMeteoAlert.number() > 0);
        assertEquals(Voivodeship.DS, imgwMeteoAlert.voivodeship());
        assertEquals("2022-06-18T12:42", imgwMeteoAlert.published().toString());

    }
}