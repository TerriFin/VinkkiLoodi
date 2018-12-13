package vinkkiloodi.logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samisaukkonen
 */
public class ParserKomentoManageri {

    private String[] syote;
    private List<String> komennot;
    private int parserKohta;

    public boolean annaUudetKomennot(String syote) {
        if (!hipsujaSamaMaara(syote)) {
            return false;
        }

        if (!sulkujaSamaMaara(syote)) {
            return false;
        }

        String[] muisti = syote.trim().split(" ");
        
        if (muisti.length == 0) {
            return false;
        }
        
        this.syote = muisti;
        return true;
    }

    public boolean prosessoiSyote() {
        komennot = new ArrayList<>();
        parserKohta = 0;
        int prosessointiParserKohta = 0;

        while (prosessointiParserKohta < syote.length) {
            if (syote[prosessointiParserKohta].charAt(0) == '(') {
                return lisaaSulkujenSisalto(prosessointiParserKohta);
            } else {
                komennot.add(syote[prosessointiParserKohta]);
            }

            prosessointiParserKohta++;
        }

        return true;
    }

    public String annaSeuraavaKomento() {
        try {
            return komennot.get(parserKohta++);
        } catch (Exception e) {
            return null;
        }
    }

    private boolean lisaaSulkujenSisalto(int kohta) {
        try {
            String sulkujenSisalto = "";
            for (int i = kohta; i < syote.length; i++) {
                if (syote[i].charAt(syote[i].length() - 1) == ')') {
                    sulkujenSisalto += syote[i];
                    komennot.add(sulkujenSisalto);
                    return true;
                } else {
                    sulkujenSisalto += syote[i] + " ";
                }
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean hipsujaSamaMaara(String syote) {
        int hipsuja = syote.chars()
                .filter(c -> (c == '"'))
                .sum();

        return hipsuja % 2 == 0;
    }

    private boolean sulkujaSamaMaara(String input) {
        int vasenSulkuja = 0;
        int oikeaSulkuja = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                vasenSulkuja++;
            } else if (input.charAt(i) == ')') {
                oikeaSulkuja++;
            }
        }

        return vasenSulkuja == oikeaSulkuja;
    }
}
