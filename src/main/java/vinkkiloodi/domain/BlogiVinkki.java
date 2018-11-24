package vinkkiloodi.domain;

/**
 *
 * @author samisaukkonen
 */
public class BlogiVinkki extends Vinkki {
    
    private String url;
    
    public BlogiVinkki(String tekija, String nimi, String url, int tarkastettu) {
        super(tekija, nimi, tarkastettu);
        this.url = url;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public Tyyppi getTyyppi() {
        return Tyyppi.Blog;
    }
}
