package vinkkiloodi.ui;

import java.util.List;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.domain.Kirjavinkki;
import vinkkiloodi.domain.Vinkki;
import vinkkiloodi.io.IO;

/**
 *
 * @author tumppi
 */
public class KomentoriviUI {

    IO io;
    VinkkiDAO dao;

    public KomentoriviUI(IO io, VinkkiDAO dao) {
        this.io = io;
        this.dao = dao;
    }

    public void start() {
        io.printLine("Tervetuloa vinkkiloodiin!\n----------------------\n"
                + "Vinkkiloodi on lukuvinkkikirjasto, johon voit lisätä lukuvinkkejä.\n");
        paavalikko();
    }

    public void paavalikko() {
        while (true) {
            io.printLine("\nMitä haluat tehdä?"
                    + "\n1 - Lisää vinkki"
                    + "\n2 - Listaa vinkit"
                    + "\n3 - Merkitse vinkki luetuksi"
                    + "\nX - Sammuta ohjelma");

            String komento = io.nextLine();

            if (komento.toLowerCase().equals("x")) {
                break;
            } else if (komento.equals("1")) {
                lisaaVinkki();
            } else if (komento.equals("2")) {
                listaaKaikki();
            } else if (komento.equals("3")) {
                merkitseVinkkiLuetuksi();
            } else {
                io.printLine("\nVirheellinen komento.");
            }
        }
        System.exit(0);
    }

    public void lisaaVinkki() {
        io.printLine("\nLisää vinkki\n-----------\n");
        io.printLine("Kirjoittajan nimi: ");
        String nimi = io.nextLine();
        io.printLine("Otsikko: ");
        String otsikko = io.nextLine();
        io.printLine("ISBN: ");
        String isbn = io.nextLine();
        
        if (isbn.trim().length() < 1) {
            dao.add(new Kirjavinkki(nimi, otsikko));
        }
        else {
            dao.add(new Kirjavinkki(nimi, otsikko, 0, isbn));
        }
        
        io.printLine("Vinkki lisätty!");

        
    }

    public void listaaKaikki() {
        io.printLine("\nKaikki vinkit\n------------\n");
        List<Vinkki> vinkit = dao.getAll();
        for (int i = 0; i < vinkit.size(); i++) {
            io.printLine(vinkit.get(i).toString());
        }
    }

    public void merkitseVinkkiLuetuksi() {
        io.printLine("\nMerkitse vinkki luetuksi\n--------------------\n");
        io.printLine("Syötä lukuvinkin id: ");
        int hakuId = io.nextInt();

        // TODO haku tietokannasta daon avulla.
        Vinkki haettu = dao.getById(hakuId);

        if (haettu != null) {
            io.printLine("Vinkki " + hakuId + " merkittiin luetuksi.");
        } else {
            io.printLine("Vinkki " + hakuId + " ei löytynyt järjestelmästä.");
        }
    }
}
