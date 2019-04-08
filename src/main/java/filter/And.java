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
public class And implements Matcher {
    Matcher[] matchers;
    
    public And(Matcher... matchers) {
        this.matchers = matchers;
    }
    
    @Override
    public boolean matches(Vinkki v) {
        for (Matcher m : matchers) {
            if (!m.matches(v)) {
                return false;
            }
        }
        
        return true;
    }
}
