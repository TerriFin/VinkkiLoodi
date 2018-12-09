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
public class Not implements Matcher {
    
    private Matcher matcher;
    
    public Not(Matcher matcher) {
        this.matcher = matcher;
    }
    
    @Override
    public boolean matches(Vinkki v) {
        return !matcher.matches(v);
    }
}
