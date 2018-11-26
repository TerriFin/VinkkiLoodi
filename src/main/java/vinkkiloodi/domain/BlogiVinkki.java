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

    public Tyyppi getTyyppi() {
        return Tyyppi.Blog;
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
        return "Tyyppi: Blog\n"
                + super.toString()
                + urlToString();
    }
}
