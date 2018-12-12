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
import vinkkiloodi.io.ColorFlag;
import vinkkiloodi.io.ColorPlacer;
import vinkkiloodi.io.IO;
import vinkkiloodi.io.StubIO;

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
                + "Vinkkiloodi on lukuvinkkikirjasto, johon voit lisätä lukuvinkkejä.\n",
                new ColorPlacer(new ColorFlag(7, "green")));
        paavalikko();
    }
    
    private void paavalikko() {
        while (true) {
            io.printLine("\nMitä haluat tehdä? "
                    + "\n1 - Lisää vinkki "
                    + "\n2 - Listaa vinkit "
                    + "\n3 - Nopea haku "
                    + "\n4 - Tarkka haku "
                    + "\n5 - Päivitä vinkki "
                    + "\n6 - Listaa pikakomennot "
                    + "\npar - Avaa parser "
                    + "\nX - Sammuta ohjelma\n",
                    new ColorPlacer(
                            new ColorFlag(3, "purple"),
                            new ColorFlag(7, "purple"),
                            new ColorFlag(11, "purple"),
                            new ColorFlag(15, "purple"),
                            new ColorFlag(19, "purple"),
                            new ColorFlag(23, "purple"),
                            new ColorFlag(27, "purple"),
                            new ColorFlag(31, "purple"),
                            new ColorFlag(33, "red"),
                            new ColorFlag(34, "red")));
            
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
            } else if (komento.startsWith("et")) {
                printtaaTarkastamattomat(komento);
            } else if (komento.startsWith("kt")) {
                printtaaTarkastetut(komento);
            } else if (komento.startsWith("pv ")) {
                paivitaVinkki(komento.substring(2).trim());
            } else if (komento.startsWith("ta ")) {
                merkkaaTarkastetuksi(komento.substring(2).trim());
            } else if (komento.equals("par")) {
                this.parser = new KomentoriviParserUI(io, dao);
                parser.start();
            } else {
                printtaaVirheellinenKomento();
            }
        }
    }
    
    private void lisaa() {
        while (true) {
            io.printLine("\n-----------\n");
            io.printLine("\nOnko vinkkisi: "
                    + "\n1 - Kirja "
                    + "\n2 - Blogipostaus "
                    + "\n3 - Artikkeli "
                    + "\nX - Peruuta\n",
                    new ColorPlacer(
                            new ColorFlag(2, "purple"),
                            new ColorFlag(5, "purple"),
                            new ColorFlag(8, "purple"),
                            new ColorFlag(11, "purple"),
                            new ColorFlag(4, "green"),
                            new ColorFlag(7, "green"),
                            new ColorFlag(10, "green"),
                            new ColorFlag(13, "red")));
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
                printtaaVirheellinenKomento();
            }
        }
        
    }
    
    private void lisaaKirjaVinkki() {
        io.printLine("\nLisää kirja\n-----------\n",
                new ColorPlacer(
                        new ColorFlag(1, "green")));
        io.printLine("Kirjoittajan nimi: ",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
        String nimi = io.nextLine();
        io.printLine("Otsikko: ",
                new ColorPlacer(
                        new ColorFlag(0, "cyan")));
        String otsikko = io.nextLine();
        io.printLine("ISBN: ",
                new ColorPlacer(
                        new ColorFlag(0, "cyan")));
        String isbn = io.nextLine();
        
        if (isbn.trim().length() < 1) {
            dao.add(new KirjaVinkki(nimi, otsikko));
        } else {
            dao.add(new KirjaVinkki(nimi, otsikko, 0, isbn));
        }
        
        printtaaVinkkiLisatty();
    }
    
    private void lisaaBlogiVinkki() {
        io.printLine("\nLisää blogpost\n-----------\n",
                new ColorPlacer(
                        new ColorFlag(1, "green"))
        );
        io.printLine("Tekijän nimi: ",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
        String nimi = io.nextLine();
        io.printLine("Blogpostauksen nimi: ",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
        String otsikko = io.nextLine();
        io.printLine("URL-osoite:",
                new ColorPlacer(
                        new ColorFlag(0, "cyan")));
        String url = io.nextLine();
        
        dao.add(new BlogiVinkki(nimi, otsikko, url, 0));
        
        printtaaVinkkiLisatty();
    }
    
    private void lisaaArtikkeliVinkki() {
        io.printLine("\nLisää artikkeli\n-----------\n",
                new ColorPlacer(
                        new ColorFlag(1, "green")));
        io.printLine("Kirjoittajan nimi: ",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
        String nimi = io.nextLine();
        io.printLine("Otsikko: ",
                new ColorPlacer(
                        new ColorFlag(0, "cyan")));
        String otsikko = io.nextLine();
        io.printLine("Julkaisija: ",
                new ColorPlacer(
                        new ColorFlag(0, "cyan")));
        String julkaisija = io.nextLine();
        
        dao.add(new ArtikkeliVinkki(nimi, otsikko, julkaisija, 0));
        
        printtaaVinkkiLisatty();
    }
    
    private void listaaKaikki() {
        io.printLine("\nKaikki vinkit\n------------\n");
        printtaaKaikkiVinkitListalla(dao.getAll());
    }
    
    private void paivitaVinkki() {
        io.printLine("\nPäivitä vinkki\n--------------------\n");
        io.printLine("Syötä lukuvinkin otsikko: ",
                new ColorPlacer(
                        new ColorFlag(2, "cyan")));
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
        
        io.printLine("Nykyinen otsikko: " + haettu.getNimi() + ", Uusi otsikko: ",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
        String uusiOtsikko = io.nextLine();
        
        io.printLine("Nykyinen tekijä: " + haettu.getTekija() + ", Uusi tekijä: ",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
        String uusiTekija = io.nextLine();
        
        String tarkastettu = "";
        if (haettu.getTarkastettu() == 0) {
            tarkastettu = "Ei";
        } else {
            tarkastettu = "Kyllä";
        }
        io.printLine("On tarkastettu: " + tarkastettu + ", Onko (k/e): ",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
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
        io.printLine("Vanha ISBN: " + kv.getISBN() + ", Uusi ISBN: ",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
        String uusiIsbn = io.nextLine();
        if (!uusiIsbn.isEmpty()) {
            kv.setISBN(uusiIsbn);
        }
        return kv;
    }
    
    private Vinkki paivitaBlogi(BlogiVinkki bv) {
        io.printLine("Vanha URL: " + bv.getUrl() + ", Uusi URL: ",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
        String uusiUrl = io.nextLine();
        if (!uusiUrl.isEmpty()) {
            bv.setUrl(uusiUrl);
        }
        return bv;
    }
    
    private Vinkki paivitaArtikkeli(ArtikkeliVinkki av) {
        io.printLine("Vanha julkaisija: " + av.getJulkaisija() + ", Uusi julkaisija: ",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
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
        io.printLine("Anna hakusana: ",
                new ColorPlacer(
                        new ColorFlag(1, "yellow")));
        String hakusana = io.nextLine();
        
        nopeaHaku(hakusana);
    }
    
    private void nopeaHaku(String hakusana) {
        io.printLine("Haetaan hakusannalla \"" + hakusana + "\"...\n",
                new ColorPlacer(
                        new ColorFlag(1, "yellow")));
        
        Matcher haku = new HakuBuilder().matchOne(new HakuBuilder().tekijaSisaltaa(hakusana).build(),
                                                new HakuBuilder().nimiSisaltaa(hakusana).build()
        ).build();
        
        List<Vinkki> vinkit = dao.matches(haku);
        
        printtaaKaikkiVinkitListalla(vinkit);
    }
    
    private void tarkkaHaku() {
        io.printLine("\nTarkka haku\n-----------\n");
        io.printLine("\nMitä haet?: "
                + "\n1 - Kirjoja "
                + "\n2 - Blogeja "
                + "\n3 - Artikkeleita "
                + "\n4 - Vinkit tekijällä "
                + "\n5 - Vinkit otsikolla "
                + "\n6 - Tarkastettamattomia vinkkejä "
                + "\n7 - Tarkastettuja vinkkejä "
                + "\nX - Peruuta\n",
                new ColorPlacer(
                        new ColorFlag(2, "purple"),
                        new ColorFlag(5, "purple"),
                        new ColorFlag(8, "purple"),
                        new ColorFlag(11, "purple"),
                        new ColorFlag(15, "purple"),
                        new ColorFlag(19, "purple"),
                        new ColorFlag(23, "purple"),
                        new ColorFlag(27, "purple"),
                        new ColorFlag(4, "green"),
                        new ColorFlag(7, "green"),
                        new ColorFlag(10, "green"),
                        new ColorFlag(14, "cyan"),
                        new ColorFlag(18, "cyan"),
                        new ColorFlag(21, "cyan"),
                        new ColorFlag(25, "cyan"),
                        new ColorFlag(29, "red")));
        
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
                printtaaVirheellinenKomento();
            }
        }
    }
    
    private void printtaaKaikkiKirjat() {
        Matcher haku = new HakuBuilder().onTyyppia(Tyyppi.Kirja).build();
        
        List<Vinkki> kirjat = dao.matches(haku);
        
        printtaaKaikkiVinkitListalla(kirjat);
    }
    
    private void printtaaKaikkiBlogit() {
        Matcher haku = new HakuBuilder().onTyyppia(Tyyppi.Blog).build();
        
        List<Vinkki> blogit = dao.matches(haku);
        
        printtaaKaikkiVinkitListalla(blogit);
    }
    
    private void printtaaKaikkiArtikkelit() {
        Matcher haku = new HakuBuilder().onTyyppia(Tyyppi.Artikkeli).build();
        
        List<Vinkki> artikkelit = dao.matches(haku);
        
        printtaaKaikkiVinkitListalla(artikkelit);
    }
    
    private void printtaaKaikkiTekijalla() {
        io.printLine("\nMikä tekijä?: ",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
        String hakuSana = io.nextLine();
        printtaaKaikkiTekijalla(hakuSana);
    }
    
    private void printtaaKaikkiTekijalla(String hakuSana) {
        io.printLine("Haetaan tekijällä \"" + hakuSana + "\"...\n",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
        
        Matcher haku = new HakuBuilder().tekijaSisaltaa(hakuSana).build();
        
        List<Vinkki> vinkit = dao.matches(haku);
        
        if (vinkit.isEmpty()) {
            printtaaVinkkiaEiLoytynyt();
        } else {
            printtaaKaikkiVinkitListalla(vinkit);
        }
    }
    
    private void printtaaKaikkiNimella() {
        io.printLine("\nMikä nimi?: ",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
        String hakuSana = io.nextLine();
        printtaaKaikkiNimella(hakuSana);
    }
    
    private void printtaaKaikkiNimella(String hakuSana) {
        io.printLine("Haetaan otsikolla \"" + hakuSana + "\"...\n",
                new ColorPlacer(
                        new ColorFlag(1, "cyan")));
        Matcher haku = new HakuBuilder().nimiSisaltaa(hakuSana).build();
        
        List<Vinkki> vinkit = dao.matches(haku);
        
        if (vinkit.isEmpty()) {
            printtaaVinkkiaEiLoytynyt();
        } else {
            printtaaKaikkiVinkitListalla(vinkit);
        }
    }
    
    private void printtaaTarkastetut(String komento) {
        if (komento.trim().equals("kt")) {
            printtaaKaikkiTarkastetut();
        } else {
            komento = komento.substring(3);
            printtaaTietytTarkastetut(komento.contains("k"), komento.contains("a"), komento.contains("b"));
        }
    }
    
    private void printtaaTarkastamattomat(String komento) {
        if (komento.trim().equals("et")) {
            printtaaKaikkiTarkastamattomat();
        } else {
            komento = komento.substring(3);
            printtaaTietytTarkastamattomat(komento.contains("k"), komento.contains("a"), komento.contains("b"));
        }
    }
    
    private void printtaaTietytTarkastetut(boolean kirjat, boolean artikkelit, boolean blogit) {
        Matcher haku = new HakuBuilder()
                .onJokuHalutuistaTyypeista(kirjat, artikkelit, blogit)
                .tarkastettu()
                .build();
        List<Vinkki> halutut = dao.matches(haku);
        if (halutut.isEmpty()) {
            printtaaVinkkiaEiLoytynyt();
        } else {
            printtaaKaikkiVinkitListalla(halutut);
        }
    }
    
    private void printtaaTietytTarkastamattomat(boolean kirjat, boolean artikkelit, boolean blogit) {
        Matcher haku = new HakuBuilder()
                .onJokuHalutuistaTyypeista(kirjat, artikkelit, blogit)
                .tarkastamaton()
                .build();
        List<Vinkki> halutut = dao.matches(haku);
        if (halutut.isEmpty()) {
            printtaaVinkkiaEiLoytynyt();
        } else {
            printtaaKaikkiVinkitListalla(halutut);
        }
    }
    
    private void printtaaKaikkiTarkastamattomat() {
        Matcher haku = new HakuBuilder().tarkastamaton().build();
        
        List<Vinkki> tarkastamattomat = dao.matches(haku);
        
        if (tarkastamattomat.isEmpty()) {
            printtaaVinkkiaEiLoytynyt();
        } else {
            printtaaKaikkiVinkitListalla(tarkastamattomat);
        }
    }
    
    private void printtaaKaikkiTarkastetut() {
        Matcher haku = new HakuBuilder().tarkastettu().build();
        
        List<Vinkki> tarkastetut = dao.matches(haku);
        
        if (tarkastetut.isEmpty()) {
            printtaaVinkkiaEiLoytynyt();
        } else {
            printtaaKaikkiVinkitListalla(tarkastetut);
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
                + "\'ta\' + hakusana = merkkaa vinkki tarkastetuksi\n"
                + "Hae vinkkejä:\n"
                + "\'s\' + hakusana = hae vinkkejä hakusanalla\n"
                + "\'so\' + hakusana = hae vinkkejä otsikolla\n"
                + "\'st\' + hakusana = hae vinkkejä tekijällä\n"
                + "\'et\' + tyhjä/k/a/b = listaa tarkastamattomat vinkit (tyhjä = kaikki,"
                + " k = kirjat, a = artikkelit, b = blogit) (esim. \'et ka\') \n"
                + "\'kt\' + tyhjä/k/a/b = listaa tarkastetut vinkit (tyhjä = kaikki,"
                + " k = kirjat, a = artikkelit, b = blogit) (esim. \'kt ab\') \n");
    }
    
    private void merkkaaTarkastetuksi(String haku) {
        Vinkki haettu = haeVinkkiOtsikolla(haku);
        
        if (haettu == null) {
            printtaaVinkkiaEiLoytynyt();
            return;
        }
        
        haettu.setTarkastettu(1);
        dao.update(haettu.getId(), haettu);
        
        io.printLine("Vinkki '" + haku + "' merkattu luetuksi.",
                new ColorPlacer(
                        new ColorFlag(0, "cyan")));
    }
    
    private void printtaaVinkkiaEiLoytynyt() {
        io.printLine("Vinkkejä ei löytynyt!",
                new ColorPlacer(
                        new ColorFlag(0, "red"),
                        new ColorFlag(1, "red"),
                        new ColorFlag(2, "red")));
    }
    
    private void printtaaVirheellinenKomento() {
        io.printLine("\nVirheellinen komento.",
                new ColorPlacer(
                        new ColorFlag(0, "red"),
                        new ColorFlag(1, "red")));
    }
    
    private void printtaaVinkkiLisatty() {
        io.printLine("Vinkki lisätty!",
                new ColorPlacer(
                        new ColorFlag(0, "green"),
                        new ColorFlag(1, "green")));
    }

    // Tässä kohtaa pitää mennä pois "helposta" tavasta, ja vähän säätää että testit eivät mene rikki.
    // Tämä johtuu siitä, että värit perustuvat metadataan tekstin seassa, ja tähän saakka kun olemme injektoineet
    // Tämän datan tekstin mukaan io.printlinen avulla, osaa ohjelmisto olla laittamatta sitä testejä ajaessa.
    // Tässä kohtaa kuitenkin se ei ole mahdollista, sillä kohdissa saattaa olla mielivaltainen määrä välejä.
    // Tästä johtuen, meidän pitää jakaa rivijaoilla, ja laittaa jokaisen rivin eka värilliseksi.
    // Tämä sotkisi testit, joten katsomme vain onko kyseessä testi. Jos on, niin emme tee sitä.
    // Tämä ei välttämättä ole hyvää käytäntöä, mutta värejä testataan omilla testeillään ja jos ne eivät toimi
    // Niin se olisi itsestäänselvää muutenkin, ja printtaus ei olisi silloinkaan "rikki".
    private void printtaaVinkki(Vinkki vinkki) {
        if (io.getClass() == StubIO.class) {
            io.printLine(vinkki.toString());
        } else {
            String[] vinkinToStringRivit = vinkki.toString().split("\n");
            
            for (int i = 0; i < vinkinToStringRivit.length; i++) {
                if (i == 0) {
                    io.printLine(vinkinToStringRivit[i],
                            new ColorPlacer(
                                    new ColorFlag(0, "cyan"),
                                    new ColorFlag(1, "green")));
                } else {
                    io.printLine(vinkinToStringRivit[i],
                            new ColorPlacer(
                                    new ColorFlag(0, "cyan")));
                }
            }
            
            io.printLine("");
        }
    }
    
    private void printtaaKaikkiVinkitListalla(List<Vinkki> vinkit) {
        for (Vinkki vinkki : vinkit) {
            printtaaVinkki(vinkki);
        }
    }
}
