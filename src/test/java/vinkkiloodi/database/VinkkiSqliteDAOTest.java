package vinkkiloodi.database;

import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import vinkkiloodi.domain.KirjaVinkki;
import vinkkiloodi.domain.Vinkki;

public class VinkkiSqliteDAOTest {
    private VinkkiSqliteDAO dao;
    
    @Before
    public void setUp() {
        try {
            dao = new VinkkiSqliteDAO("test.db");
        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        File db = new File("test.db");
        
        if (db.exists()) {
            db.delete();
        }
    }
    
    @Test 
    public void sqliteDAOMuodostaaTietokantaYhteyden() {
        try {
            assert(dao.getConnection() != null);
        } catch (SQLException ex) {
            assert(false);
        }
    }
    
    @Test 
    public void lisaysLisaaElementinTietokantaan() {
        int alkuKoko = dao.getAll().size();
        
        KirjaVinkki vinkki = new KirjaVinkki("Testi", "Testi");
        
        dao.add(vinkki);
        
        assert(dao.getAll().size() > alkuKoko);
    }
    
    @Test 
    public void elementtiLoytyyListaltaLisayksenJalkeen() {
        int alkuKoko = dao.getAll().size();
        
        KirjaVinkki vinkki = new KirjaVinkki("Testi2", "Testi2");
        
        dao.add(vinkki);
        
        boolean loytyi = false;
        
        for (Vinkki v : dao.getAll()) {
            if (v.getNimi().equals(vinkki.getNimi())) {
                loytyi = true;
            }
        }
        
        assert(loytyi);
    }
    
    @Test 
    public void idHakuLoytaaOikeanElementin() {
        int alkuKoko = dao.getAll().size();
        
        KirjaVinkki vinkki = new KirjaVinkki("Testi3", "Testi3");
        KirjaVinkki vinkki2 = new KirjaVinkki("Testi4", "Testi4");
        
        dao.add(vinkki);
        dao.add(vinkki2);
        
        Vinkki tulos = dao.getById(vinkki.getId());
        
        assertEquals(tulos.getId(), vinkki.getId());
    }
    
    @Test 
    public void olematonIdPalauttaaNull() {
        int alkuKoko = dao.getAll().size();
        
        KirjaVinkki vinkki = new KirjaVinkki("Testi5", "Testi5");
        
        dao.add(vinkki);
        
        Vinkki tulos = dao.getById(Integer.MAX_VALUE);
        
        assertEquals(tulos, null);
    }
    
    @Test 
    public void paivitysMuuttaaKirjoittajaa() {
        KirjaVinkki vinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen");
        
        dao.add(vinkki);
        
        KirjaVinkki uusiVinkki = new KirjaVinkki("Uusi Kirjoittaja", "Alkuperäinen");
        
        dao.update(vinkki.getId(), uusiVinkki);
        
        Vinkki tulos = dao.getById(vinkki.getId());
        
        assertEquals(tulos.getTekija(), "Uusi Kirjoittaja");
    }
    
    @Test 
    public void paivitysMuuttaaOtsikkoa() {
        KirjaVinkki vinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen");
        
        dao.add(vinkki);
        
        KirjaVinkki uusiVinkki = new KirjaVinkki("Alkuperäinen", "Uusi Otsikko");
        
        dao.update(vinkki.getId(), uusiVinkki);
        
        Vinkki tulos = dao.getById(vinkki.getId());
        
        assertEquals(tulos.getNimi(), "Uusi Otsikko");
    }
    
    @Test 
    public void paivitysMuuttaaLuettua() {
        KirjaVinkki vinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen");
        
        dao.add(vinkki);
        
        KirjaVinkki uusiVinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen", 1, "");
        
        dao.update(vinkki.getId(), uusiVinkki);
        
        Vinkki tulos = dao.getById(vinkki.getId());
        
        assertEquals(tulos.getTarkastettu(), 1);
    }
}
