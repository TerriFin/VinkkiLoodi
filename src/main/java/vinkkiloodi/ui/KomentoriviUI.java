package vinkkiloodi.ui;

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
                    + "\n3 - Nopea haku"
                    + "\n4 - Tarkka haku"
                    + "\n5 - Päivitä vinkki"
                    + "\n6 - Listaa pikakomennot"
                    + "\nX - Sammuta ohjelma\n");

            String komento = io.nextLine();

            if (komento.toLowerCase().trim().equals("x")) {
                break;
            } else if (komento.equals("1")) {
                lisaa();
            } else if (komento.equals("2")) {
                listaaKaikki();
            } else if (komento.equals("3")) {
                nopeaHaku();
            } else if (komento.equals("4")) {
                tarkkaHaku();
            } else if (komento.equals("5")) {
                paivitaVinkki();
            } else if (komento.equals("6")) {
                listaaPikakomennot();
            } else if (komento.toLowerCase().trim().equals("lk")) {
                lisaaKirjaVinkki();
            } else if (komento.toLowerCase().trim().equals("la")) {
                lisaaArtikkeliVinkki();
            } else if (komento.toLowerCase().trim().equals("lb")) {
                lisaaBlogiVinkki();
            } else if (komento.toLowerCase().trim().startsWith("s ")) {
                nopeaHaku(komento.trim().substring(2).trim().toLowerCase());
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
            } else if (komento.toLowerCase().trim().equals("x")) {
                break;
            } else {
                io.printLine("\nVirheellinen komento.");
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
        io.printLine("Tekijän nimi: ");
        String nimi = io.nextLine();
        io.printLine("Blogin nimi: ");
        String otsikko = io.nextLine();
        io.printLine("URL-osoite:");
        String url = io.nextLine();

        dao.add(new BlogiVinkki(nimi, otsikko, url, 0));

        io.printLine("Vinkki lisätty!");
    }

    private void lisaaArtikkeliVinkki() {
        io.printLine("\nLisää artikkeli\n-----------\n");
        io.printLine("Kirjoittajan nimi: ");
        String nimi = io.nextLine();
        io.printLine("Otsikko: ");
        String otsikko = io.nextLine();
        io.printLine("Julkaisija: ");
        String julkaisija = io.nextLine();

        dao.add(new ArtikkeliVinkki(nimi, otsikko, julkaisija, 0));

        io.printLine("Vinkki lisätty!");
    }

    private void listaaKaikki() {
        io.printLine("\nKaikki vinkit\n------------\n");
        List<Vinkki> vinkit = dao.getAll();
        for (Vinkki vinkki : vinkit) {
            io.printLine(vinkki.toString());
        }
    }

    private void paivitaVinkki() {
        io.printLine("\nPäivitä vinkki\n--------------------\n");
        io.printLine("Syötä lukuvinkin otsikko: ");
        String haku = io.nextLine();
        haku = haku.toLowerCase().trim();

        // Alkeellinen hakutoiminnallisuus.
        Vinkki haettu = haeVinkkiOtsikolla(haku);

        if (haettu == null) {
            io.printLine("Ei löytynyt vinkkiä hakusanalla \"" + haku + "\".");
            return;
        }

        io.printLine("Vinkki löytyi järjestelmästä.\nPäivitä vinkki (tyhjä kenttä = pysyy entisenä)\n");

        System.out.println("Nykyinen otsikko: " + haettu.getNimi() + ", Uusi otsikko: ");
        String uusiOtsikko = io.nextLine();
        System.out.println("Nykyinen tekijä: " + haettu.getTekija() + ", Uusi tekijä: ");
        String uusiTekija = io.nextLine();
        String tarkastettu = "";
        if (haettu.getTarkastettu() == 0) {
            tarkastettu = "Ei";
        } else {
            tarkastettu = "Kyllä";
        }
        System.out.println("On tarkastettu: " + tarkastettu + ", Onko (k/e): ");
        String uusiTarkastettu = io.nextLine();

        haettu.setNimi(uusiOtsikko);
        haettu.setTekija(uusiTekija);
        if (uusiTarkastettu.equals("k")) {
            haettu.setTarkastettu(1);
        } else if (uusiTarkastettu.equals("e")) {
            haettu.setTarkastettu(0);
        }

        switch (haettu.getTyyppi()) {
            case Kirja:
                haettu = paivitaKirja((KirjaVinkki) haettu);
                break;
            case Blog:
                haettu = paivitaBlogi((BlogiVinkki) haettu);
                break;
            case Artikkeli:
                haettu = paivitaArtikkeli((ArtikkeliVinkki) haettu);
                break;
            default:
                break;
        }

        dao.update(haettu.getId(), haettu);
        io.printLine("Päivitettiin vinkki \"" + haettu.getNimi() + "\".");

    }

    private Vinkki paivitaKirja(KirjaVinkki kv) {
        io.printLine("Vanha ISBN: " + kv.getISBN() + ", Uusi ISBN: ");
        String uusiIsbn = io.nextLine();
        kv.setISBN(uusiIsbn);
        return kv;
    }

    private Vinkki paivitaBlogi(BlogiVinkki bv) {
        io.printLine("Vanha URL: " + bv.getUrl() + ", Uusi URL: ");
        String uusiUrl = io.nextLine();
        bv.setUrl(uusiUrl);
        return bv;
    }

    private Vinkki paivitaArtikkeli(ArtikkeliVinkki av) {
        io.printLine("Vanha julkaisija: " + av.getJulkaisija() + ", Uusi julkaisija: ");
        String uusiJulkaisija = io.nextLine();
        av.setJulkaisija(uusiJulkaisija);
        return av;
    }

    private Vinkki haeVinkkiOtsikolla(String haku) {
        List<Vinkki> vinkit = dao.getAll();
        for (int i = 0; i < vinkit.size(); i++) {
            Vinkki v = vinkit.get(i);
            if (v.getNimi().toLowerCase().trim().equals(haku)) {
                return v;
            }
        }
        return null;
    }

    private void nopeaHaku() {
        io.printLine("\nNopea haku\n-----------\n");
        io.printLine("Anna hakusana: ");
        String hakusana = io.nextLine();
        // Toteuta nopea haku tänne
    }
    
    private void nopeaHaku(String hakusana) {
        io.printLine("\nNopea haku\n-----------\n");
        io.printLine("Hakusana: \"" + hakusana + "\"");
        // Toteuta nopea haku tänne
    }

    private void tarkkaHaku() {
        io.printLine("\nTarkka haku\n-----------\n");
        io.printLine("\nMitä haet?: "
                + "\n1 - Kirjoja"
                + "\n2 - Blogeja"
                + "\n3 - Artikkeleita"
                + "\n4 - Tekijän vinkit"
                + "\n5 - Vinkit nimellä"
                + "\n6 - Tarkastettamattomia vinkkejä"
                + "\n7 - Tarkastettuja vinkkejä"
                + "\nX - Peruuta\n");

        while (true) {
            String komento = io.nextLine().toLowerCase();

            if (komento.equals("1")) {
                printtaaKaikkiKirjat();
                break;
            } else if (komento.equals("2")) {
                printtaaKaikkiBlogit();
                break;
            } else if (komento.equals("3")) {
                printtaaKaikkiArtikkelit();
                break;
            } else if (komento.equals("4")) {
                printtaaKaikkiTekijalla();
                break;
            } else if (komento.equals("5")) {
                printtaaKaikkiNimella();
                break;
            } else if (komento.equals("6")) {
                printtaaKaikkiTarkastamattomat();
                break;
            } else if (komento.equals("7")) {
                printtaaKaikkiTarkastetut();
                break;
            } else if (komento.equals("x")) {
                break;
            } else {
                io.printLine("\nVirheellinen komento.");
            }
        }
    }

    private void printtaaKaikkiKirjat() {
        List<Vinkki> kirjat = dao.getKaikkiKirjat();

        for (Vinkki vinkki : kirjat) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    private void printtaaKaikkiBlogit() {
        List<Vinkki> blogit = dao.getKaikkiBlogit();

        for (Vinkki vinkki : blogit) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    private void printtaaKaikkiArtikkelit() {
        List<Vinkki> artikkelit = dao.getKaikkiArtikkelit();

        for (Vinkki vinkki : artikkelit) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    private void printtaaKaikkiTekijalla() {
        io.printLine("\nMikä tekijä?: ");
        String hakuSana = io.nextLine();

        List<Vinkki> vinkit = dao.getByTekija(hakuSana);

        if (vinkit.isEmpty()) {
            io.printLine("Vinkkejä ei löytynyt!");
        } else {
            for (Vinkki kirjaVinkki : vinkit) {
                io.printLine("\n" + kirjaVinkki.toString());
            }
        }
    }

    private void printtaaKaikkiNimella() {
        io.printLine("\nMikä nimi?: ");
        String hakuSana = io.nextLine();

        List<Vinkki> vinkit = dao.getByNimi(hakuSana);

        if (vinkit.isEmpty()) {
            io.printLine("Vinkkejä ei löytynyt!");
        } else {
            for (Vinkki kirjaVinkki : vinkit) {
                io.printLine("\n" + kirjaVinkki.toString());
            }
        }
    }

    private void printtaaKaikkiTarkastamattomat() {
        List<Vinkki> tarkastamattomat = dao.getKaikkiTarkastamattomat();

        for (Vinkki vinkki : tarkastamattomat) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    private void printtaaKaikkiTarkastetut() {
        List<Vinkki> tarkastetut = dao.getKaikkitarkastetut();

        for (Vinkki vinkki : tarkastetut) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    private void listaaPikakomennot() {
        System.out.println("\nPikakomennot\n-----------");
        System.out.println("\nLisää vinkki:");
        System.out.println("\'lk\' = lisää kirjavinkki");
        System.out.println("\'la\' = lisää artikkelivinkki");
        System.out.println("\'lb\' = lisää blogivinkki");
        System.out.println("\nHaku:");
        System.out.println("\'s\' + hakusana = hae vinkkejä hakusanalla");
    }

}
