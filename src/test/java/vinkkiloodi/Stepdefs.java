
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import vinkkiloodi.database.InMemoryDAO;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.io.StubIO;
import vinkkiloodi.ui.KomentoriviUI;
import vinkkiloodi.ui.KomentoriviUI;

public class Stepdefs {

    VinkkiDAO dao = new InMemoryDAO();
    KomentoriviUI ui;
    StubIO io;

    List<String> inputLines = new ArrayList<>();

    @Given("^command Lisää vinkki is selected$")
    public void command_lisaa_vinkki_is_selected() throws Throwable {
        inputLines.add("1");

    }

    @Given("^command Kirja is selected$")
    public void command_Kirja_is() throws Throwable {
        inputLines.add("1");
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

    @Then("^system responds with \"([^\"]*)\"$")
    public void system_will_respond_with(String expectedOutput) throws Throwable {
        aloita();
        assertTrue(io.getOutput().contains(expectedOutput));
    }

    private void aloita() {
        inputLines.add("x");
        ui = new KomentoriviUI(io = new StubIO(inputLines), dao);
        ui.start();
    }
}
