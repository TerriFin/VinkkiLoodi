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

    private void lopetaOhjelma() {
        input.add("x");
    }

    private void aloitaOhjelma() {
        input.add("x");
        ui = new KomentoriviUI(new StubIO(input), dao);
        ui.start();
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
        lopetaOhjelma();

        aloitaOhjelma();
    }
    
    @Test
    public void voiPaivittaaArtikkeliVinkin() {
        lisaaTestiArtikkeli("kirjoittaja", "artikkeli", "testiLehti");
        paivitaTestiArtikkeli("artikkeli", "uusiArtikkeli", "uusiKirjoittaja", "k", "uusiLehti");
        
        aloitaOhjelma();
        List<Vinkki> vanhat = dao.getByNimi("artikkeli");
        List<Vinkki> uudet = dao.getByNimi("uusiArtikkeli");
        assertEquals(vanhat.size(), 1);
        assertEquals(uudet.size(), 0);
    }
    
    @Test
    public void voiPaivittaaBlogiVinkin() {
        lisaaTestiBlogi("kirjoittaja", "blogiposti", "https://olematontestiurl.ei");
        paivitaTestiBlogi("blogiposti", "uusiBlogi", "uusiKirjoittaja", "e", "uusiUrl");
        
        aloitaOhjelma();
        List<Vinkki> vanhat = dao.getByNimi("blogiposti");
        List<Vinkki> uudet = dao.getByNimi("uusiBlogi");
        assertEquals(vanhat.size(), 1);
        assertEquals(uudet.size(), 0);
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
}
