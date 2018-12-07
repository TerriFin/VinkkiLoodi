/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.ui;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.domain.ArtikkeliVinkki;
import vinkkiloodi.domain.BlogiVinkki;
import vinkkiloodi.domain.KirjaVinkki;
import vinkkiloodi.domain.Vinkki;
import vinkkiloodi.io.IO;

/**
 *
 * @author Pyry
 */
public class Komento {
    
    
    IO io;
    VinkkiDAO dao;
    KomentoriviParserUI parser;

    public Komento(IO io, VinkkiDAO dao) {
        this.io = io;
        this.dao = dao;
    }
    
    public void getKomento(String komento) {
        
        
            if (komento.equals("1")) {
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
            }
    }
    
    public void lisaa() {
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

    public void lisaaKirjaVinkki() {
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

    public void lisaaBlogiVinkki() {
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

    public void lisaaArtikkeliVinkki() {
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

    public void listaaKaikki() {
        io.printLine("\nKaikki vinkit\n------------\n");
        List<Vinkki> vinkit = dao.getAll();
        for (Vinkki vinkki : vinkit) {
            io.printLine(vinkki.toString());
        }
    }

    public void paivitaVinkki() {
        io.printLine("\nPäivitä vinkki\n--------------------\n");
        io.printLine("Syötä lukuvinkin otsikko: ");
        String haku = io.nextLine();
        haku = haku.toLowerCase().trim();
        paivitaVinkki(haku);
    }

    public void paivitaVinkki(String haku) {

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
        
        if (!uusiOtsikko.isEmpty())
            haettu.setNimi(uusiOtsikko);
        
        if (!uusiTekija.isEmpty())
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

    public Vinkki paivitaKirja(KirjaVinkki kv) {
        io.printLine("Vanha ISBN: " + kv.getISBN() + ", Uusi ISBN: ");
        String uusiIsbn = io.nextLine();
        if (!uusiIsbn.isEmpty())
            kv.setISBN(uusiIsbn);
        return kv;
    }

    public Vinkki paivitaBlogi(BlogiVinkki bv) {
        io.printLine("Vanha URL: " + bv.getUrl() + ", Uusi URL: ");
        String uusiUrl = io.nextLine();
        if (!uusiUrl.isEmpty())
            bv.setUrl(uusiUrl);
        return bv;
    }

    public Vinkki paivitaArtikkeli(ArtikkeliVinkki av) {
        io.printLine("Vanha julkaisija: " + av.getJulkaisija() + ", Uusi julkaisija: ");
        String uusiJulkaisija = io.nextLine();
        if (!uusiJulkaisija.isEmpty())
            av.setJulkaisija(uusiJulkaisija);
        return av;
    }

    public Vinkki haeVinkkiOtsikolla(String haku) {
        List<Vinkki> vinkit = dao.getAll();
        for (int i = 0; i < vinkit.size(); i++) {
            Vinkki v = vinkit.get(i);
            if (v.getNimi().toLowerCase().trim().equals(haku)) {
                return v;
            }
        }
        return null;
    }

    public void nopeaHaku() {
        io.printLine("\nNopea haku\n-----------\n");
        io.printLine("Anna hakusana: ");
        String hakusana = io.nextLine();

        nopeaHaku(hakusana);
    }

    public void nopeaHaku(String hakusana) {
        io.printLine("Haetaan hakusannalla \"" + hakusana + "\"...");

        List<Vinkki> kirjat = dao.megaHaku(hakusana);

        for (Vinkki vinkki : kirjat) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    public void tarkkaHaku() {
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

    public void printtaaKaikkiKirjat() {
        List<Vinkki> kirjat = dao.getKaikkiKirjat();

        for (Vinkki vinkki : kirjat) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    public void printtaaKaikkiBlogit() {
        List<Vinkki> blogit = dao.getKaikkiBlogit();

        for (Vinkki vinkki : blogit) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    public void printtaaKaikkiArtikkelit() {
        List<Vinkki> artikkelit = dao.getKaikkiArtikkelit();

        for (Vinkki vinkki : artikkelit) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    public void printtaaKaikkiTekijalla() {
        io.printLine("\nMikä tekijä?: ");
        String hakuSana = io.nextLine();
        printtaaKaikkiTekijalla(hakuSana);
    }

    public void printtaaKaikkiTekijalla(String hakuSana) {
        io.printLine("Haetaan tekijällä \"" + hakuSana + "\"...");
        List<Vinkki> vinkit = dao.getByTekija(hakuSana);

        if (vinkit.isEmpty()) {
            io.printLine("Vinkkejä ei löytynyt!");
        } else {
            for (Vinkki kirjaVinkki : vinkit) {
                io.printLine("\n" + kirjaVinkki.toString());
            }
        }
    }

    public void printtaaKaikkiNimella() {
        io.printLine("\nMikä nimi?: ");
        String hakuSana = io.nextLine();
        printtaaKaikkiNimella(hakuSana);
    }

    public void printtaaKaikkiNimella(String hakuSana) {
        io.printLine("Haetaan otsikolla \"" + hakuSana + "\"...");
        List<Vinkki> vinkit = dao.getByNimi(hakuSana);

        if (vinkit.isEmpty()) {
            io.printLine("Vinkkejä ei löytynyt!");
        } else {
            for (Vinkki kirjaVinkki : vinkit) {
                io.printLine("\n" + kirjaVinkki.toString());
            }
        }
    }

    public void printtaaKaikkiTarkastamattomat() {
        List<Vinkki> tarkastamattomat = dao.getKaikkiTarkastamattomat();

        for (Vinkki vinkki : tarkastamattomat) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    public void printtaaKaikkiTarkastetut() {
        List<Vinkki> tarkastetut = dao.getKaikkitarkastetut();

        for (Vinkki vinkki : tarkastetut) {
            io.printLine("\n" + vinkki.toString());
        }
    }

    public void listaaPikakomennot() {
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
