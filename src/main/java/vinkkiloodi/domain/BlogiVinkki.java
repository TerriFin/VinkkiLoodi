package vinkkiloodi.domain;

/**
 *
 * @author samisaukkonen
 */
public class BlogiVinkki extends Vinkki {
    
    public BlogiVinkki(String tekija, String nimi, int tarkastettu) {
        super(tekija, nimi, tarkastettu);
    }
    
    public Tyyppi getTyyppi() {
        return Tyyppi.Blog;
    }
}
