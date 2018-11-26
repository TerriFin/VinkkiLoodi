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
public class BlogiVinkkiTest {

    private BlogiVinkki blogi;
    private String tekija;
    private String nimi;
    private int luettu;
    private String url;
    private List<String> tagit;
    private List<String> esitietokurssit;
    private List<String> liittyvatKurssit;

    @Before
    public void setUp() {
        tekija = "Bob Blogittaja";
        nimi = "Hieno Blogi";
        luettu = 0;
        url = "testurl·eiole";
        tagit = new ArrayList<>();
        esitietokurssit = new ArrayList<>();
        liittyvatKurssit = new ArrayList<>();

        tagit.add("tag1");
        tagit.add("tag2");
        esitietokurssit.add("Keksitty kurssi");
        liittyvatKurssit.add("kurssi1");
        liittyvatKurssit.add("Toinen kurssi");

        blogi = new BlogiVinkki(tekija, nimi, url, luettu);
    }

    @Test
    public void KonstruktoriLuoTekijalla() {
        assertEquals(tekija, blogi.getTekija());
    }

    @Test
    public void KonstruktoriLuoOtsikolla() {
        assertEquals(nimi, blogi.getNimi());
    }

    @Test
    public void KonstruktoriLuoOikeallaTyypilla() {
        assertEquals(Tyyppi.Blog, blogi.getTyyppi());
    }

    @Test
    public void KonstruktoriLuoUrlilla() {
        assertEquals(url, blogi.getUrl());
    }

    @Test
    public void UrlVoidaanPaivittaa() {
        blogi.setUrl("uusiurl.ei");

        assertEquals("uusiurl.ei", blogi.getUrl());
    }

    @Test
    public void kaikkienTulostusHalutunMuotoinen() {
        String haluttu = "Tyyppi: Blog\n"
                + "Tekijä: " + tekija + "\n"
                + "Nimi: " + nimi + "\n"
                + "Tarkastettu: Ei\n"
                + "Tagit: " + tagit.get(0) + ", " + tagit.get(1) + "\n"
                + "Esitietokurssit: " + esitietokurssit.get(0) + "\n"
                + "Liittyvät kurssit: " + liittyvatKurssit.get(0) + ", " + liittyvatKurssit.get(1) + "\n"
                + "URL: " + url + "\n";

        blogi.setTagit(tagit);
        blogi.setEsitietokurssit(esitietokurssit);
        blogi.setLiittyvatKurssit(liittyvatKurssit);

        assertEquals(haluttu, blogi.toString());
    }

    @Test
    public void tulostuksessaEiTulostetaTyhjia() {
        String haluttu = "Tyyppi: Blog\n"
                + "Tekijä: " + tekija + "\n"
                + "Nimi: " + nimi + "\n"
                + "Tarkastettu: Ei\n"
                + "Tagit: " + tagit.get(0) + ", " + tagit.get(1) + "\n"
                + "Esitietokurssit: " + esitietokurssit.get(0) + "\n"
                + "URL: " + url + "\n";

        blogi.setTagit(tagit);
        blogi.setEsitietokurssit(esitietokurssit);

        assertEquals(haluttu, blogi.toString());
    }
}
