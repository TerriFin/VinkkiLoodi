package vinkkiloodi.ui;

import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.io.IO;
import vinkkiloodi.logic.ParserLogic;

/**
 *
 * @author samisaukkonen
 */
public class KomentoriviParserUI {

    private IO io;
    private ParserLogic logic;

    public KomentoriviParserUI(IO io, VinkkiDAO dao) {
        this.io = io;
        this.logic = new ParserLogic(io, dao);
    }

    private String NaytaOhjeetJaKysyKomennot() {
        io.printLine("\nTervetuloa käyttämään vinkkiloodi parseria!"
                + "\nLisää tähän tieto siitä miten rakennetaan komentoja"
                + "\n"
                + "\nX - Sulje parser\n");

        return io.nextLine();
    }

    public void start() {
        while (true) {
            String komento = NaytaOhjeetJaKysyKomennot();

            if (komento.toLowerCase().trim().equals("x")) {
                break;
            } else {
                logic.kasitteleKomennot(komento);
            }
        }
    }
}
