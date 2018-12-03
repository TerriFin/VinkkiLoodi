Feature: Käyttäjänä voin listata tallennettuja vinkkejä

  Scenario: Ohjelma näyttää tallennetun Kirjavinkin
    Given esimerkkikirjavinkki otsikolla "testiotsikko1" on tallennettu tietokantaan
    When komento Listaa vinkit on valittu
    Then esimerkkikirjavinkki näytetään käyttäjälle

  Scenario: Ohjelma näyttää tallennetun Blogivinkin
    Given esimerkkiblogivinkki otsikolla "testiotsikko2" on tallennettu tietokantaan
    When komento Listaa vinkit on valittu
    Then esimerkkiblogivinkki näytetään käyttäjälle

  Scenario: Ohjelma näyttää tallennetun Artikkelivinkin
    Given esimerkkiartikkelivinkki otsikolla "testiotsikko3" on tallennettu tietokantaan
    When komento Listaa vinkit on valittu
    Then esimerkkiartikkelivinkki näytetään käyttäjälle

  Scenario: Ohjelma näyttää tallennettuja Blogivinkkejä ja Kirjavinkkejä
    Given esimerkkikirjavinkki otsikolla "testiotsikko4" on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    When komento Listaa vinkit on valittu
    Then esimerkkikirjavinkki näytetään käyttäjälle
    And esimerkkiblogivinkki näytetään käyttäjälle

  Scenario: Ohjelma näyttää tallennettuja Blogivinkkejä, Kirjavinkkejä ja Artikkelivinkkejä
    Given esimerkkiblogivinkki otsikolla "testiotsikko5" on tallennettu tietokantaan
    And esimerkkikirjavinkki otsikolla "testiotsikko6" on tallennettu tietokantaan
    And esimerkkiartikkelivinkki otsikolla "testiotsikko7" on tallennettu tietokantaan
    When komento Listaa vinkit on valittu
    Then esimerkkiblogivinkki näytetään käyttäjälle
    And esimerkkiblogivinkki näytetään käyttäjälle
    And esimerkkiartikkelivinkki näytetään käyttäjälle
