
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.database.VinkkiSqliteDAO;
import vinkkiloodi.database.VinkkiSqliteDAOTest;
import vinkkiloodi.domain.Vinkki;
import vinkkiloodi.io.StubIO;
import vinkkiloodi.ui.KomentoriviUI;

public class Stepdefs {

    VinkkiDAO dao;
    KomentoriviUI ui;
    StubIO io;

    List<String> inputLines = new ArrayList<>();
    File db;
    File folder;

    @Before
    public void setUp() throws IOException {
        try {
            new File(".tmp").mkdirs();
            folder = new File(".tmp");
            db = new File(".tmp/tmptestCucumber.db");
            dao = new VinkkiSqliteDAO(db.getAbsolutePath());
        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() throws IOException {
        db.delete();
        folder.delete();

    }

    @Given("^command Lisää vinkki is selected$")
    public void command_lisaa_vinkki_is_selected() throws Throwable {
        inputLines.add("1");

    }

    @Given("^command Kirja is selected$")
    public void command_Kirja_is() throws Throwable {
        inputLines.add("1");
    }

    @Given("^esimerkkikirjavinkki on tallennettu tietokantaan$")
    public void esimerkkikirjavinkki_on_tallennettu_tietokantaan() throws Throwable {
        inputLines.add("1");
        inputLines.add("1");
        inputLines.add("testitekija");
        inputLines.add("testiotsikko");
        inputLines.add("12323");

    }

    @Given("^esimerkkiblogivinkki on tallennettu tietokantaan$")
    public void esimerkkiblogivinkki_on_tallennettu_tietokantaan() throws Throwable {
        inputLines.add("1");
        inputLines.add("2");
        inputLines.add("testitekija");
        inputLines.add("testiotsikko");
        inputLines.add("testurl");

    }

    @Given("^esimerkkiartikkelivinkki on tallennettu tietokantaan$")
    public void esimerkkiartikkelivinkki_on_tallennettu_tietokantaan() throws Throwable {

        inputLines.add("1");
        inputLines.add("3");
        inputLines.add("testitekija");
        inputLines.add("testiotsikko");
        inputLines.add("testjulkasija");

    }

    @When("^writer \"([^\"]*)\" title \"([^\"]*)\" and ISBN \"([^\"]*)\" are entered$")
    public void writer_title_and_ISBN_are_entered(String writer, String title, String ISBN) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        inputLines.add(writer);
        inputLines.add(title);
        inputLines.add(ISBN);

    }

    @When("^all vinkkis are printed$")
    public void all_vinkkis_are_printed() throws Throwable {
        inputLines.add("2");
    }

    @When("^komento Listaa vinkit on valittu$")
    public void komento_Listaa_vinkit_on_valittu() throws Throwable {
        inputLines.add("2");
    }

    @Then("^system responds with \"([^\"]*)\"$")
    public void system_will_respond_with(String expectedOutput) throws Throwable {
        aloita();
        assert (io.getOutput().contains(expectedOutput));
    }

    @Then("^system saves the Vinkki titled \"([^\"]*)\"$")
    public void system_saves_the_Vinkki_titled(String arg1) throws Throwable {
        List<Vinkki> ennen = dao.getByNimi(arg1);
        aloita();
        List<Vinkki> jalkeen = dao.getByNimi(arg1);
        assertEquals(ennen.size() + 1, jalkeen.size());
    }

    @Then("^esimerkkikirjavinkki näytetään käyttäjälle$")
    public void esimerkkikirjavinkki_näytetään_käyttäjälle() throws Throwable {
        List<Vinkki> ennen = dao.getAll();
        aloita();
        List<Vinkki> jalkeen = dao.getAll();
        assertEquals(ennen.size() + 1, jalkeen.size());
    }

    @Then("^esimerkkiblogivinkki näytetään käyttäjälle$")
    public void esimerkkiblogivinkki_näytetään_käyttäjälle() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        List<Vinkki> ennen = dao.getAll();
        aloita();
        List<Vinkki> jalkeen = dao.getAll();
        assertEquals(ennen.size() + 1, jalkeen.size());
    }

    @Then("^esimerkkiartikkelivinkki näytetään käyttäjälle$")
    public void esimerkkiartikkelivinkki_näytetään_käyttäjälle() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^esimerkkikirjavinkki ja esimerkkiblogivinkki näytetään käyttäjälle$")
    public void esimerkkikirjavinkki_ja_esimerkkiblogivinkki_näytetään_käyttäjälle() throws Throwable {
        List<Vinkki> ennen = dao.getAll();
        aloita();
        List<Vinkki> jalkeen = dao.getAll();
        assertEquals(ennen.size() + 2, jalkeen.size());
    }

    @Then("^esimerkkikirjavinkki, esimerkkiblogivinkki ja esimerkkiartikkelivinkki  näytetään käyttäjälle$")
    public void esimerkkikirjavinkki_esimerkkiblogivinkki_ja_esimerkkiartikkelivinkki_näytetään_käyttäjälle() throws Throwable {
        List<Vinkki> ennen = dao.getAll();
        aloita();
        List<Vinkki> jalkeen = dao.getAll();
        assertEquals(ennen.size() + 3, jalkeen.size());
    }

    private void aloita() {
        inputLines.add("x");
        io = new StubIO(inputLines);
        ui = new KomentoriviUI(io, dao);
        ui.start();
    }
}
