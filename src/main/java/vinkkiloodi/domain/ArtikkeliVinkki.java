package vinkkiloodi.domain;

/**
 *
 * @author samisaukkonen
 */
public class ArtikkeliVinkki extends Vinkki {

    private String julkaisija;

    public ArtikkeliVinkki(String tekija, String nimi, String julkaisija, int tarkastettu) {
        super(tekija, nimi, tarkastettu);

        this.julkaisija = julkaisija;
    }

    public Tyyppi getTyyppi() {
        return Tyyppi.Artikkeli;
    }

    public String getJulkaisija() {
        return this.julkaisija;
    }

    public void setJulkaisija(String julkaisija) {
        this.julkaisija = julkaisija;
    }

    private String julkaisijaToString() {
        String s = "";
        if (!getJulkaisija().equals("")) {
            s = "Julkaisija: " + getJulkaisija() + "\n";
        }
        return s;
    }

    @Override
    public String toString() {
        return "Tyyppi: Artikkeli\n"
                + super.toString()
                + julkaisijaToString();
    }
}
