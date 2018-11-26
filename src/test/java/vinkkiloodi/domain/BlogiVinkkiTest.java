package vinkkiloodi.domain;

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

    @Before
    public void setUp() {
        tekija = "Bob Blogittaja";
        nimi = "Hieno Blogi";
        luettu = 0;
        url = "testurl·eiole";
        

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

}
