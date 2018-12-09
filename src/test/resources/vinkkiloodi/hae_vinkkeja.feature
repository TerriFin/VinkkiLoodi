Feature: Käyttäjä voi hakea tallennettuja vinkkejä Nopealla ja Tarkalla haulla

  Scenario: Ohjelma tulostaa haetun kirjavinkin käytettäessä nopeaa hakua
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    When komento Nopea haku on valittu
    And esimerkkikirjavinkin otsikko on syötetty
    Then tuloste sisältää haetun esimerkkikirjavinkin

  Scenario: Ohjelma tulostaa haetun blogivinkin käytettäessä nopeaa hakua
    Given esimerkkiblogivinkki on tallennettu tietokantaan
    When komento Nopea haku on valittu
    And esimerkkiblogivinkin otsikko on syötetty
    Then tuloste sisältää haetun esimerkkiblogivinkin

  Scenario: Ohjelma tulostaa haetun artikkelivinkin käytettäessä nopeaa hakua
    Given esimerkkiartikkelivinkki on tallennettu tietokantaan
    When komento Nopea haku on valittu
    And esimerkkiartikkelivinkin otsikko on syötetty
    Then tuloste sisältää haetun esimerkkiartikkelivinkin

  Scenario: Nopea haku ei tulosta vinkkiä jota ei haeta
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And eri niminen toinen kirjavinkki on tallennettu tietokantaan
    When komento Nopea haku on valittu
    And esimerkkikirjavinkin otsikko on syötetty
    Then tuloste ei sisällä toista kirjavinkkiä

  Scenario: Nopea haku ei tulosta vinkkejä väärällä hakusanalla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    When komento Nopea haku on valittu
    And "Väärä hakusana" on syötetty
    Then tuloste ei sisällä esimerkkivinkkejä

  Scenario: Nopea haku löytää kaikki esimerkkivinkit niiden yhteisellä otsikolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    When komento Nopea haku on valittu
    And esimerkkikirjavinkin otsikko on syötetty
    Then tuloste sisältää kaikki vinkit

  Scenario: Tarkka haku löytää esimerkkikirjavinkin
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    When komento Tarkka haku on valittu
    And valitaan tulostettavaksi kirjoja
    Then tuloste sisältää haetun esimerkkikirjavinkin

  Scenario: Tarkka haku löytää esimerkkiblogivinkin
    Given esimerkkiblogivinkki on tallennettu tietokantaan
    When komento Tarkka haku on valittu
    And valitaan tulostettavaksi blogeja
    Then tuloste sisältää haetun esimerkkiblogivinkin

  Scenario: Tarkka haku löytää esimerkkiartikkelivinkin
    Given esimerkkiartikkelivinkki on tallennettu tietokantaan
    When komento Tarkka haku on valittu
    And valitaan tulostettavaksi artikkeleita
    Then tuloste sisältää haetun esimerkkiartikkelivinkin

  Scenario: Tarkka haku löytää molemmat kirjavinkit
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And eri niminen toinen kirjavinkki on tallennettu tietokantaan
    When komento Tarkka haku on valittu
    And valitaan tulostettavaksi kirjoja
    Then tuloste sisältää molemmat kirjavinkit

  Scenario: Tarkka haku ei tulosta kirjaa jota ei haeta(Tekijä haku)
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And eri niminen toinen kirjavinkki on tallennettu tietokantaan
    When komento Tarkka haku on valittu
    And haetaan vinkit tekijällä
    And esimerkkikirjavinkin tekijä on syötetty
    Then tuloste ei sisällä toista kirjavinkkiä

  Scenario: Tarkka haku ei tulosta kirjaa jota ei haeta(Otsikko haku)
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And eri niminen toinen kirjavinkki on tallennettu tietokantaan
    When komento Tarkka haku on valittu
    And haetaan vinkit otsikolla
    And esimerkkikirjavinkin otsikko on syötetty
    Then tuloste ei sisällä toista kirjavinkkiä

  Scenario: Tarkastamattomien tulostaminen tulostaa tarkastamattomia vinkkejä
    Given esimerkkiartikkelivinkki on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    When komento Tarkka haku on valittu
    And valitaan tulostettavaksi tarkastamattomia vinkkejä
    Then tuloste sisältää haetun esimerkkiartikkelivinkin
    And tuloste sisältää haetun esimerkkiblogivinkin

  Scenario: Tarkalla haulla voi tulostaa tarkastetut vinkit eikä tarkastamattomia tulosteta
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And eri niminen toinen kirjavinkki on tallennettu tietokantaan
    And toinen kirja merkitään tarkastetuksi
    Then tuloste ei sisällä esimerkkivinkkejä
