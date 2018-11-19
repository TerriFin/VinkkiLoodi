
package vinkkiloodi.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Niko
 */
public class Kirjavinkki extends Vinkki {

    private String isbn;

    public Kirjavinkki(String kirjoittaja, String otsikko, int luettu, String isbn) {
        super(kirjoittaja, otsikko, luettu);
        this.isbn = isbn;
    }
    
    public Kirjavinkki(String kirjoittaja, String otsikko) {
        super(kirjoittaja, otsikko, 0);
        this.isbn = "";
    }

    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return super.toString() + " ISBN: " + getIsbn();
    }
}
