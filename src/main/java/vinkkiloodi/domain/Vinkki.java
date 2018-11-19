package vinkkiloodi.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Vinkki {

    private int id;
    private String kirjoittaja;
    private String otsikko;
    private int luettu;
    List<String> tagit;
    List<String> esitietokurssit;
    List<String> liittyvatKurssit;

    public Vinkki(String kirjoittaja, String otsikko, int luettu) {
        this.kirjoittaja = kirjoittaja;
        this.otsikko = otsikko;
        this.luettu = luettu;
        this.tagit = new ArrayList<>();
        this.esitietokurssit = new ArrayList<>();
        this.liittyvatKurssit = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public int getLuettu() {
        return luettu;
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

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setKirjoittaja(String kirjoittaja) {
        this.kirjoittaja = kirjoittaja;
    }

    public void setLuettu(int luettu) {
        this.luettu = luettu;
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

    public void addRealtedCourse(String nimi) {
        this.liittyvatKurssit.add(nimi);
    }

    public void addTag(String tag) {
        this.liittyvatKurssit.add(tag);
    }

    @Override
    public String toString() {
        String luettuTeksti = "";
        if (luettu == 0) {
            luettuTeksti = "on luettu";
        } else {
            luettuTeksti = "ei ole luettu";
        }
        return "\"" + otsikko + "\", " + kirjoittaja + ", " + luettuTeksti + ", tagit: " + tagit;
    }
}
