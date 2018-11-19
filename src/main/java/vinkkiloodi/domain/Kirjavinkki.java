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
public class Kirjavinkki extends Vinkki {

    private int id;
    private String kirjoittaja;
    private String otsikko;
    private String isbn;
    private int luettu;
    List<String> tagit;
    List<String> esitietokurssit;
    List<String> relatedCourses;

    public Kirjavinkki(String kirjoittaja, String otsikko, int luettu, String isbn) {
        super(kirjoittaja, otsikko, luettu);
        this.isbn = isbn;
        this.esitietokurssit = new ArrayList<>();
        this.relatedCourses = new ArrayList<>();
    }
    
    public Kirjavinkki(String kirjoittaja, String otsikko) {
        super(kirjoittaja, otsikko, 0);
        this.isbn = "undef";
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

    public int getLuettu() {
        return luettu;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<String> getTagit() {
        return tagit;
    }

    public List<String> getEsitietokurssit() {
        return esitietokurssit;
    }

    public List<String> getRelatedCourses() {
        return relatedCourses;
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

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTagit(List<String> tagit) {
        this.tagit = tagit;
    }

    public void setEsitietokurssit(List<String> esitietokurssit) {
        this.esitietokurssit = esitietokurssit;
    }

    public void setRelatedCourses(List<String> relatedCourses) {
        this.relatedCourses = relatedCourses;
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

    @Override
    public String toString() {
        return super.toString();
    }
}
