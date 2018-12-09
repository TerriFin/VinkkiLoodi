/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import vinkkiloodi.database.InMemoryDAO;
import vinkkiloodi.domain.ArtikkeliVinkki;
import vinkkiloodi.domain.BlogiVinkki;
import vinkkiloodi.domain.KirjaVinkki;
import vinkkiloodi.domain.Tyyppi;
import vinkkiloodi.domain.Vinkki;

/**
 *
 * @author sami
 */
public class HakuBuilderTest {
    private InMemoryDAO dao;
    private HakuBuilder haku;
    
    @Before
    public void setUp() {
        dao = new InMemoryDAO();
        haku = new HakuBuilder();
        
        Vinkki vinkki = new KirjaVinkki("kirja1", "kirjakirjoittaja1", 0, "12345678");
        Vinkki vinkki2 = new KirjaVinkki("kirja2", "kirjakirjoittaja2", 0, "12345679");
        Vinkki vinkki3 = new KirjaVinkki("kirja3", "kirjakirjoittaja3", 0, "12345680");
        
        Vinkki vinkkiBlogi = new BlogiVinkki("blogi1", "blgikirjoittaja1", "www.url.ei", 1);
        Vinkki vinkkiArtikkeli = new ArtikkeliVinkki("artikkeli1", "artikkelikirjoittaja1", "julkaisija", 0);
        Vinkki vinkkiArtikkeli2 = new ArtikkeliVinkki("artikkeli2", "artikkelikirjoittaja2", "julkaisija1", 1);
        
        dao.add(vinkki);
        dao.add(vinkki2);
        dao.add(vinkki3);
        dao.add(vinkkiBlogi);
        dao.add(vinkkiArtikkeli);
        dao.add(vinkkiArtikkeli2);
    }
    
    @Test
    public void kaikkiLoytyvat() {
        Matcher filtteri = haku.build();
        
        assert(dao.matches(filtteri).size() == 6);
    }
    
    @Test
    public void kaikkiKirjaVinkitLoytyvat() {
        Matcher filtteri = haku.onTyyppia(Tyyppi.Kirja).build();
        
        assert(dao.matches(filtteri).size() == 3);
    }
    
    @Test
    public void kaikkiBlogiVinkitLoytyvat() {
        Matcher filtteri = haku.onTyyppia(Tyyppi.Blog).build();
        
        assert(dao.matches(filtteri).size() == 1);
    }
    
    @Test
    public void kaikkiArtikkeliVinkitLoytyvat() {
        Matcher filtteri = haku.onTyyppia(Tyyppi.Artikkeli).build();
        
        assert(dao.matches(filtteri).size() == 2);
    }
    
    @Test
    public void loytyyOtsikolla() {
        Matcher filtteri = haku.nimiSisaltaa("kirja").build();
        
        assert(dao.matches(filtteri).size() == 3);
    }
    
    @Test
    public void loytyyTekijalla() {
        Matcher filtteri = haku.tekijaSisaltaa("artikkeli").build();
        
        assert(dao.matches(filtteri).size() == 2);
    }
    
    @Test
    public void loytyyKirjatJaBlogit() {
        Matcher filtteri = haku.matchOne(
                haku.onTyyppia(Tyyppi.Kirja).build(),
                haku.onTyyppia(Tyyppi.Blog).build()
        ).build();
        
        assert(dao.matches(filtteri).size() == 4);
    }
    
    @Test
    public void lukemattomatLoytyvat() {
        Matcher filtteri = haku.tarkastamaton().build();
        
        assert(dao.matches(filtteri).size() == 4);
    }
    
    @Test
    public void luetutLoytyvat() {
        Matcher filtteri = haku.tarkastettu().build();
        
        assertEquals(2, dao.matches(filtteri).size());
    }
}
