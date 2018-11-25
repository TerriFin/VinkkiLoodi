package vinkkiloodi.ui;

import java.util.List;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.domain.ArtikkeliVinkki;
import vinkkiloodi.domain.BlogiVinkki;
import vinkkiloodi.domain.KirjaVinkki;
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

    private void paavalikko() {
        while (true) {
            io.printLine("\nMitä haluat tehdä?"
                    + "\n1 - Lisää vinkki"
                    + "\n2 - Listaa vinkit"
                    + "\n3 - Merkitse vinkki luetuksi"
                    + "\nX - Sammuta ohjelma\n");

            String komento = io.nextLine();

            if (komento.toLowerCase().equals("x")) {
                break;
            } else if (komento.equals("1")) {
                lisaa();
            } else if (komento.equals("2")) {
                listaaKaikki();
            } else if (komento.equals("3")) {
                merkitseVinkkiLuetuksi();
            } else {
                io.printLine("\nVirheellinen komento.");
            }
        }
    }

    private void lisaa() {
        while (true) {
            io.printLine("\n-----------\n");
            io.printLine("\nOnko vinkkisi: "
                    + "\n1 - Kirja"
                    + "\n2 - Blogipostaus"
                    + "\n3 - Artikkeli"
                    + "\nX - Peruuta\n");
            String komento = io.nextLine().toLowerCase();

            if (komento.equals("1")) {
                lisaaKirjaVinkki();
                break;
            } else if (komento.equals("2")) {
                lisaaBlogiVinkki();
                break;
            } else if (komento.equals("3")) {
                lisaaArtikkeliVinkki();
                break;
            } else if (komento.equals("x")) {
                break;
            }
        }

    }

    private void lisaaKirjaVinkki() {
        io.printLine("\nLisää kirja\n-----------\n");
        io.printLine("Kirjoittajan nimi: ");
        String nimi = io.nextLine();
        io.printLine("Otsikko: ");
        String otsikko = io.nextLine();
        io.printLine("ISBN: ");
        String isbn = io.nextLine();

        if (isbn.trim().length() < 1) {
            dao.add(new KirjaVinkki(nimi, otsikko));
        } else {
            dao.add(new KirjaVinkki(nimi, otsikko, 0, isbn));
        }

        io.printLine("Vinkki lisätty!");

    }

    private void lisaaBlogiVinkki() {
        io.printLine("\nLisää blogpost\n-----------\n");
        io.printLine("Kirjoittajan nimi: ");
        String nimi = io.nextLine();
        io.printLine("Otsikko: ");
        String otsikko = io.nextLine();
        io.printLine("URL-osoite:");
        String url = io.nextLine();

        dao.add(new BlogiVinkki(nimi, otsikko, url, 0));

    }

    private void lisaaArtikkeliVinkki() {
        io.printLine("\nLisää artikkeli\n-----------\n");
        io.printLine("Kirjoittajan nimi: ");
        String nimi = io.nextLine();
        io.printLine("Otsikko: ");
        String otsikko = io.nextLine();

        dao.add(new ArtikkeliVinkki(nimi, otsikko, "", 0));

    }

    private void listaaKaikki() {
        io.printLine("\nKaikki vinkit\n------------\n");
        List<Vinkki> vinkit = dao.getAll();
        for (int i = 0; i < vinkit.size(); i++) {
            io.printLine(vinkit.get(i).toString());
        }
    }

    private void merkitseVinkkiLuetuksi() {
        io.printLine("\nMerkitse vinkki luetuksi\n--------------------\n");
        io.printLine("Syötä lukuvinkin otsikko: ");
        String haku = io.nextLine();
        haku = haku.toLowerCase().trim();

        // Alkeellinen hakutoiminnallisuus.
        Vinkki haettu = null;
        List<Vinkki> vinkit = dao.getAll();
        for (int i = 0; i < vinkit.size(); i++) {
            Vinkki v = vinkit.get(i);
            if (v.getNimi().toLowerCase().trim().equals(haku)) {
                haettu = v;
                break;
            }
        }

        if (haettu != null) {
            haettu.setTarkastettu(1);
            dao.update(haettu.getId(), haettu);
            io.printLine("Vinkki " + haku + " merkittiin luetuksi.");
        } else {
            io.printLine("Vinkki " + haku + " ei löytynyt järjestelmästä.");
        }
    }
}
