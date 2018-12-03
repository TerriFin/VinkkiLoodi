package vinkkiloodi.logic;

import java.util.ArrayList;
import java.util.List;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.domain.ArtikkeliVinkki;
import vinkkiloodi.domain.BlogiVinkki;
import vinkkiloodi.domain.KirjaVinkki;
import vinkkiloodi.domain.Vinkki;
import vinkkiloodi.io.IO;

/**
 *
 * @author samisaukkonen
 */
public class ParserLogic {

    private IO io;
    private VinkkiDAO dao;
    private List<String> komennot;
    private int kohta;

    public ParserLogic(IO io, VinkkiDAO dao) {
        this.io = io;
        this.dao = dao;
    }

    private void lisaaKomentoihinUudetKomennot(String uudetKomennot) {
        komennot = new ArrayList<>();
        kohta = 0;

        String[] komennotLeikattuina = uudetKomennot.split(" ");
        for (int i = 0; i < komennotLeikattuina.length; i++) {
            if (komennotLeikattuina[i].charAt(0) == '(') {
                lisaaSulkujenSisalto(komennotLeikattuina, i);
                break;
            } else {
                komennot.add(komennotLeikattuina[i].toLowerCase());
            }
        }
    }

    private void lisaaSulkujenSisalto(String[] komennotLeikattuina, int sulkuAlku) {
        String sulkujenSisalto = "";
        for (int i = sulkuAlku; i < komennotLeikattuina.length; i++) {
            if (komennotLeikattuina[i].charAt(komennotLeikattuina[i].length() - 1) == ')') {
                sulkujenSisalto += komennotLeikattuina[i];
            } else {
                sulkujenSisalto += komennotLeikattuina[i] + " ";
            }
        }

        komennot.add(sulkujenSisalto);
    }

    private int annaAttribuuttiKomennonLainausmerkkienLkm(String komento) {
        int lainausMerkkiLkm = 0;
        for (int i = 0; i < komento.length(); i++) {
            if (komento.charAt(i) == '"') {
                lainausMerkkiLkm++;
            }
        }

        return lainausMerkkiLkm;
    }

    private String[] annaAttribuutitAtribuuttiKomennosta(String komento, int lainausMerkkiLkm) {
        String[] attribuutit = new String[lainausMerkkiLkm / 2];
        int attribuuttiOsoitin = 0;
        String lisattava = "";

        boolean lainauksenSisalla = false;
        for (int i = 0; i < komento.length(); i++) {
            if (lainauksenSisalla) {
                if (komento.charAt(i) == '"') {
                    lainauksenSisalla = false;
                    attribuutit[attribuuttiOsoitin++] = lisattava;
                } else {
                    lisattava += komento.charAt(i);
                }
            } else {
                if (komento.charAt(i) == '"') {
                    lainauksenSisalla = true;
                    lisattava = "";
                }
            }
        }

        return attribuutit;
    }

    private String getSeuraavaKomento() {
        return komennot.get(kohta++);
    }

    private String pyydaKomentoUudestaan(String komento, String mahdKomennot) {
        io.printLine("\nKomento '" + komento + "' ei vastaa mitään validia komentoa,"
                + "\nvoitko uudelleenkirjoittaa komennon ja tarkistaa oikeinkirjoituksen?"
                + "\n"
                + "\nmahdolliset komennot ovat: " + mahdKomennot
                + "\n");

        return io.nextLine().toLowerCase();
    }

    public void kasitteleKomennot(String uudetKomennot) {
        lisaaKomentoihinUudetKomennot(uudetKomennot);
        String komento = getSeuraavaKomento();

        while (true) {
            if (komento.equals("lisää") || komento.equals("lisaa")) {
                kasitteleLisays();
                break;
            } else if (komento.equals("listaa")) {
                printtaaKaikkiVinkit(dao.getAll());
                break;
            } else if (komento.equals("nhaku") || komento.equals("nopeahaku")) {
                kasitteleNopeaHaku();
                break;
            } else if (komento.equals("thaku") || komento.equals("tarkkahaku")) {
                kasitteleTarkkaHaku();
                break;
            } else if (komento.equals("päivitä") || komento.equals("paivita")) {
                kasittelePaivitys();
                break;
            } else {
                komento = pyydaKomentoUudestaan(komento, "lisää, listaa, nopeahaku, tarkkahaku, päivitä");
            }
        }
    }

    private void kasitteleLisays() {
        String komento = getSeuraavaKomento();

        while (true) {
            if (komento.equals("k") || komento.equals("kirja")) {
                kasitteleKirjanLisays();
                break;
            } else if (komento.equals("b") || komento.equals("blogi")) {
                kasitteleBloginLisays();
                break;
            } else if (komento.equals("a") || komento.equals("artikkeli")) {
                kasitteleArtikkelinLisays();
                break;
            } else {
                komento = pyydaKomentoUudestaan(komento, "kirja, blogi, artikkeli");
            }
        }
    }

    private void kasitteleKirjanLisays() {
        String komento = getSeuraavaKomento();

        int lainausMerkkiLkm = annaAttribuuttiKomennonLainausmerkkienLkm(komento);
        if (lainausMerkkiLkm % 2 == 0) {
            String[] attribuutit = annaAttribuutitAtribuuttiKomennosta(komento, lainausMerkkiLkm);

            dao.add(new KirjaVinkki(attribuutit[0], attribuutit[1], 0, attribuutit[2]));

            io.printLine("\nUusi kirja '" + attribuutit[1] + "' lisätty!");
        } else {
            System.out.println("KYSY OIKEITA KIRJAN TIETOJA");
        }
    }

    private void kasitteleBloginLisays() {
        String komento = getSeuraavaKomento();

        int lainausMerkkiLkm = annaAttribuuttiKomennonLainausmerkkienLkm(komento);
        if (lainausMerkkiLkm % 2 == 0) {
            String[] attribuutit = annaAttribuutitAtribuuttiKomennosta(komento, lainausMerkkiLkm);

            dao.add(new BlogiVinkki(attribuutit[0], attribuutit[1], attribuutit[2], 0));

            io.printLine("\nUusi blogi '" + attribuutit[1] + "' lisätty!");
        } else {
            System.out.println("KYSY OIKEITA BLOGIN TIETOJA");
        }
    }

    private void kasitteleArtikkelinLisays() {
        String komento = getSeuraavaKomento();

        int lainausMerkkiLkm = annaAttribuuttiKomennonLainausmerkkienLkm(komento);
        if (lainausMerkkiLkm % 2 == 0) {
            String[] attribuutit = annaAttribuutitAtribuuttiKomennosta(komento, lainausMerkkiLkm);

            dao.add(new ArtikkeliVinkki(attribuutit[0], attribuutit[1], attribuutit[2], 0));

            io.printLine("\nUusi artikkeli '" + attribuutit[1] + "' lisätty!");
        } else {
            System.out.println("KYSY OIKEITA ARTIKKELIN TIETOJA");
        }
    }

    private void kasitteleNopeaHaku() {
        String komento = getSeuraavaKomento();
        List<Vinkki> loydetytVinkit = dao.megaHaku(komento);

        printtaaKaikkiVinkit(loydetytVinkit);
    }

    private void kasitteleTarkkaHaku() {
        String komento = getSeuraavaKomento();

        while (true) {
            if (komento.equals("kirjoja") || komento.equals("kirja") || komento.equals("k")) {
                printtaaKaikkiVinkit(dao.getKaikkiKirjat());
                break;
            } else if (komento.equals("blogeja") || komento.equals("blogi") || komento.equals("b")) {
                printtaaKaikkiVinkit(dao.getKaikkiBlogit());
                break;
            } else if (komento.equals("artikkeleita") || komento.equals("artikkeli") || komento.equals("a")) {
                printtaaKaikkiVinkit(dao.getKaikkiArtikkelit());
                break;
            } else if (komento.equals("tekija") || komento.equals("tekijan") || komento.equals("tekijä") || komento.equals("tekijän")) {
                kasitteleTekijaHaku();
                break;
            } else if (komento.equals("nimi")) {
                kasitteleNimiHaku();
                break;
            } else if (komento.equals("tarkastamattomia") || komento.equals("tarkastamattomat")) {
                printtaaKaikkiVinkit(dao.getKaikkiTarkastamattomat());
                break;
            } else if (komento.equals("tarkastettuja") || komento.equals("tarkastetut")) {
                printtaaKaikkiVinkit(dao.getKaikkitarkastetut());
                break;
            } else {
                komento = pyydaKomentoUudestaan(komento, "kirjoja, blogeja, artikkeleita, tekijä, nimi, tarkastamattomat, tarkastetut");
            }
        }
    }

    private void kasitteleTekijaHaku() {
        String komento = getSeuraavaKomento();

        printtaaKaikkiVinkit(dao.getByTekija(komento));
    }

    private void kasitteleNimiHaku() {
        String komento = getSeuraavaKomento();

        printtaaKaikkiVinkit(dao.getByNimi(komento));
    }

    private void kasittelePaivitys() {
        String komento = getSeuraavaKomento();

        Vinkki haettu = haeVinkkiOtsikolla(komento);

        if (haettu == null) {
            io.printLine("Ei löytynyt vinkkiä hakusanalla \"" + komento + "\".");
            return;
        }
        
        System.out.println("Löytyi");
    }

    private Vinkki haeVinkkiOtsikolla(String haku) {
        haku = haku.toLowerCase().trim();

        List<Vinkki> vinkit = dao.getAll();
        for (int i = 0; i < vinkit.size(); i++) {
            Vinkki v = vinkit.get(i);
            if (v.getNimi().toLowerCase().trim().equals(haku)) {
                return v;
            }
        }
        return null;
    }

    private void printtaaKaikkiVinkit(List<Vinkki> vinkit) {
        vinkit.forEach((vinkki) -> {
            io.printLine("\n" + vinkki);
        });
    }
}
