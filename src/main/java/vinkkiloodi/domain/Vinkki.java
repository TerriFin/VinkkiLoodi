package vinkkiloodi.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Vinkki {

    private int id;
    private String tekija;
    private String nimi;
    private int tarkastettu;
    List<String> tagit;
    List<String> esitietokurssit;
    List<String> liittyvatKurssit;

    public Vinkki(String tekija, String nimi, int tarkastettu) {
        this.tekija = tekija;
        this.nimi = nimi;
        this.tarkastettu = tarkastettu;
        this.tagit = new ArrayList<>();
        this.esitietokurssit = new ArrayList<>();
        this.liittyvatKurssit = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Tyyppi getTyyppi() {
        return Tyyppi.None;
    }

    public String getTekija() {
        return tekija;
    }

    public String getNimi() {
        return nimi;
    }

    public int getTarkastettu() {
        return tarkastettu;
    }

    public List<String> getTagit() {
        return tagit;
    }

    public List<String> getEsitietokurssit() {
        return esitietokurssit;
    }

    public List<String> getLiittyvatKurssit() {
        return liittyvatKurssit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setTekija(String tekija) {
        this.tekija = tekija;
    }

    public void setTarkastettu(int tarkastettu) {
        this.tarkastettu = tarkastettu;
    }

    public void setTagit(List<String> tagit) {
        this.tagit = tagit;
    }

    public void setEsitietokurssit(List<String> esitietokurssit) {
        this.esitietokurssit = esitietokurssit;
    }

    public void setLiittyvatKurssit(List<String> liittyvatKurssit) {
        this.liittyvatKurssit = liittyvatKurssit;
    }

    public void addEsitietokurssi(String nimi) {
        this.esitietokurssit.add(nimi);
    }

    public void addLiittyvakurssi(String nimi) {
        this.liittyvatKurssit.add(nimi);
    }

    public void addTag(String tag) {
        this.tagit.add(tag);
    }

    private String listaToString(List<String> lista) {
        return lista.stream().collect(Collectors.joining(", "));
    }

    private String tekijaToString() {
        String s = "";
        if (!getTekija().equals("")) {
            s = "Tekijä: " + getTekija() + "\n";
        }
        return s;
    }

    private String nimiToString() {
        String s = "";
        if (!getNimi().equals("")) {
            s = "Nimi: " + getNimi() + "\n";
        }
        return s;
    }

    private String tarkastettuToString() {
        if (getTarkastettu() == 0) {
            return "Tarkastettu: Ei\n";
        } else {
            return "Tarkastettu: Kyllä\n";
        }
    }

    private String tagitToString() {
        String s = "";
        if (!getTagit().isEmpty()) {
            s = "Tagit: " + listaToString(getTagit()) + "\n";
        }
        return s;
    }

    private String esitietokurssitToString() {
        String s = "";
        if (!getEsitietokurssit().isEmpty()) {
            s = "Esitietokurssit: " + listaToString(getEsitietokurssit()) + "\n";
        }
        return s;
    }

    private String liittyvatKurssitToString() {
        String s = "";
        if (!getLiittyvatKurssit().isEmpty()) {
            s = "Liittyvät kurssit: " + listaToString(getLiittyvatKurssit()) + "\n";
        }
        return s;
    }

    @Override
    public String toString() {
        return tekijaToString()
                + nimiToString()
                + tarkastettuToString()
                + tagitToString()
                + esitietokurssitToString()
                + liittyvatKurssitToString();
    }
}
