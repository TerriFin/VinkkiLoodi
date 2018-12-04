package vinkkiloodi.logic;

import java.util.ArrayList;
import java.util.List;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.domain.ArtikkeliVinkki;
import vinkkiloodi.domain.BlogiVinkki;
import vinkkiloodi.domain.KirjaVinkki;
import vinkkiloodi.domain.Tyyppi;
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

    private boolean onkoSulkujaSamaMaara(String input) {
        int vasenSulkuja = 0;
        int oikeaSulkuja = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                vasenSulkuja++;
            } else if (input.charAt(i) == ')') {
                oikeaSulkuja++;
            }
        }

        return vasenSulkuja == oikeaSulkuja;
    }

    private boolean lisaaKomentoihinUudetKomennot(String uudetKomennot) {
        komennot = new ArrayList<>();
        kohta = 0;

        if (!onkoSulkujaSamaMaara(uudetKomennot)) {
            return false;
        }

        String[] komennotLeikattuina = uudetKomennot.split(" ");
        for (int i = 0; i < komennotLeikattuina.length; i++) {
            if (komennotLeikattuina[i].charAt(0) == '(') {
                if (!lisaaSulkujenSisalto(komennotLeikattuina, i)) {
                    return false;
                }

                break;
            } else {
                komennot.add(komennotLeikattuina[i].toLowerCase());
            }
        }

        return true;
    }

    private boolean lisaaSulkujenSisalto(String[] komennotLeikattuina, int sulkuAlku) {
        try {
            boolean loydettyLoppu = false;
            String sulkujenSisalto = "";
            for (int i = sulkuAlku; i < komennotLeikattuina.length; i++) {
                if (komennotLeikattuina[i].charAt(komennotLeikattuina[i].length() - 1) == ')') {
                    sulkujenSisalto += komennotLeikattuina[i];
                    loydettyLoppu = true;
                } else {
                    sulkujenSisalto += komennotLeikattuina[i] + " ";
                }
            }

            komennot.add(sulkujenSisalto);
            return loydettyLoppu;
        } catch (Exception e) {
            return false;
        }
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

    private boolean eiOleSulullaAlkava(String komento) {
        return komento.charAt(0) != '(';
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

    public void printtaaSulkuVaroitus() {
        io.printLine("\nAntamassasi komennossa sulkuja ei käytetä oikein."
                + "\nTarkista että suljet sulut ja että ylipäätänsä käytät niitä."
                + "\nKorjaa virhe ja yritä uudelleen.");
    }

    public String pyydäUusiaAttribuutteja(String tarvitutAttribuutit) {
        io.printLine("Antamassasi attribuutit ovat joko väärässä muodossa tai lainausmerkit ovat väärin"
                + "\n"
                + "\nLaita uudet attribuutit muodossa: " + tarvitutAttribuutit
                + "\n");

        return io.nextLine();
    }

    public void kasitteleKomennot(String uudetKomennot) {
        if (!lisaaKomentoihinUudetKomennot(uudetKomennot)) {
            printtaaSulkuVaroitus();
            return;
        }

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

        while (true) {
            int lainausMerkkiLkm = annaAttribuuttiKomennonLainausmerkkienLkm(komento);
            if (lainausMerkkiLkm == 6) {
                String[] attribuutit = annaAttribuutitAtribuuttiKomennosta(komento, lainausMerkkiLkm);

                dao.add(new KirjaVinkki(attribuutit[0], attribuutit[1], 0, attribuutit[2]));

                io.printLine("\nUusi kirja '" + attribuutit[1] + "' lisätty!");
                break;
            } else {
                komento = pyydäUusiaAttribuutteja("(\"Tekijä\" \"Kirjan nimi\" \"ISBN\")");
            }
        }
    }

    private void kasitteleBloginLisays() {
        String komento = getSeuraavaKomento();

        while (true) {
            int lainausMerkkiLkm = annaAttribuuttiKomennonLainausmerkkienLkm(komento);
            if (lainausMerkkiLkm == 6) {
                String[] attribuutit = annaAttribuutitAtribuuttiKomennosta(komento, lainausMerkkiLkm);

                dao.add(new BlogiVinkki(attribuutit[0], attribuutit[1], attribuutit[2], 0));

                io.printLine("\nUusi blogi '" + attribuutit[1] + "' lisätty!");
                break;
            } else {
                komento = pyydäUusiaAttribuutteja("(\"Tekijä\" \"Blogin nimi\" \"URL\")");
            }
        }
    }

    private void kasitteleArtikkelinLisays() {
        String komento = getSeuraavaKomento();

        while (true) {
            int lainausMerkkiLkm = annaAttribuuttiKomennonLainausmerkkienLkm(komento);
            if (lainausMerkkiLkm == 6) {
                String[] attribuutit = annaAttribuutitAtribuuttiKomennosta(komento, lainausMerkkiLkm);

                dao.add(new ArtikkeliVinkki(attribuutit[0], attribuutit[1], attribuutit[2], 0));

                io.printLine("\nUusi artikkeli '" + attribuutit[1] + "' lisätty!");
                break;
            } else {
                komento = pyydäUusiaAttribuutteja("(\"Tekijä\" \"Blogin nimi\" \"Julkaisija\")");
            }
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
        String hakusana = "";

        while (eiOleSulullaAlkava(komento)) {
            hakusana += komento;
            komento = getSeuraavaKomento();
        }

        Vinkki haettu = haeVinkkiOtsikolla(hakusana);

        if (haettu == null) {
            io.printLine("Ei löytynyt vinkkiä hakusanalla \"" + hakusana + "\".");
            return;
        }

        if (haettu.getTyyppi() == Tyyppi.Kirja) {
            paivitaKirja(komento, (KirjaVinkki) haettu);
        } else if (haettu.getTyyppi() == Tyyppi.Blog) {
            paivitaBlogi(komento, (BlogiVinkki) haettu);
        } else {
            paivitaArtikkeli(komento, (ArtikkeliVinkki) haettu);
        }
    }

    private void paivitaKirja(String komento, KirjaVinkki paivitettava) {
        while (true) {
            int lainausMerkkiLkm = annaAttribuuttiKomennonLainausmerkkienLkm(komento);
            if (lainausMerkkiLkm == 8) {
                String[] attribuutit = annaAttribuutitAtribuuttiKomennosta(komento, lainausMerkkiLkm);

                paivitettava.setTekija(attribuutit[0]);
                paivitettava.setNimi(attribuutit[1]);
                paivitettava.setISBN(attribuutit[2]);
                paivitettava.setTarkastettu(tarkistaOnkoSyoteTarkastanut(attribuutit[3]));

                dao.update(paivitettava.getId(), paivitettava);

                io.printLine("Kirja " + attribuutit[1] + " onnistuneesti päivitetty!"
                        + "\n");
                break;
            } else {
                komento = pyydäUusiaAttribuutteja("(\"Tekijä\" \"Nimi\" \"ISBN\" \"Tarkistettu/Tarkastamaton\")");
            }
        }
    }

    private void paivitaBlogi(String komento, BlogiVinkki paivitettava) {
        while (true) {
            int lainausMerkkiLkm = annaAttribuuttiKomennonLainausmerkkienLkm(komento);
            if (lainausMerkkiLkm == 8) {
                String[] attribuutit = annaAttribuutitAtribuuttiKomennosta(komento, lainausMerkkiLkm);

                paivitettava.setTekija(attribuutit[0]);
                paivitettava.setNimi(attribuutit[1]);
                paivitettava.setUrl(attribuutit[2]);
                paivitettava.setTarkastettu(tarkistaOnkoSyoteTarkastanut(attribuutit[3]));

                dao.update(paivitettava.getId(), paivitettava);

                io.printLine("Blogi " + attribuutit[1] + " onnistuneesti päivitetty!"
                        + "\n");
                break;
            } else {
                komento = pyydäUusiaAttribuutteja("(\"Tekijä\" \"Nimi\" \"URL\" \"Tarkistettu/Tarkastamaton\")");
            }
        }
    }

    private void paivitaArtikkeli(String komento, ArtikkeliVinkki paivitettava) {
        while (true) {
            int lainausMerkkiLkm = annaAttribuuttiKomennonLainausmerkkienLkm(komento);
            if (lainausMerkkiLkm == 8) {
                String[] attribuutit = annaAttribuutitAtribuuttiKomennosta(komento, lainausMerkkiLkm);

                paivitettava.setTekija(attribuutit[0]);
                paivitettava.setNimi(attribuutit[1]);
                paivitettava.setJulkaisija(attribuutit[2]);
                paivitettava.setTarkastettu(tarkistaOnkoSyoteTarkastanut(attribuutit[3]));

                dao.update(paivitettava.getId(), paivitettava);

                io.printLine("Artikkeli " + attribuutit[1] + " onnistuneesti päivitetty!"
                        + "\n");
                break;
            } else {
                komento = pyydäUusiaAttribuutteja("(\"Tekijä\" \"Nimi\" \"Julkaisija\" \"Tarkistettu/Tarkastamaton\")");
            }
        }
    }

    private Vinkki haeVinkkiOtsikolla(String haku) {
        haku = haku.toLowerCase().trim();

        List<Vinkki> vinkit = dao.getAll();
        for (int i = 0; i < vinkit.size(); i++) {
            Vinkki v = vinkit.get(i);
            String vinkkiNimi = poistaValilyonnit(v.getNimi().toLowerCase().trim());
            if (vinkkiNimi.equals(haku)) {
                return v;
            }
        }
        return null;
    }

    private String poistaValilyonnit(String syote) {
        String palaute = "";

        for (int i = 0; i < syote.length(); i++) {
            if (syote.charAt(i) != ' ') {
                palaute += syote.charAt(i);
            }
        }

        return palaute;
    }

    private int tarkistaOnkoSyoteTarkastanut(String syote) {
        syote = syote.toLowerCase().trim();

        if (syote.equals("1") || syote.equals("true") || syote.equals("tarkistettu")) {
            return 1;
        } else if (syote.equals("0") || syote.equals("false") || syote.equals("tarkastamaton") || syote.equals("eitarkastettu")) {
            return 0;
        }

        return -1;
    }

    private void printtaaKaikkiVinkit(List<Vinkki> vinkit) {
        vinkit.forEach((vinkki) -> {
            io.printLine("\n" + vinkki);
        });
    }
}
