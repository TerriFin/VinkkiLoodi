/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Niko
 */
public class Kirjavinkki {

    private int id;
    private String kirjoittaja;
    private String otsikko;
    private String isbn;
    private int luettu;
    List<String> tagit;
    List<String> esitietokurssit;
    List<String> relatedCourses;

    public Kirjavinkki(int id, String kirjoittaja, String otsikko, String isbn, int luettu) {
        this.id = id;
        this.kirjoittaja = kirjoittaja;
        this.otsikko = otsikko;
        this.isbn = isbn;
        this.luettu = luettu;
        this.tagit = new ArrayList<>();
        this.esitietokurssit = new ArrayList<>();
        this.relatedCourses = new ArrayList<>();
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

    public String getIsbn() {
        return isbn;
    }

    public List<String> getEsitietokurssit() {
        return esitietokurssit;
    }

    public List<String> getRelatedCourses() {
        return relatedCourses;
    }

    public void addEsitietokurssi(String nimi) {
        this.esitietokurssit.add(nimi);
    }

    public void addRealtedCourse(String nimi) {
        this.relatedCourses.add(nimi);
    }
    
    public void addTag(String tag) {
        this.relatedCourses.add(tag);
    }
}
