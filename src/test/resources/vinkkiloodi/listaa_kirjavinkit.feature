Feature: Käyttäjänä voin listata tallennettuja vinkkejä
    Scenario: Ohjelma näyttää tallennetun Kirjavinkin
        Given esimerkkikirjavinkki on tallennettu tietokantaan
        When komento Listaa vinkit on valittu  
        Then esimerkkikirjavinkki näytetään käyttäjälle

    Scenario: Ohjelma näyttää tallennetun Blogivinkin
        Given esimerkkiblogivinkki on tallennettu tietokantaan
        When komento Listaa vinkit on valittu
        Then esimerkkiblogivinkki näytetään käyttäjälle

    Scenario: Ohjelma näyttää tallennetun Artikkelivinkin
        Given esimerkkiartikkelivinkki on tallennettu tietokantaan
        When komento Listaa vinkit on valittu
        Then esimerkkiartikkelivinkki näytetään käyttäjälle

    Scenario: Ohjelma näyttää tallennettuja Blogivinkkejä ja Kirjavinkkejä 
        Given esimerkkikirjavinkki on tallennettu tietokantaan
        And esimerkkiblogivinkki on tallennettu tietokantaan
        When komento Listaa vinkit on valittu
        Then esimerkkikirjavinkki näytetään käyttäjälle
        And esimerkkiblogivinkki näytetään käyttäjälle

    Scenario: Ohjelma näyttää tallennettuja Blogivinkkejä, Kirjavinkkejä ja Artikkelivinkkejä
        Given esimerkkiblogivinkki on tallennettu tietokantaan
        And esimerkkikirjavinkki on tallennettu tietokantaan
        And esimerkkiartikkelivinkki on tallennetu tietokantaan
        When komento Listaa vinkit on valittu
        Then esimerkkiblogivinkki näytetään käyttäjälle
        And esimerkkiblogivinkki näytetään käyttäjälle
        And esimerkkiartikkelivinkki näytetään käyttäjälle
