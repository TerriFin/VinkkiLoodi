
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import vinkkiloodi.database.InMemoryDAO;
import vinkkiloodi.domain.Kirjavinkki;
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
    public void vinkinLisaysLisaaTietokantaan() {
        komennot.add("1");
        komennot.add("Kirjoittaja");
        komennot.add("Otsikko");
        komennot.add("ISBN");
        komennot.add("x");

        io = new StubIO(komennot);
        ui = new KomentoriviUI(io, dao);

        int alkukoko = dao.getAll().size();

        ui.start();

        assert (alkukoko < dao.getAll().size());
    }

    @Test
    public void lisattyVinkkiNakyyListalla() {
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
                assert (true);
            }
        }

        assert (false);
    }

    @Test
    public void lisattyjenListausToimii() {
        Kirjavinkki vinkki1 = new Kirjavinkki("Hyvä kirja", "Matti", 0, "123456789");
        Kirjavinkki vinkki2 = new Kirjavinkki("Parempi kirja", "Teppo", 0, "987654321");

        komennot.add("1");
        komennot.add(vinkki1.getKirjoittaja());
        komennot.add(vinkki1.getOtsikko());
        komennot.add(vinkki1.getIsbn());
        komennot.add("1");
        komennot.add(vinkki2.getKirjoittaja());
        komennot.add(vinkki2.getOtsikko());
        komennot.add(vinkki2.getIsbn());
        komennot.add("2");
        komennot.add("x");

        io = new StubIO(komennot);
        ui = new KomentoriviUI(io, dao);

        ui.start();

        boolean sisaltaaVinkki1 = false;
        boolean sisaltaaVinkki2 = false;

        for (String merkkijono : io.getOutput()) {
            if (merkkijono.contains(vinkki1.toString())) {
                sisaltaaVinkki1 = true;
            } else if (merkkijono.contains(vinkki2.toString())) {
                sisaltaaVinkki2 = true;
            }
        }

        assert (sisaltaaVinkki1 && sisaltaaVinkki2);
    }
    
    @Test
    public void kirjanMuuttaminenLuetuksiToimii() {
        Kirjavinkki vinkki = new Kirjavinkki("Hyvä kirja", "Matti", 1, "123456789");
        
        komennot.add("1");
        komennot.add(vinkki.getKirjoittaja());
        komennot.add(vinkki.getOtsikko());
        komennot.add(vinkki.getIsbn());
        komennot.add("3");
        komennot.add(vinkki.getOtsikko());
        komennot.add("x");
        
        io = new StubIO(komennot);
        ui = new KomentoriviUI(io, dao);

        ui.start();
        
        for(String merkkijono:io.getOutput()) {
            if(merkkijono.contains(vinkki.toString())){
                assert(true);
            }
        }
        
        assert(false);
    }
}