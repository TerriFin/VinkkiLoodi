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
    
    public Tyyppi getTyyppi() {
        return Tyyppi.Kirja;
    }
    
    public KirjaVinkki(String tekija, String nimi) {
        super(tekija, nimi, 0);
        this.ISBN = "";
    }
    
    public String getISBN() {
        return ISBN;
    }
    
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    private String isbnToString() {
        String s = "";
        if (!getISBN().isEmpty()) {
            s = "ISBN: " + getISBN() + "\n";
        }
        return s;
    }
    
    @Override
    public String toString() {
        return "Tyyppi: Kirja\n"
                + super.toString()
                + isbnToString();
    }
}
