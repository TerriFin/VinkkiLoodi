package vinkkiloodi.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.domain.Kirjavinkki;
import vinkkiloodi.domain.Vinkki;

/**
 *
 * @author tumppi
 */
public class KomentoriviUI {

    Scanner input;
    VinkkiDAO dao;

    public KomentoriviUI(Scanner input, VinkkiDAO dao) {
        this.input = input;
        this.dao = dao;
    }

    public void start() {
        System.out.println("Tervetuloa vinkkiloodiin!\n----------------------\n"
                + "Vinkkiloodi on lukuvinkkikirjasto, johon voit lisätä lukuvinkkejä.\n");
        paavalikko();
    }

    public void paavalikko() {
        while (true) {
            System.out.println("\nMitä haluat tehdä?"
                    + "\n1 - Lisää vinkki"
                    + "\n2 - Listaa vinkit"
                    + "\n3 - Merkitse vinkki luetuksi"
                    + "\nX - Sammuta ohjelma");

            String komento = input.nextLine();

            if (komento.toLowerCase().equals("x")) {
                break;
            } else if (komento.equals("1")) {
                lisaaVinkki();
            } else if (komento.equals("2")) {
                listaaKaikki();
            } else if (komento.equals("3")) {
                merkitseVinkkiLuetuksi();
            } else {
                System.out.println("\nVirheellinen komento.");
            }
        }
        System.exit(0);
    }

    public void lisaaVinkki() {
        System.out.println("\nLisää vinkki\n-----------\n");
        System.out.println("Kirjoittajan nimi: ");
        String nimi = input.nextLine();
        System.out.println("Otsikko: ");
        String otsikko = input.nextLine();

        dao.add(new Kirjavinkki(nimi, otsikko));
    }

    public void listaaKaikki() {
        System.out.println("\nKaikki vinkit\n------------\n");
        List<Vinkki> vinkit = dao.getAll();
        for (int i = 0; i < vinkit.size(); i++) {
            System.out.println(vinkit.get(i));
        }
    }

    public void merkitseVinkkiLuetuksi() {
        System.out.println("\nMerkitse vinkki luetuksi\n--------------------\n");
        System.out.println("Syötä lukuvinkin id: ");
        int hakuId = Integer.parseInt(input.nextLine());

        // TODO haku tietokannasta daon avulla.
        Vinkki haettu = dao.getById(hakuId);

        if (haettu != null) {
            System.out.println("Vinkki " + hakuId + " merkittiin luetuksi.");
        } else {
            System.out.println("Vinkki " + hakuId + " ei löytynyt järjestelmästä.");
        }
    }
}
