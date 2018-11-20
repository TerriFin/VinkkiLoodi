
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import vinkkiloodi.database.InMemoryDAO;
import vinkkiloodi.io.StubIO;
import vinkkiloodi.ui.KomentoriviUI;


public class KomentoriviUITest {
    private KomentoriviUI ui;
    private StubIO io;
    private ArrayList<String> komennot;
    private InMemoryDAO dao;
    
    @Before
    public void setUp() {
        dao = new InMemoryDAO();
        komennot = new ArrayList<>();
    }
    
    @Test 
    public void vinkinLisäysLisääTietokantaan() {
        komennot.add("1");
        komennot.add("Kirjoittaja");
        komennot.add("Otsikko");
        komennot.add("ISBN");
        komennot.add("x");
        
        io = new StubIO(komennot);
        ui = new KomentoriviUI(io, dao);
        
        int alkukoko = dao.getAll().size();
        
        ui.start();
        
        assert(alkukoko < dao.getAll().size());
    }
    
    @Test 
    public void lisättyVinkkiNäkyyListalla() {
        komennot.add("1");
        komennot.add("UniikkiKirjoittaja");
        komennot.add("UniikkiOtsikko");
        komennot.add("ISBN");
        komennot.add("2");
        komennot.add("x");
        
        io = new StubIO(komennot);
        ui = new KomentoriviUI(io, dao);
                
        ui.start();
        
        for (String merkkijono : io.getOutput()) {
            if (merkkijono.contains("UniikkiOtsikko")) {
                assert(true);
            }
        }
        
        assert(false);
    }
}