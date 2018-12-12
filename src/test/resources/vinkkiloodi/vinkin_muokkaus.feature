Feature: Käyttäjä voi muokata tallennettuja vinkkejä

    Scenario: Tallennetun Kirjavinkin nimeä voidaan muokata
        Given esimerkkikirjavinkki on tallennettu tietokantaan 
        When komento Päivitä vinkki on valittu
        And esimerkkikirjavinkin otsikko on syötetty
        And "Uusi testiotsikko" on syötetty
        And tyhjä rivi on syötetty
        And merkitään lukemattomaksi
        And tyhjä rivi on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "Uusi testiotsikko"

    Scenario: Tallennetun Kirjavinkin tekijää voidaan muokata
        Given esimerkkikirjavinkki on tallennettu tietokantaan
        When komento Päivitä vinkki on valittu
        And esimerkkikirjavinkin otsikko on syötetty
        And "Uusi testiotsikko" on syötetty
        And "Uusi testitekija" on syötetty
        And merkitään lukemattomaksi
        And tyhjä rivi on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "Uusi testitekija"

    Scenario: Tallennetun Kirjavinkin tarkastettu kenttää voidaan muokata
        Given esimerkkikirjavinkki on tallennettu tietokantaan
        When komento Päivitä vinkki on valittu
        And esimerkkikirjavinkin otsikko on syötetty
        And tyhjä rivi on syötetty
        And tyhjä rivi on syötetty
        And merkitään luetuksi
        And tyhjä rivi on syötetty
        And pikakomento "et" on syötetty
        Then tuloste sisältää haetun esimerkkikirjavinkin
    
    Scenario: Tallennetun Blogivinkin nimeä voidaan muokata
        Given esimerkkiblogivinkki on tallennettu tietokantaan 
        When komento Päivitä vinkki on valittu
        And esimerkkiblogivinkin otsikko on syötetty
        And "Uusi testiotsikko" on syötetty
        And tyhjä rivi on syötetty
        And merkitään lukemattomaksi
        And tyhjä rivi on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "Uusi testiotsikko"

    Scenario: Tallennetun Blogivinkin tekijää voidaan muokata
        Given esimerkkiblogivinkki on tallennettu tietokantaan
        When komento Päivitä vinkki on valittu
        And esimerkkiblogivinkin otsikko on syötetty
        And "Uusi testiotsikko" on syötetty
        And "Uusi testitekija" on syötetty
        And merkitään lukemattomaksi
        And tyhjä rivi on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "Uusi testitekija"


    Scenario: Tallennetun Blogivinkin tarkastettu kenttää voidaan muokata
        Given esimerkkiblogivinkki on tallennettu tietokantaan
        When komento Päivitä vinkki on valittu
        And esimerkkiblogivinkin otsikko on syötetty
        And tyhjä rivi on syötetty
        And tyhjä rivi on syötetty
        And merkitään luetuksi
        And tyhjä rivi on syötetty
        And pikakomento "et" on syötetty
        Then tuloste sisältää haetun esimerkkiblogivinkin

     Scenario: Tallennetun Artikkelivinkin nimeä voidaan muokata
        Given esimerkkiartikkelivinkki on tallennettu tietokantaan 
        When komento Päivitä vinkki on valittu
        And esimerkkiartikkelivinkin otsikko on syötetty
        And "Uusi testiotsikko" on syötetty
        And tyhjä rivi on syötetty
        And merkitään lukemattomaksi
        And tyhjä rivi on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "Uusi testiotsikko"

    Scenario: Tallennetun Artikkelivinkin tekijää voidaan muokata
        Given esimerkkiartikkelivinkki on tallennettu tietokantaan
        When komento Päivitä vinkki on valittu
        And esimerkkiartikkelivinkin otsikko on syötetty
        And "Uusi testiotsikko" on syötetty
        And "Uusi testitekija" on syötetty
        And merkitään lukemattomaksi
        And tyhjä rivi on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "Uusi testitekija"

    Scenario: Tallennetun Artikkelivinkin tarkastettu kenttää voidaan muokata
        Given esimerkkiartikkelivinkki on tallennettu tietokantaan
        When komento Päivitä vinkki on valittu
        And esimerkkiartikkelivinkin otsikko on syötetty
        And tyhjä rivi on syötetty
        And tyhjä rivi on syötetty
        And merkitään luetuksi
        And tyhjä rivi on syötetty
        And pikakomento "et" on syötetty
        Then tuloste sisältää haetun esimerkkiartikkelivinkin
