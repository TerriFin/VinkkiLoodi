package vinkkiloodi.ui;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import vinkkiloodi.database.InMemoryDAO;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.io.StubIO;

/**
 *
 * @author samisaukkonen
 */
public class KomentoriviUITest {
    
    private KomentoriviUI ui;
    private StubIO io;
    private VinkkiDAO dao;
    private List<String> komennot;

    @Before
    public void setUp() {
        dao = new InMemoryDAO();
        komennot = new ArrayList<>();
    }
    
    private void lisaaVinkki(String lisattava) {
        komennot.add("1");
        komennot.add(lisattava);
        komennot.add(lisattava);
        komennot.add(lisattava);
    }
    
    private void alustaTesti() {
        komennot.add("x");
        
        io = new StubIO(komennot);
        ui = new KomentoriviUI(io, dao);
    }

    @Test
    public void lisayksenJalkeenLoytyy() {
        lisaaVinkki("test");
        alustaTesti();
        
        int kokoEnnen = dao.getAll().size();
        
        ui.start();
        
        assertTrue(kokoEnnen < dao.getAll().size());
    }
    
    @Test
    public void vinkkiLoytyyLisayksenJalkeen() {
        lisaaVinkki("test1");
        lisaaVinkki("test2");
        komennot.add("2");
        alustaTesti();
        
        ui.start();
        
        assertTrue(io.getOutput().contains("test2"));
    }
}
