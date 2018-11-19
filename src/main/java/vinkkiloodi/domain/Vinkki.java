package vinkkiloodi.domain;

import java.util.ArrayList;
import java.util.List;


public abstract class Vinkki {
    private int id;
    private String kirjoittaja;
    private String otsikko;
    private int luettu;
    List<String> tagit;

    public Vinkki(String kirjoittaja, String otsikko, int luettu) {
        this.kirjoittaja = kirjoittaja;
        this.otsikko = otsikko;
        this.luettu = luettu;
        this.tagit = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        String luettuTeksti = "";
        if (luettu == 0) {
            luettuTeksti = "on luettu";
        } else {
            luettuTeksti = "ei ole luettu";
        }
        return "\"" + otsikko + "\", " + kirjoittaja + ", " + luettuTeksti + "tagit: " + tagit;
    }
}
