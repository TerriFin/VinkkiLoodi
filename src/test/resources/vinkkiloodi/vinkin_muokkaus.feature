Feature: Käyttäjä voi muokata tallennettuja vinkkejä

    Scenario: Tallennetun Kirjavinkin nimeä voidaan muokata
        Given esimerkkikirjavinkki on tallennettu tietokantaan 
        When komento Päivitä vinkki on valittu
        And esimerkkikirjavinkin otsikko on syötetty
        And "Uusi otsikko" on syötetty
        And tyhjä rivi on syötetty
        And tyhjä rivi on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "Uusi otsikko"

    Scenario: Tallennetun Kirjavinkin tekijää voidaan muokata
        Given esimerkkikirjavinkki on tallennettu tietokantaan
        When komento Päivitä vinkki on valittu
        And esimerkkikirjavinkin otsikko on syötetty
        And "Uusi otsikko" on syötetty
        And "Uusi tekijä" on syötetty
        And tyhjä rivi on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "Uusi tekijä"
    
    Scenario: Tallennetun Blogivinkin nimeä voidaan muokata
        Given esimerkkiblogivinkki on tallennettu tietokantaan 
        When komento Päivitä vinkki on valittu
        And esimerkkiblogivinkin otsikko on syötetty
        And "Uusi otsikko" on syötetty
        And tyhjä rivi on syötetty
        And tyhjä rivi on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "Uusi otsikko"

    Scenario: Tallennetun Blogivinkin tekijää voidaan muokata
        Given esimerkkiblogivinkki on tallennettu tietokantaan
        When komento Päivitä vinkki on valittu
        And esimerkkiblogivinkin otsikko on syötetty
        And "Uusi otsikko" on syötetty
        And "Uusi tekijä" on syötetty
        And tyhjä rivi on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "Uusi tekijä"

     Scenario: Tallennetun Artikkelivinkin nimeä voidaan muokata
        Given esimerkkiartikkelivinkki on tallennettu tietokantaan 
        When komento Päivitä vinkki on valittu
        And esimerkkiartikkelivinkin otsikko on syötetty
        And "Uusi otsikko" on syötetty
        And tyhjä rivi on syötetty
        And tyhjä rivi on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "Uusi otsikko"

    Scenario: Tallennetun Artikkelivinkin tekijää voidaan muokata
        Given esimerkkiartikkelivinkki on tallennettu tietokantaan
        When komento Päivitä vinkki on valittu
        And esimerkkiartikkelivinkin otsikko on syötetty
        And "Uusi otsikko" on syötetty
        And "Uusi tekijä" on syötetty
        And tyhjä rivi on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "Uusi tekijä"
