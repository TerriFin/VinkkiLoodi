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
public class KomentoriviParserUITest {

    private StubIO io;
    private VinkkiDAO dao;
    private List<String> input;
    private KomentoriviUI ui;

    @Before
    public void setUp() {

        dao = new InMemoryDAO();
        input = new ArrayList<>();

        input.add("par");
    }

    private void startParser() {
        input.add("x");
        input.add("x");

        io = new StubIO(input);
        KomentoriviUI ui = new KomentoriviUI(io, dao);
        ui.start();
    }

    private void lisaaTestiKirja(String tekija, String nimi, String ISBN) {
        input.add("lisää kirja (\"" + tekija + "\" \"" + nimi + "\" \"" + ISBN + "\")");
    }

    private void lisaaTestiBlogi(String tekija, String nimi, String URL) {
        input.add("lisää blogi (\"" + tekija + "\" \"" + nimi + "\" \"" + URL + "\")");
    }

    private void lisaaTestiArtikkeli(String tekija, String nimi, String julkaisija) {
        input.add("lisää artikkeli (\"" + tekija + "\" \"" + nimi + "\" \"" + julkaisija + "\")");
    }

    @Test
    public void avunPrinttaaminenToimii() {
        input.add("apua");

        startParser();

        boolean apuLoytynyt = false;
        for (String rivi : io.getOutput()) {
            if (rivi.contains("Aloitus Komennot ovat seuraavat")) {
                apuLoytynyt = true;
                break;
            }
        }

        assertTrue(apuLoytynyt);
    }

    @Test
    public void kirjanLisaaminenToimii() {
        lisaaTestiKirja("tekijä", "kirjanNimi", "123123");

        startParser();

        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void bloginLisaaminenToimii() {
        lisaaTestiKirja("tekijä", "bloginNimi", "www.google.com");

        startParser();

        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void artikkelinLisaaminenToimii() {
        lisaaTestiKirja("tekijä", "artikkelinNimi", "iltalehti");

        startParser();

        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void vaarinLaitettuSulkuEiToimi() {
        input.add("lisää kirja (\"testi\" \"testi\" \"123123\"");
        input.add("lisää blogi \"testi\" \"testi\" \"123123\")");
        input.add("lisää artikkeli (\"testi\" \"testi\" \"123123\"");
        input.add("lisää kirja (");
        input.add("lisää blogi )");

        startParser();

        assertEquals(0, dao.getAll().size());
    }

    @Test
    public void kirjanVoiLaittaaOsissa() {
        input.add("lis kirjq (\"testi\" \"testi\" \"123123\")");
        input.add("lisää");
        input.add("kirja");

        startParser();

        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void bloginVoiLaittaaOsissa() {
        input.add("lis blag (\"testi\" \"testi\" \"www.yt.com\")");
        input.add("lisää");
        input.add("blogi");

        startParser();

        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void artikkelinVoiLaittaaOsissa() {
        input.add("lis arti (\"testi\" \"testi\" \"iltalehti\")");
        input.add("lisää");
        input.add("artikkeli");

        startParser();

        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void listausToimii() {
        String ekaNimi = "Eetu";
        String tokaNimi = "Sami";
        String kolmasNimi = "Leo";

        lisaaTestiKirja(ekaNimi, "kirjaNimi", "123123");
        lisaaTestiBlogi(tokaNimi, "blogiNimi", "www.youtube.com");
        lisaaTestiArtikkeli(kolmasNimi, "artikkeliNimi", "powdergame");

        input.add("listaa");

        boolean ekaLoydetty = false;
        boolean tokaLoydetty = false;
        boolean kolmasLoydetty = false;

        startParser();

        for (String output : io.getOutput()) {
            if (output.contains(ekaNimi)) {
                ekaLoydetty = true;
            }

            if (output.contains(tokaNimi)) {
                tokaLoydetty = true;
            }

            if (output.contains(kolmasNimi)) {
                kolmasLoydetty = true;
            }
        }

        assertTrue(ekaLoydetty);
        assertTrue(tokaLoydetty);
        assertTrue(kolmasLoydetty);
    }

    @Test
    public void nopeaHakuToimii() {
        lisaaTestiKirja("eetu", "eetunkirja", "13123");
        lisaaTestiBlogi("eppu", "blogi ee", "www.test.com");
        lisaaTestiArtikkeli("aatu", "artikkeli", "spotify");

        startParser();

        input = new ArrayList<>();
        input.add("par");
        input.add("nhaku ee");

        startParser();

        boolean eetuLoytynyt = false;
        boolean eppuLoytynyt = false;

        for (String rivi : io.getOutput()) {
            if (rivi.contains("eetu")) {
                eetuLoytynyt = true;
            }

            if (rivi.contains("eppu")) {
                eppuLoytynyt = true;
            }
        }

        assertTrue(eetuLoytynyt);
        assertTrue(eppuLoytynyt);
    }

    @Test
    public void kirjojaVoiPaivittaa() {
        lisaaTestiKirja("testi", "testi", "123");

        input.add("päivitä testi (\"sami\" \"kirja\" \"321\" \"tarkistettu\")");
        input.add("listaa");

        startParser();

        boolean samiPaivitetty = false;
        boolean tarkastettu = false;
        for (String rivi : io.getOutput()) {
            if (rivi.contains("sami")) {
                samiPaivitetty = true;
            }

            if (rivi.contains("Tarkastettu: Kyllä")) {
                tarkastettu = true;
            }
        }

        assertTrue(samiPaivitetty);
        assertTrue(tarkastettu);
    }

    @Test
    public void blogejaVoiPaivittaa() {
        lisaaTestiBlogi("testi", "testi", "123");

        input.add("päivitä testi (\"sami\" \"blogi\" \"www.google.com\" \"tarkistettu\")");
        input.add("listaa");

        startParser();

        boolean samiPaivitetty = false;
        boolean tarkastettu = false;
        for (String rivi : io.getOutput()) {
            if (rivi.contains("sami")) {
                samiPaivitetty = true;
            }

            if (rivi.contains("Tarkastettu: Kyllä")) {
                tarkastettu = true;
            }
        }

        assertTrue(samiPaivitetty);
        assertTrue(tarkastettu);
    }

    @Test
    public void artikkeleitaVoiPaivittaa() {
        lisaaTestiArtikkeli("testi", "testi", "123");

        input.add("päivitä testi (\"sami\" \"artikkeli\" \"iltalehti\" \"tarkistettu\")");
        input.add("listaa");

        startParser();

        boolean samiPaivitetty = false;
        boolean tarkastettu = false;
        for (String rivi : io.getOutput()) {
            if (rivi.contains("sami")) {
                samiPaivitetty = true;
            }

            if (rivi.contains("Tarkastettu: Kyllä")) {
                tarkastettu = true;
            }
        }

        assertTrue(samiPaivitetty);
        assertTrue(tarkastettu);
    }

    @Test
    public void osaaPyytaaUusiaAtribuuttejaLisatessa() {
        input.add("lisää kirja (\"test\" \"test\" \"123)");
        input.add("(\"test\" \"test\" \"123\")");

        startParser();

        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void osaaPyytaaUusiaAtribuuttejaPaivittaessa() {
        lisaaTestiArtikkeli("testi", "testi", "123");

        input.add("päivitä testi (\"sami\" \"artikkeli \"iltalehti\" \"tarkistettu\")");
        input.add("(\"sami\" \"artikkeli\" \"iltalehti\" \"tarkistettu\")");

        input.add("listaa");

        startParser();

        boolean samiPaivitetty = false;
        boolean tarkastettu = false;
        for (String rivi : io.getOutput()) {
            if (rivi.contains("sami")) {
                samiPaivitetty = true;
            }

            if (rivi.contains("Tarkastettu: Kyllä")) {
                tarkastettu = true;
            }
        }

        assertTrue(samiPaivitetty);
        assertTrue(tarkastettu);
    }
}
