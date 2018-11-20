package vinkkiloodi.ui;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import vinkkiloodi.database.InMemoryDAO;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.io.IO;
import vinkkiloodi.io.StubIO;

/**
 *
 * @author samisaukkonen
 */
public class KomentoriviUITest {
    
    private KomentoriviUI ui;
    private IO io;
    private VinkkiDAO dao;
    private List<String> komennot;

    @Before
    public void setUp() {
        dao = new InMemoryDAO();
        komennot = new ArrayList<>();
    }

    @Test
    public void lisayksenJalkeenLoytyy() {
        komennot.add("1");
        komennot.add("test");
        komennot.add("test");
        komennot.add("test");
        komennot.add("x");
        
        io = new StubIO(komennot);
        ui = new KomentoriviUI(io, dao);
        
        int kokoEnnen = dao.getAll().size();
        
        ui.start();
        
        assertTrue(kokoEnnen < dao.getAll().size());
    }
}
