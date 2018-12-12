Feature: Käyttäjä voi hyödyntää pikakomentoja

  Scenario: KirjaVinkki voidaan tallentaa pikakomennolla
    Given pikakomento "lk" on syötetty
    When Nimi "testinimi" on syötetty
    And Otsikko "otsikkotestikirja" on syötetty
    And ISBN "12345678isb" on syötetty
    And komento Listaa vinkit on valittu
    Then tuloste sisältää "otsikkotestikirja"

  Scenario: ArtikkeliVinkki voidaan tallentaa pikakomennolla
    Given pikakomento "la" on syötetty
    When Nimi "testinimi" on syötetty
    And Otsikko "otsikkotestiartikkeli" on syötetty
    And Julkaisija "testijulkaisija" on syötetty
    And komento Listaa vinkit on valittu
    Then tuloste sisältää "otsikkotestiartikkeli"

  Scenario: BlogiVinkki voidaan tallentaa pikakomennolla
    Given pikakomento "lb" on syötetty
    When Nimi "testinimi" on syötetty
    And Otsikko "otsikkotestiblogi" on syötetty
    And URL "testiurli.fi" on syötetty
    And komento Listaa vinkit on valittu
    Then tuloste sisältää "otsikkotestiblogi"

  Scenario: Vinkki voidaan päivittää pikakomenolla ja otsikolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And pikakomento "pv testiotsikko" on syötetty
    And "Uusi testiotsikko" on syötetty
    And "Uusi testitekija" on syötetty
    And merkitään lukemattomaksi
    And tyhjä rivi on syötetty
    And komento Listaa vinkit on valittu
    Then tuloste sisältää "Uusi testitekija"

  Scenario: Vinkki voidaan merkata tarkastetuksi pikakomennolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And pikakomento "ta testiotsikko" on syötetty
    And komento Listaa vinkit on valittu
    Then tuloste sisältää "merkattu luetuksi."

  Scenario: Vinkkejä voidaan hakea pikakomennolla ja hakusanalla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And pikakomento "s testitekija" on syötetty
    Then tuloste sisältää haetun esimerkkikirjavinkin

  Scenario: Vinkkejä voidaan hakea pikakomennolla ja otsikolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And pikakomento "so testiotsikko" on syötetty
    Then tuloste sisältää haetun esimerkkikirjavinkin

  Scenario: Vinkkejä voidaan hakea pikakomennolla ja tekijällä
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And pikakomento "st testitekija" on syötetty
    Then tuloste sisältää haetun esimerkkikirjavinkin

  Scenario: Tarkastamattomia vinkkejä voidaan hakea pikakomennolla
    And esimerkkiblogivinkki on tallennettu tietokantaan
    And pikakomento "et" on syötetty
    Then tuloste sisältää haetun esimerkkiblogivinkin

  Scenario: Tarkastettuja vinkkejä voidaan hakea pikakomennolla
    Given tarkastettu esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "kt" on syötetty
    Then tuloste sisältää haetun esimerkkiartikkelivinkin
