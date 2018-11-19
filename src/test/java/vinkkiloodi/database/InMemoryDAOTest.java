package vinkkiloodi.database;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import vinkkiloodi.domain.Kirjavinkki;
import vinkkiloodi.domain.Vinkki;

public class InMemoryDAOTest {
    private InMemoryDAO dao;
    
    @Before
    public void setUp() {
        dao = new InMemoryDAO();
    }
    
    @Test 
    public void lisäysLisääElementinTietokantaan() {
        int alkuKoko = dao.getAll().size();
        
        Kirjavinkki vinkki = new Kirjavinkki("Testi", "Testi");
        
        dao.add(vinkki);
        
        assert(dao.getAll().size() > alkuKoko);
    }
    
    @Test 
    public void elementtiLöytyyListaltaLisäyksenJälkeen() {
        int alkuKoko = dao.getAll().size();
        
        Kirjavinkki vinkki = new Kirjavinkki("Testi2", "Testi2");
        
        dao.add(vinkki);
        
        boolean löytyi = false;
        
        for (Vinkki v : dao.getAll()) {
            if (v.getOtsikko().equals(vinkki.getOtsikko())) {
                löytyi = true;
            }
        }
        
        assert(löytyi);
    }
    
    @Test 
    public void idHakuLöytääOikeanElementin() {
        int alkuKoko = dao.getAll().size();
        
        Kirjavinkki vinkki = new Kirjavinkki("Testi3", "Testi3");
        Kirjavinkki vinkki2 = new Kirjavinkki("Testi4", "Testi4");
        
        dao.add(vinkki);
        dao.add(vinkki2);
        
        Vinkki tulos = dao.getById(vinkki.getId());
        
        assertEquals(tulos, vinkki);
    }
    
    @Test 
    public void olematonIdPalauttaaNull() {
        int alkuKoko = dao.getAll().size();
        
        Kirjavinkki vinkki = new Kirjavinkki("Testi5", "Testi5");
        
        dao.add(vinkki);
        
        Vinkki tulos = dao.getById(Integer.MAX_VALUE);
        
        assertEquals(tulos, null);
    }
}
