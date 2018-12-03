package vinkkiloodi.logic;

import java.util.ArrayList;
import java.util.List;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.io.IO;

/**
 *
 * @author samisaukkonen
 */
public class ParserLogic {

    private IO io;
    private VinkkiDAO dao;
    private List<String> komennot;
    private int kohta;

    public ParserLogic(IO io, VinkkiDAO dao) {
        this.io = io;
        this.dao = dao;
    }

    private void lisaaKomentoihinUudetKomennot(String uudetKomennot) {
        komennot = new ArrayList<>();
        kohta = 0;

        for (String komento : uudetKomennot.split(" ")) {
            komennot.add(komento);
        }
    }

    private String getSeuraavaKomento() {
        return komennot.get(kohta++);
    }

    private String pyydaKomentoUudestaan(String komento, String mahdKomennot) {
        io.printLine("\nKomento '" + komento + "' ei vastaa mitään validia komentoa,"
                + "\nvoitko uudelleenkirjoittaa komennon ja tarkistaa oikeinkirjoituksen?"
                + "\n"
                + "\nmahdolliset komennot ovat: " + mahdKomennot
                + "\n");

        return io.nextLine().toLowerCase();
    }

    public void kasitteleKomennot(String uudetKomennot) {
        lisaaKomentoihinUudetKomennot(uudetKomennot);
        String komento = getSeuraavaKomento();

        while (true) {
            if (komento.equals("lisää") || komento.equals("lisaa")) {
                kasitteleLisays();
                break;
            } else if (komento.equals("listaa")) {
                kasitteleListaus();
                break;
            } else if (komento.equals("nhaku") || komento.equals("nopeahaku")) {
                kasitteleNopeaHaku();
                break;
            } else if (komento.equals("thaku") || komento.equals("tarkkahaku")) {
                kasitteleTarkkaHaku();
                break;
            } else if (komento.equals("päivitä") || komento.equals("paivita")) {
                kasittelePaivitys();
                break;
            } else {
                komento = pyydaKomentoUudestaan(komento, "lisää, listaa, nopeahaku, tarkkahaku, päivitä");
            }
        }
    }

    private void kasitteleLisays() {
        
    }

    private void kasitteleListaus() {

    }

    private void kasitteleNopeaHaku() {

    }

    private void kasitteleTarkkaHaku() {

    }

    private void kasittelePaivitys() {

    }
}
