package vinkkiloodi.domain;

/**
 *
 * @author Niko
 */
public class KirjaVinkki extends Vinkki {

    private String ISBN;

    public KirjaVinkki(String tekija, String nimi, int tarkastettu, String ISBN) {
        super(tekija, nimi, tarkastettu);
        this.ISBN = ISBN;
    }
    
    public KirjaVinkki(String tekija, String nimi) {
        super(tekija, nimi, 0);
        this.ISBN = "";
    }
    
    public Tyyppi getTyyppi() {
        return Tyyppi.Kirja;
    }
    
    public String getISBN() {
        return ISBN;
    }
    
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return super.toString() + " ISBN: " + getISBN();
    }
}
