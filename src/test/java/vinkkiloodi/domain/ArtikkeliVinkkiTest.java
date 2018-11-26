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
public class ArtikkeliVinkkiTest {

    private ArtikkeliVinkki artikkeli;
    private String tekija;
    private String nimi;
    private int tarkastettu;
    private String julkaisija;
    private List<String> tagit;
    private List<String> esitietokurssit;
    private List<String> liittyvatKurssit;

    @Before
    public void setUp() {
        tekija = "Aatu Artikkelintekija";
        nimi = "Hienompi Artikkeli";
        tarkastettu = 0;
        julkaisija = "julkaisija";

        tagit = new ArrayList<>();
        esitietokurssit = new ArrayList<>();
        liittyvatKurssit = new ArrayList<>();

        tagit.add("tag1");
        tagit.add("tag2");
        esitietokurssit.add("Keksitty kurssi");
        liittyvatKurssit.add("kurssi1");
        liittyvatKurssit.add("Toinen kurssi");

        artikkeli = new ArtikkeliVinkki(tekija, nimi, julkaisija, tarkastettu);
    }

    @Test
    public void KonstruktoriLuoTekijalla() {
        assertEquals(tekija, artikkeli.getTekija());
    }

    @Test
    public void KonstruktoriLuoOikeallaTyypilla() {
        assertEquals(Tyyppi.Artikkeli, artikkeli.getTyyppi());
    }

    @Test
    public void KonstruktoriLuoOtsikolla() {
        assertEquals(nimi, artikkeli.getNimi());
    }

    @Test
    public void KonstruktoriLuoUrlilla() {
        assertEquals(julkaisija, artikkeli.getJulkaisija());
    }

    @Test
    public void UrlVoidaanPaivittaa() {
        artikkeli.setJulkaisija("uusi julkaisija");

        assertEquals("uusi julkaisija", artikkeli.getJulkaisija());
    }

    @Test
    public void kaikkienTulostusHalutunMuotoinen() {
        String haluttu = "Tyyppi: Artikkeli\n"
                + "Tekijä: " + tekija + "\n"
                + "Nimi: " + nimi + "\n"
                + "Tarkastettu: Ei\n"
                + "Tagit: " + tagit.get(0) + ", " + tagit.get(1) + "\n"
                + "Esitietokurssit: " + esitietokurssit.get(0) + "\n"
                + "Liittyvät kurssit: " + liittyvatKurssit.get(0) + ", " + liittyvatKurssit.get(1) + "\n"
                + "Julkaisija: " + julkaisija + "\n";

        artikkeli.setTagit(tagit);
        artikkeli.setEsitietokurssit(esitietokurssit);
        artikkeli.setLiittyvatKurssit(liittyvatKurssit);

        assertEquals(haluttu, artikkeli.toString());
    }

    @Test
    public void tulostuksessaEiTulostetaTyhjiaJaArtikkeliOnLuettu() {
        String haluttu = "Tyyppi: Artikkeli\n"
                + "Tekijä: " + tekija + "\n"
                + "Nimi: " + nimi + "\n"
                + "Tarkastettu: Kyllä\n"
                + "Esitietokurssit: " + esitietokurssit.get(0) + "\n"
                + "Liittyvät kurssit: " + liittyvatKurssit.get(0) + ", " + liittyvatKurssit.get(1) + "\n"
                + "Julkaisija: " + julkaisija + "\n";

        artikkeli.setEsitietokurssit(esitietokurssit);
        artikkeli.setLiittyvatKurssit(liittyvatKurssit);
        artikkeli.setTarkastettu(1);

        assertEquals(haluttu, artikkeli.toString());
    }
}
