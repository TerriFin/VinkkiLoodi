package vinkkiloodi.ui;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.domain.ArtikkeliVinkki;
import vinkkiloodi.domain.BlogiVinkki;
import vinkkiloodi.domain.KirjaVinkki;
//import vinkkiloodi.domain.Tyyppi;
import vinkkiloodi.domain.Vinkki;
import vinkkiloodi.io.IO;

/**
 *
 * @author tumppi
 */
public class KomentoriviUI {

    IO io;
    VinkkiDAO dao;
    KomentoriviParserUI parser;
    Komento command;

    public KomentoriviUI(IO io, VinkkiDAO dao) {
        this.io = io;
        this.dao = dao;
        this.command = new Komento(io, dao);
    }

    public void start() {
        io.printLine("Tervetuloa vinkkiloodiin!\n----------------------\n"
                + "Vinkkiloodi on lukuvinkkikirjasto, johon voit lisätä lukuvinkkejä.\n");
        paavalikko();
    }

    private void paavalikko() {
        while (true) {
            io.printLine("\nMitä haluat tehdä?"
                    + "\n1 - Lisää vinkki"
                    + "\n2 - Listaa vinkit"
                    + "\n3 - Nopea haku"
                    + "\n4 - Tarkka haku"
                    + "\n5 - Päivitä vinkki"
                    + "\n6 - Listaa pikakomennot"
                    + "\npar - Avaa parser"
                    + "\nX - Sammuta ohjelma\n");

            String komento = io.nextLine();
            komento = komento.toLowerCase().trim();
            
            if (komento.equals("x")) {
                break;
            } 
            
            command.getKomento(komento);
            
            if (komento.equals("par")) {
                this.parser = new KomentoriviParserUI(io, dao);
                parser.start();
            } else {
                io.printLine("\nVirheellinen komento.");
            }
        }
    }

}
