package vinkkiloodi.ui;

import filter.HakuBuilder;
import filter.Matcher;
import java.util.List;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.domain.ArtikkeliVinkki;
import vinkkiloodi.domain.BlogiVinkki;
import vinkkiloodi.domain.KirjaVinkki;
import vinkkiloodi.domain.Tyyppi;
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
                    + "\npar - Avaa parser"
                    + "\nX - Sammuta ohjelma\n");

            String komento = io.nextLine();
            komento = komento.toLowerCase().trim();

            if (komento.equals("x")) {
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
            } else if (komento.equals("lk")) {
                lisaaKirjaVinkki();
            } else if (komento.equals("la")) {
                lisaaArtikkeliVinkki();
            } else if (komento.equals("lb")) {
                lisaaBlogiVinkki();
            } else if (komento.startsWith("s ")) {
                nopeaHaku(komento.substring(2).trim());
            } else if (komento.startsWith("so ")) {
                printtaaKaikkiNimella(komento.substring(2).trim());
            } else if (komento.startsWith("st ")) {
                printtaaKaikkiTekijalla(komento.substring(2).trim());
            } else if (komento.equals("et")) {
                printtaaKaikkiTarkastamattomat();
            } else if (komento.equals("kt")) {
                printtaaKaikkiTarkastetut();
            } else if (komento.startsWith("pv ")) {
                paivitaVinkki(komento.substring(2).trim());
            } else if (komento.equals("par")) {
                this.parser = new KomentoriviParserUI(io, dao);
                parser.start();
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
            komento = komento.toLowerCase().trim();

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
        io.printLine("Blogpostauksen nimi: ");
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
        paivitaVinkki(haku);
    }

    private void paivitaVinkki(String haku) {

        Vinkki haettu = haeVinkkiOtsikolla(haku);

        if (haettu == null) {
            io.printLine("Ei löytynyt vinkkiä hakusanalla \"" + haku + "\".");
            return;
        }

        io.printLine("Vinkki löytyi järjestelmästä.\nPäivitä vinkki (tyhjä kenttä = pysyy entisenä)\n");

        io.printLine("Nykyinen otsikko: " + haettu.getNimi() + ", Uusi otsikko: ");
        String uusiOtsikko = io.nextLine();

        io.printLine("Nykyinen tekijä: " + haettu.getTekija() + ", Uusi tekijä: ");
        String uusiTekija = io.nextLine();

        String tarkastettu = "";
        if (haettu.getTarkastettu() == 0) {
            tarkastettu = "Ei";
        } else {
            tarkastettu = "Kyllä";
        }
        io.printLine("On tarkastettu: " + tarkastettu + ", Onko (k/e): ");
        String uusiTarkastettu = io.nextLine();

        if (!uusiOtsikko.isEmpty()) {
            haettu.setNimi(uusiOtsikko);
        }

        if (!uusiTekija.isEmpty()) {
            haettu.setTekija(uusiTekija);
        }

        if (uusiTarkastettu.equals("k")) {
            haettu.setTarkastettu(1);
        } else if (uusiTarkastettu.equals("e")) {
            haettu.setTarkastettu(0);
        }

        haettu = paivittaja(haettu);
        dao.update(haettu.getId(), haettu);
        io.printLine("Päivitettiin vinkki \"" + haettu.getNimi() + "\".");

    }

    private Vinkki paivittaja(Vinkki haettu) {
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
        return haettu;
    }

    private Vinkki paivitaKirja(KirjaVinkki kv) {
        io.printLine("Vanha ISBN: " + kv.getISBN() + ", Uusi ISBN: ");
        String uusiIsbn = io.nextLine();
        if (!uusiIsbn.isEmpty()) {
            kv.setISBN(uusiIsbn);
        }
        return kv;
    }

    private Vinkki paivitaBlogi(BlogiVinkki bv) {
        io.printLine("Vanha URL: " + bv.getUrl() + ", Uusi URL: ");
        String uusiUrl = io.nextLine();
        if (!uusiUrl.isEmpty()) {
            bv.setUrl(uusiUrl);
        }
        return bv;
    }

    private Vinkki paivitaArtikkeli(ArtikkeliVinkki av) {
        io.printLine("Vanha julkaisija: " + av.getJulkaisija() + ", Uusi julkaisija: ");
        String uusiJulkaisija = io.nextLine();
        if (!uusiJulkaisija.isEmpty()) {
            av.setJulkaisija(uusiJulkaisija);
        }
        return av;
    }

    private Vinkki haeVinkkiOtsikolla(String hakusana) {
        Matcher haku = new HakuBuilder().nimiSisaltaa(hakusana).build();
        
        List<Vinkki> vinkit = dao.matches(haku);
        
        if (vinkit.isEmpty()) {
            return null;
        } else {
            return vinkit.get(0);
        }
    }

    private void nopeaHaku() {
        io.printLine("\nNopea haku\n-----------\n");
        io.printLine("Anna hakusana: ");
        String hakusana = io.nextLine();

        nopeaHaku(hakusana);
    }

    private void nopeaHaku(String hakusana) {
        io.printLine("Haetaan hakusannalla \"" + hakusana + "\"...");

        List<Vinkki> kirjat = dao.megaHaku(hakusana);

        for (Vinkki vinkki : kirjat) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    private void tarkkaHaku() {
        io.printLine("\nTarkka haku\n-----------\n");
        io.printLine("\nMitä haet?: "
                + "\n1 - Kirjoja"
                + "\n2 - Blogeja"
                + "\n3 - Artikkeleita"
                + "\n4 - Vinkit tekijällä"
                + "\n5 - Vinkit otsikolla"
                + "\n6 - Tarkastettamattomia vinkkejä"
                + "\n7 - Tarkastettuja vinkkejä"
                + "\nX - Peruuta\n");

        while (true) {
            String komento = io.nextLine().toLowerCase();
            komento = komento.toLowerCase().trim();

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
        Matcher haku = new HakuBuilder().onTyyppia(Tyyppi.Kirja).build();
        
        List<Vinkki> kirjat = dao.matches(haku);

        for (Vinkki vinkki : kirjat) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    private void printtaaKaikkiBlogit() {
        Matcher haku = new HakuBuilder().onTyyppia(Tyyppi.Blog).build();
        
        List<Vinkki> blogit = dao.matches(haku);

        for (Vinkki vinkki : blogit) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    private void printtaaKaikkiArtikkelit() {
        Matcher haku = new HakuBuilder().onTyyppia(Tyyppi.Artikkeli).build();
        
        List<Vinkki> artikkelit = dao.matches(haku);

        for (Vinkki vinkki : artikkelit) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    private void printtaaKaikkiTekijalla() {
        io.printLine("\nMikä tekijä?: ");
        String hakuSana = io.nextLine();
        printtaaKaikkiTekijalla(hakuSana);
    }

    private void printtaaKaikkiTekijalla(String hakuSana) {
        io.printLine("Haetaan tekijällä \"" + hakuSana + "\"...");
        
        Matcher haku = new HakuBuilder().tekijaSisaltaa(hakuSana).build();
        
        List<Vinkki> vinkit = dao.matches(haku);

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
        printtaaKaikkiNimella(hakuSana);
    }

    private void printtaaKaikkiNimella(String hakuSana) {
        io.printLine("Haetaan otsikolla \"" + hakuSana + "\"...");
        Matcher haku = new HakuBuilder().nimiSisaltaa(hakuSana).build();
        
        List<Vinkki> vinkit = dao.matches(haku);

        if (vinkit.isEmpty()) {
            io.printLine("Vinkkejä ei löytynyt!");
        } else {
            for (Vinkki kirjaVinkki : vinkit) {
                io.printLine("\n" + kirjaVinkki.toString());
            }
        }
    }
    
    private void oldPrinttaaKaikkiTarkastamattomat() {
        List<Vinkki> vinkit = dao.getAll();

        for (Vinkki vinkki : vinkit) {
            if (vinkki.getTarkastettu() == 0) {
                io.printLine("\n" + vinkki.toString());
            }
        }
    }
    
    private void printtaaKaikkiTarkastamattomat() {
        Matcher haku = new HakuBuilder().tarkastamaton().build();
        
        List<Vinkki> tarkastamattomat = dao.matches(haku);

        for (Vinkki vinkki : tarkastamattomat) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    private void printtaaKaikkiTarkastetut() {
        Matcher haku = new HakuBuilder().tarkastettu().build();
        
        List<Vinkki> tarkastetut = dao.matches(haku);

        for (Vinkki vinkki : tarkastetut) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    private void listaaPikakomennot() {
        io.printLine("\nPikakomennot (syötä ilman hipsuja)\n-----------\n"
                + "Lisää vinkkejä:\n"
                + "\'lk\' = lisää kirjavinkki\n"
                + "\'la\' = lisää artikkelivinkki\n"
                + "\'lb\' = lisää blogivinkki\n"
                + "Päivitä vinkkejä:\n"
                + "\'pv\' + hakusana = päivitä vinkki otsikolla\n"
                + "Hae vinkkejä:\n"
                + "\'s\' + hakusana = hae vinkkejä hakusanalla\n"
                + "\'so\' + hakusana = hae vinkkejä otsikolla\n"
                + "\'st\' + hakusana = hae vinkkejä tekijällä\n"
                + "\'et\' = listaa tarkastamattomat vinkit (et = \"ei-tarkastetut\")\n"
                + "\'kt\' = listaa tarkastetut vinkit (kt = \"kyllä-tarkastetut\")\n");
    }
}
