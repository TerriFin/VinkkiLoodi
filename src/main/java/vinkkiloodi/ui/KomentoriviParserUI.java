package vinkkiloodi.ui;

import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.io.IO;
import vinkkiloodi.logic.ParserLogic;

/**
 *
 * @author samisaukkonen
 */
public class KomentoriviParserUI {

    private IO io;
    private ParserLogic logic;

    public KomentoriviParserUI(IO io, VinkkiDAO dao) {
        this.io = io;
        this.logic = new ParserLogic(io, dao);
    }

    private String NaytaOhjeetJaKysyKomennot() {
        io.printLine("\nTervetuloa käyttämään vinkkiloodi parseria!"
                + "\n"
                + "\napua - printtaa lisätietoa"
                + "\nx - Sulje parser\n");

        return io.nextLine();
    }

    private void printtaaApua() {
        io.printLine("----------------------------------------------------------------------------"
                + "\nPienillä/isoilla kirjaimilla ja ääkkösillä ei ole väliä."
                + "\nKomennot erotetaan toisistaan välilyönneillä, ja attribuutit laitetaan"
                + "\nomien sulkujen sisään viittausmerkkien sisään."
                + "\n"
                + "\nParseri pyrkii myös kysymään käyttäjältä korjauksia virheellisiin syötteisiin,"
                + "\nmutta aina se ei ole mahdollista ja käyttäjä joutuu tällöin syöttämään komentonsa uudestaan."
                + "\n"
                + "\nAloitus Komennot ovat seuraavat;"
                + "\n-Lisää"
                + "\n-Listaa"
                + "\n-Nopeahaku"
                + "\n-Tarkkahaku"
                + "\n-Päivitä"
                + "\n"
                + "\nLisää"
                + "\n----------------------------------------------------------------------------"
                + "\nLisää komento pyrkii lisäämään uuden vinkin systeemiin."
                + "\nSen jälkeen tarkennetaan vinkkityyppi jota halutaan lisätä (kirja, blogi, artikkeli),"
                + "\nJonka jälkeen annetaan suluissa ja lainausmerkeissä vinkin ominaisuudet seuraavassa järjestyksessä: "
                + "\n(\"Tekijä\" \"Vinkin nimi\" \"Vinkin oma ominaisuus\")"
                + "\n"
                + "\nEsimerkki komento voisi olla: Lisää Kirja (\"Eetu\" \"Eetun superkirja\" \"3124232\")"
                + "\nEsimerkissä kirjan ominaisuus on ISBN, joka on numerosarja. blogeissa se on URL ja artikkeleissa julkaisija."
                + "\n"
                + "\nListaa"
                + "\n----------------------------------------------------------------------------"
                + "\nListaa komento listaa kaikki säilötyt vinkit. Se ei tarvitse muuta."
                + "\n"
                + "\nNopeahaku"
                + "\n----------------------------------------------------------------------------"
                + "\nNopeahaku ottaa yhden hakusanan sellaisenaan (ilman sulkuja ja lainausmerkkejä) itsensä jälkeen."
                + "\nTätä muuttujaa käytetään hakusanana, jonka tulokset listataan."
                + "\n"
                + "\nEsimerkki komento voi olla: Nopeahaku eetu"
                + "\nEsimerkissä tulostetaan kaikki vinkit joiden tekijä tai nimi sisältää sanan \"eetu\""
                + "\n"
                + "\nTarkkahaku"
                + "\n----------------------------------------------------------------------------"
                + "\nTarkkahaku tarvitsee joko yhden muuttujan jossa määritellään mitä haetaan tarkasti,"
                + "\nja joissain hauissa se tarvitsee vielä kolmannen hakusanan"
                + "\n"
                + "\nMahdolliset tarkat haut ovat:"
                + "\n-Kirjoja"
                + "\n-Blogeja"
                + "\n-Artikkeleita"
                + "\n-Tekijä"
                + "\n-Nimi"
                + "\n-Tarkastamattomat"
                + "\n-Tarkastetut"
                + "\n"
                + "\nKaikki muut paitsi -Tekijä ja -Nimi haku toimivat sellaisenaan, ja listaavat kukin oman joukkonsa vinkkejä."
                + "\n-Tekijä ja -Nimi hauissa pitää vielä antaa kolmanneksi hakusanaksi jokin hakusana jolla etsitään vinkkejä,"
                + "\nsamalla tavalla kuin nopeassa haussa."
                + "\n"
                + "\nEsimerkki komento voisi olla: Tarkkahaku kirjoja"
                + "\nTämä esimerkkihaku tulostaa kaikki kirjat."
                + "\n"
                + "\nToinen esimerkki komento voisi olla: Tarkkahaku Nimi Fire"
                + "\nTämä haku etsii kaikki vinkit joiden nimessä on sana \"Fire\""
                + "\n"
                + "\nPäivitä"
                + "\n----------------------------------------------------------------------------"
                + "\nPäivitä pyrkii päivittämään vinkin. Sille annetaan tokaksi komennoksi hakusana,"
                + "\njolla pyritään etsimään vinkkiä sen nimen kautta"
                + "\n(paras tapa lienee listata vinkit ja copy/pastea halutun vinkin nimi tähän kohtaan)."
                + "\nKolmanneksi muuttujaksi sille annetaan uudet ominaisuudet sulkujen ja lainausmerkkien sisällä"
                + "\nsamalla tavalla kuin vinkin lisäyksessä. Tässä annetaan vielä yksi ylimääräinen muuttuja sulkujen sisällä,"
                + "\njoka kuvaa onko kyseistä vinkkiä tarkistettu. Voit antaa joko arvon \"1\"/\"0\", \"true\"/\"false\" tai \"tarkistettu\"/\"tarkastamaton\"."
                + "\n"
                + "\nJos tämä komento ei löydä haluttua vinkkiä, komennon suoritus keskeytyy."
                + "\n"
                + "\nEsimerkkikomento voisi olla: Päivitä Eetun blogi(\"uusiTekijänNimi\" \"uusiBloginNimi\" \"uusiURL\" \"false\")"
                + "\nTässä esimerkki komennossa etsitään vinkki jonka nimi on \"Eetun blogi\", tämän jälkeen siihen päivitetään annetut arvot."
                + "\n");
    }

    public void start() {
        while (true) {
            String komento = NaytaOhjeetJaKysyKomennot();

            if (komento.toLowerCase().trim().equals("x")) {
                break;
            } else if (komento.toLowerCase().trim().equals("apua")) {
                printtaaApua();
            } else {
                try {
                    logic.kasitteleKomennot(komento);
                } catch (Exception e) {
                    io.printLine("\nKomento oli väärässä muodossa!");
                }
            }
        }
    }
}
