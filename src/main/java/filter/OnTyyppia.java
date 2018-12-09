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
 * @author sami
 */
public class OnTyyppia implements Matcher {
    
    private Tyyppi vinkinTyyppi;
    
    public OnTyyppia(Tyyppi tyyppi) {
        this.vinkinTyyppi = tyyppi;
    }
    
    @Override
    public boolean matches(Vinkki v) {
        return v.getTyyppi() == vinkinTyyppi;
    }
    
}
