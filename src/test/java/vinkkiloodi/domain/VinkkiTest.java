package vinkkiloodi.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JoonaHa
 */
public class VinkkiTest {

    private Vinkki kirjaPieniKonstruktori;
    private String tekija;
    private String nimi;
    private int tarkastettu;

    private class VinkkiStubTest extends Vinkki {

        private VinkkiStubTest(String tekija, String nimi, int tarkastettu) {
            super(tekija, nimi, tarkastettu);

        }

    }

    @Before
    public void setUp() {
        tekija = "Vili Vinkkinen";
        nimi = "Hyvä vinkki";
        tarkastettu = 0;

    }

    @Test
    public void KonstruktoriLuoOikeilaParametreilla() {

        Vinkki vinkki = new VinkkiStubTest(tekija, nimi, tarkastettu);

        assertEquals(tekija, vinkki.getTekija());
        assertEquals(nimi, vinkki.getNimi());
        assertEquals(tarkastettu, vinkki.getTarkastettu());

    }

    @Test
    public void LiityvatkurssitVoidaanPaivittaa() {

        Vinkki vinkki = new VinkkiStubTest(tekija, nimi, tarkastettu);
        List<String> kurssit = new ArrayList<>();
        kurssit.add("ohpe");
        kurssit.add("ohja");
        kurssit.add("tira");
        vinkki.setLiittyvatKurssit(kurssit);

        assert (vinkki.getLiittyvatKurssit().contains("ohpe"));
        assert (vinkki.getLiittyvatKurssit().contains("ohja"));
        assert (vinkki.getLiittyvatKurssit().contains("tira"));

    }

    @Test
    public void EsitietokurssitVoidaanPaivittaa() {

        Vinkki vinkki = new VinkkiStubTest(tekija, nimi, tarkastettu);
        List<String> kurssit = new ArrayList<>();
        kurssit.add("lama");
        kurssit.add("linis1");
        kurssit.add("tira");
        vinkki.setEsitietokurssit(kurssit);

        assert (vinkki.getEsitietokurssit().contains("lama"));
        assert (vinkki.getEsitietokurssit().contains("linis1"));
        assert (vinkki.getEsitietokurssit().contains("tira"));

    }

    @Test
    public void LiittyviinkursseihinVoidaanLisata() {

        Vinkki vinkki = new VinkkiStubTest(tekija, nimi, tarkastettu);

        vinkki.addLiittyvakurssi("jolo");

        assert (vinkki.getLiittyvatKurssit().contains("jolo"));

    }

    @Test
    public void EsitietokursseihinVoidaanLisata() {

        Vinkki vinkki = new VinkkiStubTest(tekija, nimi, tarkastettu);

        vinkki.addEsitietokurssi("jolo");

        assert (vinkki.getEsitietokurssit().contains("jolo"));

    }

    @Test
    public void TagitVoidaanPaivittaa() {

        Vinkki vinkki = new VinkkiStubTest(tekija, nimi, tarkastettu);
        List<String> tagit = new ArrayList<>();
        tagit.add("tag1");
        tagit.add("tag2");
        tagit.add("tag3");

        vinkki.setTagit(tagit);

        assert (vinkki.getTagit().contains("tag1"));
        assert (vinkki.getTagit().contains("tag2"));
        assert (vinkki.getTagit().contains("tag3"));

    }

    @Test
    public void TagejaVoidaanLisata() {

        Vinkki vinkki = new VinkkiStubTest(tekija, nimi, tarkastettu);

        vinkki.addTag("tag4");

        assert (vinkki.getTagit().contains("tag4"));

    }

    @Test
    public void getTyyppiPalauttaaOletuksenaNone() {

        Vinkki vinkki = new VinkkiStubTest(tekija, nimi, tarkastettu);

        assertEquals(Tyyppi.None, vinkki.getTyyppi());

    }

    @Test
    public void tekijaToStringEiPalautMitaanTurhaaJosTyhja() {

        Vinkki vinkki = new VinkkiStubTest("", "", 0);

        assertEquals("Tarkastettu: Ei\n", vinkki.toString());

    }


}
