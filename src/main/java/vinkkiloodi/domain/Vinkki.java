package vinkkiloodi.domain;


public interface Vinkki {

    void addTag(String tag);

    int getId();

    String getKirjoittaja();

    String getOtsikko();
    
}
