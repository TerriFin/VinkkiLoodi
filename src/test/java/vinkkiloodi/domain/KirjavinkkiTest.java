package vinkkiloodi.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mina
 */
public class KirjavinkkiTest {

    private Kirjavinkki kirjaPieniKonstruktori;
    private Kirjavinkki kirjaSuuriKonstruktori;
    private String kirjoittaja;
    private String otsikko;
    private int luettu;
    private String isbn;

    @Before
    public void setUp() {
        kirjoittaja = "Matt Mattinson";
        otsikko = "Great Book";
        luettu = 1;
        isbn = "978-951-98548-9-2";
        

        kirjaPieniKonstruktori = new Kirjavinkki(kirjoittaja, otsikko);
        kirjaSuuriKonstruktori = new Kirjavinkki(kirjoittaja, otsikko, luettu, isbn);

    }

    @Test
    public void PienempiKonstruktoriLuoKirjanKirjoittajalla() {
        assertEquals(kirjoittaja, kirjaPieniKonstruktori.getKirjoittaja());
    }

    @Test
    public void PienempiKonstruktoriLuoOtsikolla() {
        assertEquals(otsikko, kirjaPieniKonstruktori.getOtsikko());
    }

    @Test
    public void PienempiKonstruktoriLuoLukemattomanKirjan() {
        assertEquals(0, kirjaPieniKonstruktori.getLuettu());
    }

    @Test
    public void PienemiKonstruktoriTyhjanIsbn() {
        assertEquals("", kirjaPieniKonstruktori.getIsbn());
    }

    @Test
    public void SuurempiKonstruktoriLuoKirjanKIrjoittajalla() {
        assertEquals(kirjoittaja, kirjaSuuriKonstruktori.getKirjoittaja());

    }

    @Test
    public void SuuriKonstruktoriLuoOtsikolla() {
        assertEquals(otsikko, kirjaSuuriKonstruktori.getOtsikko());
    }

    @Test
    public void SuuriKonstruktoriLuoIsbn() {
        assertEquals(isbn, kirjaSuuriKonstruktori.getIsbn());
    }
    
    @Test
    public void SuuriKonstruktoriLuoLuetun() {
        assertEquals(luettu, kirjaSuuriKonstruktori.getLuettu());
    }

    @Test
    public void IsbnVoidaanPaivittaa() {
        kirjaPieniKonstruktori.setIsbn(isbn);
        assertEquals(isbn, kirjaPieniKonstruktori.getIsbn());
    }

}
