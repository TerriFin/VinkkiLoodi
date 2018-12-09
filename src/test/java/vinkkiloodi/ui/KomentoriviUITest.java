/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.ui;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import vinkkiloodi.database.InMemoryDAO;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.domain.Vinkki;
import vinkkiloodi.io.StubIO;

/**
 *
 * @author samisaukkonen
 */
public class KomentoriviUITest {

    private VinkkiDAO dao;
    private List<String> input;
    private KomentoriviUI ui;

    @Before
    public void setUp() {
        dao = new InMemoryDAO();
        input = new ArrayList<>();
    }

    private void lisaaTestiKirja(String kirjoittaja, String otsikko, String ISBN) {
        input.add("1");
        input.add("1");
        input.add(kirjoittaja);
        input.add(otsikko);
        input.add(ISBN);
    }

    private void lisaaTestiBlogi(String kirjoittaja, String otsikko, String url) {
        input.add("1");
        input.add("2");
        input.add(kirjoittaja);
        input.add(otsikko);
        input.add(url);
    }

    private void lisaaTestiArtikkeli(String kirjoittaja, String otsikko, String julkaisija) {
        input.add("1");
        input.add("3");
        input.add(kirjoittaja);
        input.add(otsikko);
        input.add(julkaisija);

    }

    private void paivitaTestiKirja(String haku, String otsikko, String kirjoittaja, String tarkastettu, String ISBN) {
        input.add("5");
        input.add(haku);
        input.add(otsikko);
        input.add(kirjoittaja);
        input.add(tarkastettu);
        input.add(ISBN);
    }

    private void paivitaTestiBlogi(String haku, String otsikko, String kirjoittaja, String tarkastettu, String url) {
        input.add("5");
        input.add(haku);
        input.add(otsikko);
        input.add(kirjoittaja);
        input.add(tarkastettu);
        input.add(url);
    }

    private void paivitaTestiArtikkeli(String haku, String otsikko, String kirjoittaja, String tarkastettu, String julkaisija) {
        input.add("5");
        input.add(haku);
        input.add(otsikko);
        input.add(kirjoittaja);
        input.add(tarkastettu);
        input.add(julkaisija);

    }

    private void listaaVinkit() {
        input.add("2");
    }

    private void syotaX() {
        input.add("x");
    }

    private void aloitaOhjelma() {
        input.add("x");
        ui = new KomentoriviUI(new StubIO(input), dao);
        ui.start();
    }

    private List<String> aloitaOhjelmaJaPalautaTulostus() {
        input.add("x");
        StubIO stubIO = new StubIO(input);
        ui = new KomentoriviUI(stubIO, dao);
        ui.start();
        return stubIO.getOutput();
    }

    @Test
    public void voiLisataKirjaVinkin() {
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");

        int vinkkejaEnnen = dao.getAll().size();

        aloitaOhjelma();

        assertTrue(vinkkejaEnnen < dao.getAll().size());
    }

    @Test
    public void voiLisataBlogiVinkin() {
        lisaaTestiBlogi("kirjoittaja", "blogiposti", "https://olematontestiurl.ei");

        int vinkkejaEnnen = dao.getAll().size();

        aloitaOhjelma();

        assertTrue(vinkkejaEnnen < dao.getAll().size());
    }

    @Test
    public void voiLisataArtikkeliVinkin() {
        lisaaTestiArtikkeli("kirjoittaja", "artikkeli", "testiLehti");

        int vinkkejaEnnen = dao.getAll().size();

        aloitaOhjelma();

        assertTrue(vinkkejaEnnen < dao.getAll().size());
    }

    @Test
    public void voiListataVinkit() {
        lisaaTestiArtikkeli("kirjoittaja", "artikkeli", "testiLehti");
        listaaVinkit();
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "Tyyppi: Artikkeli\nTekijä: kirjoittaja\nNimi: artikkeli\nTarkastettu: Ei\nJulkaisija: testiLehti\n");
    }

    @Test
    public void voiPaivittaaArtikkeliVinkin() {
        lisaaTestiArtikkeli("kirjoittaja", "artikkeli", "testiLehti");
        paivitaTestiArtikkeli("artikkeli", "uusiArtikkeli", "uusiKirjoittaja", "k", "uusiLehti");

        aloitaOhjelma();
        List<Vinkki> vanhat = dao.getByNimi("artikkeli");
        List<Vinkki> uudet = dao.getByNimi("uusiArtikkeli");
        assertEquals(vanhat.size(), 0);
        assertEquals(uudet.size(), 1);
    }
    
    @Test
    public void voiPaivittaaArtikkeliVinkinMontaKertaa() {
        lisaaTestiArtikkeli("kirjoittaja", "artikkeli", "testiLehti");
        paivitaTestiArtikkeli("artikkeli", "uusiArtikkeli", "uusiKirjoittaja", "k", "uusiLehti");
        paivitaTestiArtikkeli("uusiArtikkeli", "uusiArtikkel2", "uusiKirjoittaja", "k", "uusiLehti");
        
        aloitaOhjelma();
        
        List<Vinkki> vanhat = dao.getByNimi("artikkeli");
        List<Vinkki> uudet = dao.getByNimi("uusiArtikkeli");
        List<Vinkki> uudet2 = dao.getByNimi("uusiArtikkel2");
        
        assertEquals(vanhat.size(), 0);
        assertEquals(uudet.size(), 0);
        assertEquals(uudet2.size(), 1);
    }

    @Test
    public void voiPaivittaaBlogiVinkin() {
        lisaaTestiBlogi("kirjoittaja", "blogiposti", "https://olematontestiurl.ei");
        paivitaTestiBlogi("blogiposti", "uusiBlogi", "uusiKirjoittaja", "e", "uusiUrl");

        aloitaOhjelma();
        List<Vinkki> vanhat = dao.getByNimi("blogiposti");
        List<Vinkki> uudet = dao.getByNimi("uusiBlogi");
        assertEquals(vanhat.size(), 0);
        assertEquals(uudet.size(), 1);
    }

    @Test
    public void voiPaivittaaKirjaVinkin() {
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        paivitaTestiKirja("kirja", "uusiKirja", "uusiKirjoittaja", "k", "987653");

        aloitaOhjelma();
        List<Vinkki> vanhat = dao.getByNimi("kirja");
        List<Vinkki> uudet = dao.getByNimi("uusiKirja");
        assertEquals(vanhat.size(), 0);
        assertEquals(uudet.size(), 1);
    }
    
    @Test
    public void voiPaivittaaKirjaVinkinPikakomennolla() {
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        input.add("pv kirja");
        input.add("uusiKirja");
        input.add("uusiKirjoittaja");
        input.add("k");
        input.add("987653");

        aloitaOhjelma();
        List<Vinkki> vanhat = dao.getByNimi("kirja");
        List<Vinkki> uudet = dao.getByNimi("uusiKirja");
        assertEquals(vanhat.size(), 0);
        assertEquals(uudet.size(), 1);
    }

    @Test
    public void eiPaivitaVinkkiaJosEiLoydy() {
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        paivitaTestiKirja("vaarahakusana", "uusiKirja", "uusiKirjoittaja", "k", "987653");

        aloitaOhjelma();
        List<Vinkki> vanhat = dao.getByNimi("kirja");
        List<Vinkki> uudet = dao.getByNimi("uusiKirja");
        assertEquals(vanhat.size(), 1);
        assertEquals(uudet.size(), 0);
    }

    @Test
    public void voiPoistuaLisaaValikosta() {

        int vinkkejaEnnen = dao.getAll().size();

        input.add("1");
        input.add("x");
        input.add("kirjoittaja");
        input.add("kirja");
        input.add("12345");

        aloitaOhjelma();

        assertTrue(vinkkejaEnnen == dao.getAll().size());
    }

    @Test
    public void voiLisataKirjojaPikakomennolla() {

        int vinkkejaEnnen = dao.getAll().size();

        input.add("lk");
        input.add("kirjoittaja");
        input.add("kirja");
        input.add("12345");

        aloitaOhjelma();

        assertTrue(vinkkejaEnnen < dao.getAll().size());
    }

    @Test
    public void voiLisataArtikkeleitaPikakomennolla() {

        int vinkkejaEnnen = dao.getAll().size();

        input.add("la");
        input.add("kirjoittaja");
        input.add("artikkeli");
        input.add("julkaisija1");

        aloitaOhjelma();

        assertTrue(vinkkejaEnnen < dao.getAll().size());
    }

    @Test
    public void voiLisataBlogejaPikakomennolla() {

        int vinkkejaEnnen = dao.getAll().size();

        input.add("lb");
        input.add("bloggaaja");
        input.add("blogi");
        input.add("www.blogi.fi");

        aloitaOhjelma();

        assertTrue(vinkkejaEnnen < dao.getAll().size());
    }

    @Test
    public void vaihtaminenParserinJaKomentoriviIOValillaToimii() {
        input.add("par");
        input.add("x");
        List<String> output = aloitaOhjelmaJaPalautaTulostus();

        boolean kaynnistaaParserin = false;
        boolean palaaKomentoriviIO = false;
        int index = 0;

        while (index < output.size()) {
            if (output.get(index).startsWith("\nTervetuloa käyttämään vinkkiloodi parseria!")) {
                kaynnistaaParserin = true;
                break;
            }
            index++;
        }

        while (index < output.size()) {
            if (output.get(index).startsWith("\nMitä haluat tehdä?")) {
                palaaKomentoriviIO = true;
                break;
            }
            index++;
        }

        assertTrue(kaynnistaaParserin && palaaKomentoriviIO);
    }

    @Test
    public void voiListataTarkastamattomat() {
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        paivitaTestiArtikkeli("artikkeli", "", "", "k", "");
        input.add("4");
        input.add("6");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Kirja\nTekijä:"
                + " kirjoittaja\nNimi: kirja\nTarkastettu: Ei\nISBN: 12345\n");
    }
    
    @Test
    public void voiListataTarkastamattomatPikakomennolla() {
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        input.add("et");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Kirja\nTekijä:"
                + " kirjoittaja\nNimi: kirja\nTarkastettu: Ei\nISBN: 12345\n");
    }
    
    @Test
    public void voiListataTarkastetut() {
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        paivitaTestiArtikkeli("artikkeli", "", "", "k", "");
        input.add("4");
        input.add("7");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Artikkeli\nTekijä:"
                + " toimittaja\nNimi: artikkeli\nTarkastettu: Kyllä\nJulkaisija: lehti\n");
    }
    
    @Test
    public void voiListataTarkastetutPikakomennolla() {
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        paivitaTestiArtikkeli("artikkeli", "", "", "k", "");
        input.add("kt");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Artikkeli\nTekijä:"
                + " toimittaja\nNimi: artikkeli\nTarkastettu: Kyllä\nJulkaisija: lehti\n");
    }
    
    @Test
    public void tulostaaKaikkiKirjatOikein() {
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        lisaaTestiKirja("2kirjoittaja", "2kirja", "212345");
        input.add("4");
        input.add("1");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-3), "\nTyyppi: Kirja\nTekijä:"
                + " kirjoittaja\nNimi: kirja\nTarkastettu: Ei\nISBN: 12345\n");
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Kirja\nTekijä:"
                + " 2kirjoittaja\nNimi: 2kirja\nTarkastettu: Ei\nISBN: 212345\n");
    }
    
    @Test
    public void tulostaaKaikkiBlogitOikein() {
        lisaaTestiBlogi("kirjoittaja", "blogiposti", "https://olematontestiurl.ei");
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        lisaaTestiBlogi("2kirjoittaja", "2blogiposti", "https://2olematontestiurl.ei");
        input.add("4");
        input.add("2");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-3), "\nTyyppi: Blog\nTekijä:"
                + " kirjoittaja\nNimi: blogiposti\nTarkastettu: Ei\nURL: https://olematontestiurl.ei\n");
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Blog\nTekijä:"
                + " 2kirjoittaja\nNimi: 2blogiposti\nTarkastettu: Ei\nURL: https://2olematontestiurl.ei\n");
    }
    
    @Test
    public void tulostaaKaikkiArtikkelitOikein() {
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("2toimittaja", "2artikkeli", "2lehti");
        input.add("4");
        input.add("3");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-3), "\nTyyppi: Artikkeli\nTekijä:"
                + " toimittaja\nNimi: artikkeli\nTarkastettu: Ei\nJulkaisija: lehti\n");
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Artikkeli\nTekijä:"
                + " 2toimittaja\nNimi: 2artikkeli\nTarkastettu: Ei\nJulkaisija: 2lehti\n");
    }

    @Test
    public void nopeaHakuToimii() {
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("2toimittaja", "2artikkeli", "2lehti");
        input.add("3");
        input.add("kirja");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Kirja\nTekijä:"
                + " kirjoittaja\nNimi: kirja\nTarkastettu: Ei\nISBN: 12345\n");
    }
    
    @Test
    public void nopeaHakuToimiiPikakomennolla() {
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("2toimittaja", "2artikkeli", "2lehti");
        input.add("s kirja");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Kirja\nTekijä:"
                + " kirjoittaja\nNimi: kirja\nTarkastettu: Ei\nISBN: 12345\n");
    }
    
    @Test
    public void hakuTekijallaToimii() {
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("2toimittaja", "2artikkeli", "2lehti");
        input.add("4");
        input.add("4");
        input.add("kirjoit");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Kirja\nTekijä:"
                + " kirjoittaja\nNimi: kirja\nTarkastettu: Ei\nISBN: 12345\n");
    }
    
    @Test
    public void hakuVaarallaTekijallaAntaaVirheilmoituksen() {
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("2toimittaja", "2artikkeli", "2lehti");
        input.add("4");
        input.add("4");
        input.add("xdz");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "Vinkkejä ei löytynyt!");
    }
    
    @Test
    public void hakuTekijallaToimiiPikakomennolla() {
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("2toimittaja", "2artikkeli", "2lehti");
        input.add("st kirjoit");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Kirja\nTekijä:"
                + " kirjoittaja\nNimi: kirja\nTarkastettu: Ei\nISBN: 12345\n");
    }
    
    @Test
    public void hakuOtsikollaToimii() {
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("2toimittaja", "2artikkeli", "2lehti");
        input.add("4");
        input.add("5");
        input.add("kirj");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Kirja\nTekijä:"
                + " kirjoittaja\nNimi: kirja\nTarkastettu: Ei\nISBN: 12345\n");
    }
    
    @Test
    public void hakuVaarallaOtsikollaAntaaVirheilmoituksen() {
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("2toimittaja", "2artikkeli", "2lehti");
        input.add("4");
        input.add("5");
        input.add("xdz");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "Vinkkejä ei löytynyt!");
    }
    
    @Test
    public void hakuOtsikollaToimiiPikakomennolla() {
        lisaaTestiArtikkeli("toimittaja", "artikkeli", "lehti");
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");
        lisaaTestiArtikkeli("2toimittaja", "2artikkeli", "2lehti");
        input.add("so kirj");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "\nTyyppi: Kirja\nTekijä:"
                + " kirjoittaja\nNimi: kirja\nTarkastettu: Ei\nISBN: 12345\n");
    }
    
    @Test
    public void listaaPikakomennotOikein() {
        input.add("6");
        List<String> tuloste = aloitaOhjelmaJaPalautaTulostus();
        assertEquals(tuloste.get(tuloste.size()-2), "\nPikakomennot (syötä ilman hipsuja)\n-----------\n"
                + "Lisää vinkkejä:\n"
                + "\'lk\' = lisää kirjavinkki\n"
                + "\'la\' = lisää artikkelivinkki\n"
                + "\'lb\' = lisää blogivinkki\n"
                + "Päivitä vinkkejä:\n"
                + "\'pv\' + hakusana = päivitä vinkki otsikolla\n"
                + "Hae vinkkejä:\n"
                + "\'s\' + hakusana = hae vinkkejä hakusanalla\n"
                + "\'so\' + hakusana = hae vinkkejä otsikolla\n"
                + "\'st\' + hakusana = hae vinkkejä tekijällä\n"
                + "\'et\' = listaa tarkastamattomat vinkit (et = \"ei-tarkastetut\")\n"
                + "\'kt\' = listaa tarkastetut vinkit (kt = \"kyllä-tarkastetut\")\n");
    }
}
