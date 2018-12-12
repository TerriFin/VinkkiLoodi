/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import vinkkiloodi.domain.Tyyppi;

/**
 *
 * @author sami
 */
public class HakuBuilder {

    private Matcher haku;

    public HakuBuilder() {
        haku = new Kaikki();
    }

    public Matcher build() {
        Matcher uusiHaku = haku;

        haku = new Kaikki();

        return uusiHaku;
    }

    public HakuBuilder onTyyppia(Tyyppi tyyppi) {
        haku = new And(haku,
                new OnTyyppia(tyyppi)
        );

        return this;
    }

    public HakuBuilder nimiSisaltaa(String teksti) {
        haku = new And(haku,
                new NimiSisaltaa(teksti)
        );

        return this;
    }

    public HakuBuilder tekijaSisaltaa(String teksti) {
        haku = new And(haku,
                new TekijaSisaltaa(teksti)
        );

        return this;
    }

    public HakuBuilder tarkastettu() {
        haku = new And(haku,
                new Tarkastettu()
        );

        return this;
    }

    public HakuBuilder tarkastamaton() {
        haku = new And(haku,
                new Tarkistamaton()
        );

        return this;
    }

    public HakuBuilder matchOne(Matcher... matchers) {
        haku = new Or(matchers);

        return this;
    }

    public HakuBuilder onJokuHalutuistaTyypeista(boolean kirja, boolean artikkeli, boolean blog) {
        haku = new And(haku,
                new OnJotainTyypeista(kirja, artikkeli, blog)
        );
        return this;
    }
}
