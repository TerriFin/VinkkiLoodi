package vinkkiloodi.domain;

/**
 *
 * @author samisaukkonen
 */
public class BlogiVinkki extends Vinkki {

    private String url;

    public BlogiVinkki(String tekija, String nimi, String url, int tarkastettu) {
        super(tekija, nimi, tarkastettu, Tyyppi.Blog);
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String urlToString() {
        String s = "";
        if (!getUrl().equals("")) {
            s = "URL: " + getUrl() + "\n";
        }
        return s;
    }

    @Override
    public String toString() {
        return super.toString() 
                + urlToString();
    }
}
