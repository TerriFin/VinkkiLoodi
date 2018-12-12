/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import vinkkiloodi.domain.Tyyppi;
import vinkkiloodi.domain.Vinkki;

/**
 *
 * @author Niko
 */
public class OnJotainTyypeista implements Matcher {

    private boolean sailytaKirjat, sailytaArtikkelit, sailytaBlogit;

    public OnJotainTyypeista(boolean sailytaKirjat, boolean sailytaArtikkelit, boolean sailytaBlogit) {
        this.sailytaKirjat = sailytaKirjat;
        this.sailytaArtikkelit = sailytaArtikkelit;
        this.sailytaBlogit = sailytaBlogit;
    }

    @Override
    public boolean matches(Vinkki v) {
        if (v.getTyyppi() == Tyyppi.Kirja && sailytaKirjat) {
            return true;
        } else if (v.getTyyppi() == Tyyppi.Artikkeli && sailytaArtikkelit) {
            return true;
        } else if (v.getTyyppi() == Tyyppi.Blog && sailytaBlogit) {
            return true;
        }
        return false;
    }
}
