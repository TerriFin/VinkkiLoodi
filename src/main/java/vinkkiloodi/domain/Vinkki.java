package vinkkiloodi.domain;

import java.util.ArrayList;
import java.util.List;

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

    public void addRelatedCourse(String nimi) {
        this.liittyvatKurssit.add(nimi);
    }

    public void addTag(String tag) {
        this.liittyvatKurssit.add(tag);
    }

    @Override
    public String toString() {
        String tarkistettuVinkki = "";
        if (tarkastettu == 0) {
            tarkistettuVinkki = "ei ole tarkistettu";
        } else {
            tarkistettuVinkki = "on tarkistettu";
        }
        return "'" + nimi + "', " + tekija + ", " + tarkistettuVinkki + ", tagit: " + tagit;
    }
}
