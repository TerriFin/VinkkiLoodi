/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import vinkkiloodi.domain.Vinkki;

/**
 *
 * @author sami
 */
public class NimiSisaltaa implements Matcher {
    
    private String teksti;
    
    public NimiSisaltaa(String teksti) {
        this.teksti = teksti.toLowerCase();
    }
    
    @Override
    public boolean matches(Vinkki v) {
        return v.getNimi().toLowerCase().contains(teksti);
    }
}
