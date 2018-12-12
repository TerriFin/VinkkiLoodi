
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
import vinkkiloodi.domain.ArtikkeliVinkki;
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

    @Given("^eri niminen toinen kirjavinkki on tallennettu tietokantaan$")
    public void tallenna_toinen_kirjavinkki_eri_nimella() throws Throwable {
        inputLines.add("1");
        inputLines.add("1");
        inputLines.add("toinen tekija");
        inputLines.add("toinen otsikko");
        inputLines.add("98765");
    }

    @Given("^toinen kirja merkitään tarkastetuksi$")
    public void merkitse_toinen_kirja_tarkastetuksi() throws Throwable {
        inputLines.add("5");
        inputLines.add("toinen otsikko");
        inputLines.add("");
        inputLines.add("");
        inputLines.add("k");
        inputLines.add("");
    }

    @Given("^pikakomento \"([^\"]*)\" on syötetty$")
    public void pikakomento_on_syötetty(String pikakomento) throws Throwable {
        inputLines.add(pikakomento);
    }

    @Given("^tarkastettu esimerkkiartikkelivinkki on tallennettu tietokantaan$")
    public void tarkastettu_esimerkkiartikkelivinkki_on_tallennettu_tietokantaan() throws Throwable {
        dao.add(new ArtikkeliVinkki("testitekija", "testiotsikko", "testjulkasija", 1));
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

    @When("^komento Päivitä vinkki on valittu$")
    public void komento_Paivita_vinkki_on_valittu() throws Throwable {
        inputLines.add("5");
    }

    @When("^komento Listaa vinkit on valittu$")
    public void komento_Listaa_vinkit_on_valittu() throws Throwable {
        inputLines.add("2");
    }

    @When("^komento Tarkka haku on valittu$")
    public void komento_Tarkka_haku_on_valittu() throws Throwable {
        inputLines.add("4");
    }

    @When("^komento Nopea haku on valittu$")
    public void komento_Nopea_haku_on_valittu() throws Throwable {
        inputLines.add("3");
    }

    // Esimerkkivinkkien otsikkojen syöttö
    @When("^esimerkkikirjavinkin otsikko on syötetty$")
    public void esimerkkiblogivinkin_otsikko_syotetty() throws Throwable {
        inputLines.add("testiotsikko");
    }

    @When("^esimerkkiblogivinkin otsikko on syötetty$")
    public void esimerkkikirjavinkin_otsikko_syotetty() throws Throwable {
        inputLines.add("testiotsikko");
    }

    @When("^esimerkkiartikkelivinkin otsikko on syötetty$")
    public void esimerkkiartikkelivinkin_otsikko_syotetty() throws Throwable {
        inputLines.add("testiotsikko");
    }

    // Esimerkkivinkkien tekijöiden syöttö
    @When("^esimerkkikirjavinkin tekijä on syötetty$")
    public void esimerkkikirjavinkin_tekija_syotetty() throws Throwable {
        inputLines.add("testitekijä");
    }

    @When("^\"([^\"]*)\" on syötetty$")
    public void syota_sana(String sana) throws Throwable {
        inputLines.add(sana);
    }

    @When("^tyhjä rivi on syötetty$")
    public void tyhja_rivi_syotetty() throws Throwable {
        inputLines.add("");
    }

    @When("^merkitään lukemattomaksi$")
    public void merkitaan_lukemattomaksi() throws Throwable {
        inputLines.add("e");
    }

    // Tarkan haun valinnat
    @When("^valitaan tulostettavaksi kirjoja$")
    public void tarkka_valinta_1() throws Throwable {
        inputLines.add("1");
    }

    @When("^valitaan tulostettavaksi blogeja$")
    public void tarkka_valinta_2() throws Throwable {
        inputLines.add("2");
    }

    @When("^valitaan tulostettavaksi artikkeleita$")
    public void tarkka_valinta_3() throws Throwable {
        inputLines.add("3");
    }

    @When("^haetaan vinkit tekijällä$")
    public void tarkka_valinta_4() throws Throwable {
        inputLines.add("4");
    }

    @When("^haetaan vinkit otsikolla$")
    public void tarkka_valinta_5() throws Throwable {
        inputLines.add("5");
    }

    @When("^valitaan tulostettavaksi tarkastamattomia vinkkejä$")
    public void tarkka_valinta_6() throws Throwable {
        inputLines.add("6");
    }

    @When("^valitaan tulostettavaksi tarkastettuja vinkkejä$")
    public void tarkka_valinta_7() throws Throwable {
        inputLines.add("7");
    }

    @When("^Nimi \"([^\"]*)\" on syötetty$")
    public void nimi_on_syötetty(String nimi) throws Throwable {
        inputLines.add(nimi);
    }

    @When("^Otsikko \"([^\"]*)\" on syötetty$")
    public void otsikko_on_syötetty(String otsikko) throws Throwable {
        inputLines.add(otsikko);
    }

    @When("^ISBN \"([^\"]*)\" on syötetty$")
    public void isbn_on_syötetty(String isbn) throws Throwable {
        inputLines.add(isbn);
    }

    @When("^Julkaisija \"([^\"]*)\" on syötetty$")
    public void julkaisija_on_syötetty(String julkaisija) throws Throwable {
        inputLines.add(julkaisija);
    }

    @When("^URL \"([^\"]*)\" on syötetty$")
    public void url_on_syötetty(String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        inputLines.add(url);
    }

    // Then
    @Then("^tuloste sisältää \"([^\"]*)\"$")
    public void tuloste_sisaltaa(String sana) throws Throwable {
        aloita();
        assert (outputista_loytyy_sana(sana));
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

        List<Vinkki> ennen = dao.getAll();
        aloita();
        List<Vinkki> jalkeen = dao.getAll();
        assertEquals(ennen.size() + 1, jalkeen.size());
    }

    @Then("^esimerkkiartikkelivinkki näytetään käyttäjälle$")
    public void esimerkkiartikkelivinkki_näytetään_käyttäjälle() throws Throwable {
        List<Vinkki> ennen = dao.getAll();
        aloita();
        List<Vinkki> jalkeen = dao.getAll();
        assertEquals(ennen.size() + 1, jalkeen.size());

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

    @Then("^tuloste sisältää haetun esimerkkikirjavinkin$")
    public void tuloste_sisaltaa_haetun_esimerkkikirjavinkin() throws Throwable {
        aloita();
        assert (outputista_loytyy_sana("12323"));
    }

    @Then("^tuloste sisältää haetun esimerkkiblogivinkin$")
    public void tuloste_sisaltaa_haetun_esimerkkiblogivinkin() throws Throwable {
        aloita();
        assert (outputista_loytyy_sana("testurl"));
    }

    @Then("^tuloste sisältää haetun esimerkkiartikkelivinkin$")
    public void tuloste_sisaltaa_haetun_esimerkkiartikkelivinkin() throws Throwable {
        aloita();
        assert (outputista_loytyy_sana("testjulkasija"));
    }

    @Then("^tuloste sisältää molemmat kirjavinkit")
    public void tuloste_sisaltaa_molemmat_kirjavinkit() throws Throwable {
        aloita();
        assert (outputista_loytyy_sana("12323")
                && outputista_loytyy_sana("98765"));
    }

    @Then("^tuloste ei sisällä esimerkkivinkkejä$")
    public void tuloste_ei_sisalla_esimerkkivinkkeja() throws Throwable {
        aloita();
        assert (!outputista_loytyy_sana("12323")
                && !outputista_loytyy_sana("testjulkasija")
                && !outputista_loytyy_sana("testurl"));
    }

    @Then("^tuloste ei sisällä toista kirjavinkkiä$")
    public void tuloste_ei_sisalla_toista_kirjavinkkia() throws Throwable {
        aloita();
        assert (!outputista_loytyy_sana("98765"));
    }

    @Then("^tuloste sisältää kaikki vinkit$")
    public void tuloste_sisaltaa_kaikki_vinkit() throws Throwable {
        aloita();
        assert (outputista_loytyy_sana("12323")
                && outputista_loytyy_sana("testjulkasija")
                && outputista_loytyy_sana("testurl"));
    }

    @Then("^tuloste sisältää vinkin tarkastettuna$")
    public void tuloste_sisältää_vinkin_tarkastettuna() throws Throwable {
        aloita();
        assert (outputista_loytyy_sana("testiotsikko")
                && outputista_loytyy_sana("Tarkastettu: Kyllä"));
    }

    private boolean outputista_loytyy_sana(String sana) {
        List<String> tuloste = io.getOutput();
        for (String s : tuloste) {
            if (s.contains(sana)) {
                return true;
            }
        }
        return false;
    }

    private void aloita() {
        inputLines.add("x");
        io = new StubIO(inputLines);
        ui = new KomentoriviUI(io, dao);
        ui.start();
    }
}
