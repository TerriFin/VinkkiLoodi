package vinkkiloodi.domain;

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

    @Before
    public void setUp() {
        tekija = "Aatu Artikkelintekija";
        nimi = "Hienompi Artikkeli";
        tarkastettu = 0;
        julkaisija = "julkaisija";

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

}
