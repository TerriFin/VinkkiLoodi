package vinkkiloodi.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mina
 */
public class KirjavinkkiTest {

    private KirjaVinkki kirjaPieniKonstruktori;
    private KirjaVinkki kirjaSuuriKonstruktori;
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
        

        kirjaPieniKonstruktori = new KirjaVinkki(kirjoittaja, otsikko);
        kirjaSuuriKonstruktori = new KirjaVinkki(kirjoittaja, otsikko, luettu, isbn);

    }

    @Test
    public void PienempiKonstruktoriLuoKirjanKirjoittajalla() {
        assertEquals(kirjoittaja, kirjaPieniKonstruktori.getTekija());
    }

    @Test
    public void PienempiKonstruktoriLuoOtsikolla() {
        assertEquals(otsikko, kirjaPieniKonstruktori.getNimi());
    }

    @Test
    public void PienempiKonstruktoriLuoLukemattomanKirjan() {
        assertEquals(0, kirjaPieniKonstruktori.getTarkastettu());
    }

    @Test
    public void PienemiKonstruktoriTyhjanIsbn() {
        assertEquals("", kirjaPieniKonstruktori.getISBN());
    }

    @Test
    public void SuurempiKonstruktoriLuoKirjanKIrjoittajalla() {
        assertEquals(kirjoittaja, kirjaSuuriKonstruktori.getTekija());

    }

    @Test
    public void SuuriKonstruktoriLuoOtsikolla() {
        assertEquals(otsikko, kirjaSuuriKonstruktori.getNimi());
    }

    @Test
    public void SuuriKonstruktoriLuoIsbn() {
        assertEquals(isbn, kirjaSuuriKonstruktori.getISBN());
    }
    
    @Test
    public void SuuriKonstruktoriLuoLuetun() {
        assertEquals(luettu, kirjaSuuriKonstruktori.getTarkastettu());
    }

    @Test
    public void IsbnVoidaanPaivittaa() {
        kirjaPieniKonstruktori.setISBN(isbn);
        assertEquals(isbn, kirjaPieniKonstruktori.getISBN());
    }

}
