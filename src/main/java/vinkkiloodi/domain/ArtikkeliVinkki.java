package vinkkiloodi.domain;

/**
 *
 * @author samisaukkonen
 */
public class ArtikkeliVinkki extends Vinkki {
    
    public ArtikkeliVinkki(String tekija, String nimi, int tarkastettu) {
        super(tekija, nimi, tarkastettu);
    }
    
    public Tyyppi getTyyppi() {
        return Tyyppi.Artikkeli;
    }
}
