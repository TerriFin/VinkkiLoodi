package vinkkiloodi.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mina
 */
public class KirjaVinkkiTest {

    private KirjaVinkki kirjaPieniKonstruktori;
    private KirjaVinkki kirjaSuuriKonstruktori;
    private String kirjoittaja;
    private String otsikko;
    private int luettu;
    private String isbn;
    private List<String> tagit;
    private List<String> esitietokurssit;
    private List<String> liittyvatKurssit;

    @Before
    public void setUp() {
        kirjoittaja = "Matt Mattinson";
        otsikko = "Great Book";
        luettu = 1;
        isbn = "978-951-98548-9-2";
        tagit = new ArrayList<>();
        esitietokurssit = new ArrayList<>();
        liittyvatKurssit = new ArrayList<>();

        tagit.add("tag1");
        tagit.add("tag2");
        esitietokurssit.add("Keksitty kurssi");
        liittyvatKurssit.add("kurssi1");
        liittyvatKurssit.add("Toinen kurssi");

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
    public void KonstruktoriLuoOikeallaTyypilla() {
        assertEquals(Tyyppi.Kirja, kirjaPieniKonstruktori.getTyyppi());
    }

    @Test
    public void IsbnVoidaanPaivittaa() {
        kirjaPieniKonstruktori.setISBN(isbn);
        assertEquals(isbn, kirjaPieniKonstruktori.getISBN());
    }

    @Test
    public void kaikkienTulostusHalutunMuotoinen() {
        String haluttu = "Tyyppi: Kirja\n"
                + "Tekijä: " + kirjoittaja + "\n"
                + "Nimi: " + otsikko + "\n"
                + "Tarkastettu: Kyllä\n"
                + "Tagit: " + tagit.get(0) + ", " + tagit.get(1) + "\n"
                + "Esitietokurssit: " + esitietokurssit.get(0) + "\n"
                + "Liittyvät kurssit: " + liittyvatKurssit.get(0) + ", " + liittyvatKurssit.get(1) + "\n"
                + "ISBN: " + isbn + "\n";

        kirjaSuuriKonstruktori.setTagit(tagit);
        kirjaSuuriKonstruktori.setEsitietokurssit(esitietokurssit);
        kirjaSuuriKonstruktori.setLiittyvatKurssit(liittyvatKurssit);

        assertEquals(haluttu, kirjaSuuriKonstruktori.toString());
    }

}
