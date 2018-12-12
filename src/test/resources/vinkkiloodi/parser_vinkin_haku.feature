Feature: Käyttäjä haluaa käyttää parseria hakujen tekemiseen

  Scenario: Käyttäjä haluaa käyttää nopeaa hakua etsiäkseen tietyn vinkin
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää kirja ("Eetu" "Eetun uusi hieno kirja" "1234")'
    And käyttäjä antaa komennon 'nopeahaku eetu'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Nimi: Eetun uusi hieno kirja"
    And tuloste ei sisällä esimerkkivinkkejä

  Scenario: Käyttäjä haluaa listata kaikki tarkastetut
    Given tarkastettu esimerkkiartikkelivinkki on tallennettu tietokantaan
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'tarkkahaku tarkastetut'
    And käyttäjä sulkee parserin
    Then tuloste sisältää haetun esimerkkiartikkelivinkin

  Scenario: Käyttäjä haluaa listata tarkastamattomat
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'tarkkahaku tarkastamattomat'
    And käyttäjä sulkee parserin
    Then tuloste sisältää kaikki vinkit

  Scenario: Käyttäjä haluaa listata kirjoja tarkalla haulla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'tarkkahaku kirjoja'
    And käyttäjä sulkee parserin
    Then tuloste sisältää haetun esimerkkikirjavinkin

  Scenario: Käyttäjä haluaa listata artikkeleita tarkalla haulla
    Given esimerkkiartikkelivinkki on tallennettu tietokantaan
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'tarkkahaku artikkeleita'
    And käyttäjä sulkee parserin
    Then tuloste sisältää haetun esimerkkiartikkelivinkin

  Scenario: Käyttäjä haluaa listata blogeja tarkalla haulla
    Given esimerkkiblogivinkki on tallennettu tietokantaan
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'tarkkahaku blogeja'
    And käyttäjä sulkee parserin
    Then tuloste sisältää haetun esimerkkiblogivinkin

  Scenario: Käyttäjä haluaa hakea vinkkiä tekijän perusteella
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää kirja ("Eetu" "Eetun uusi hieno kirja" "1234")'
    And käyttäjä antaa komennon 'tarkkahaku tekijä eetu'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Tekijä: Eetu"

  Scenario: Käyttäjä haluaa hakea vinkkiä nimen perusteella
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää kirja ("Eetu" "Eetun uusi hieno kirja" "1234")'
    And käyttäjä antaa komennon 'tarkkahaku nimi eetu'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Tekijä: Eetu"
