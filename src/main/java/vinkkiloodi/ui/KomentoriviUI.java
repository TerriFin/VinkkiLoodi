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
                    + "\nX - Sammuta ohjelma\n");

            String komento = io.nextLine();

            if (komento.toLowerCase().equals("x")) {
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
        for (int i = 0; i < vinkit.size(); i++) {
            io.printLine(vinkit.get(i).toString());
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

    private void tarkkaHaku() {
        io.printLine("\nTarkka haku\n-----------\n");
        io.printLine("\nMitä haet?: "
                + "\n1 - Kirja"
                + "\n2 - Blogipostaus"
                + "\n3 - Artikkeli"
                + "\n4 - tekijä"
                + "\nX - Peruuta\n");

        while (true) {
            String komento = io.nextLine().toLowerCase();

            if (komento.equals("1")) {
                kysyJaPrinttaaKirjaTarkka();
                break;
            } else if (komento.equals("2")) {
                kysyJaPrinttaaBlogiTarkka();
                break;
            } else if (komento.equals("3")) {
                kysyJaPrinttaaArtikkeliTarkka();
                break;
            } else if (komento.equals("4")) {
                haeKaikkiTekijalla();
                break;
            } else if (komento.equals("x")) {
                break;
            } else {
                io.printLine("\nVirheellinen komento.");
            }
        }

    }

    private void haeKaikkiTekijalla() {
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

    private void kysyJaPrinttaaKirjaTarkka() {
        io.printLine("\nHaet kirjaa: "
                + "\n1 - Tekijän kautta"
                + "\n2 - Nimen kautta"
                + "\n");
        String komento = io.nextLine().toLowerCase();

        if (komento.equals("1")) {
            printtaaTarkkaTekijaHaku(1);
        } else if (komento.equals("2")) {
            printtaaTarkkaNimiHaku(1);
        }
    }

    private void kysyJaPrinttaaBlogiTarkka() {
        io.printLine("\nHaet blogia: "
                + "\n1 - Tekijän kautta"
                + "\n2 - Nimen kautta"
                + "\n");
        String komento = io.nextLine().toLowerCase();

        if (komento.equals("1")) {
            printtaaTarkkaTekijaHaku(2);
        } else if (komento.equals("2")) {
            printtaaTarkkaNimiHaku(2);
        }
    }

    private void kysyJaPrinttaaArtikkeliTarkka() {
        io.printLine("\nHaet artikkelia: "
                + "\n1 - Tekijän kautta"
                + "\n2 - Nimen kautta"
                + "\n");
        String komento = io.nextLine().toLowerCase();

        if (komento.equals("1")) {
            printtaaTarkkaTekijaHaku(3);
        } else if (komento.equals("2")) {
            printtaaTarkkaNimiHaku(3);
        }
    }

    private void printtaaTarkkaTekijaHaku(int vinkkiTyyppi) {
        io.printLine("\nMikä tekijä?: ");
        String hakuSana = io.nextLine();

        List<Vinkki> vinkit;
        switch (vinkkiTyyppi) {
            case 1:
                vinkit = dao.getKirjaByTekija(hakuSana);
                if (vinkit.isEmpty()) {
                    io.printLine("Vinkkejä ei löytynyt!");
                } else {
                    for (Vinkki kirjaVinkki : vinkit) {
                        io.printLine("\n" + kirjaVinkki.toString());
                    }
                }
                break;
            case 2:
                vinkit = dao.getBlogiByTekija(hakuSana);
                if (vinkit.isEmpty()) {
                    io.printLine("Vinkkejä ei löytynyt!");
                } else {
                    for (Vinkki blogiVinkki : vinkit) {
                        io.printLine("\n" + blogiVinkki.toString());
                    }
                }
                break;
            case 3:
                vinkit = dao.getArtikkeliByTekija(hakuSana);
                if (vinkit.isEmpty()) {
                    io.printLine("Vinkkejä ei löytynyt!");
                } else {
                    for (Vinkki artikkeliVinkki : vinkit) {
                        io.printLine("\n" + artikkeliVinkki.toString());
                    }
                }
                break;
            default:
                break;
        }
    }

    private void printtaaTarkkaNimiHaku(int vinkkiTyyppi) {
        io.printLine("EI OLE TOTEUTETTU");
        // Toteuta tämä kun daosta saatavilla
    }
}
