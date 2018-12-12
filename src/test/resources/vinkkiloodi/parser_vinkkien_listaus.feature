Feature: Käyttäjä haluaa listata vinkit parserin avulla

  Scenario: Käyttäjä käyttää listauskomentoa
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'listaa'
    And käyttäjä sulkee parserin
    Then tuloste sisältää kaikki vinkit

  Scenario: Parser pyytää käyttäjää kirjoittamaan väärin kirjoitetun komennon uudelleen
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'lista'
    And käyttäjä antaa komennon 'listaa'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Komento 'lista' ei vastaa mitään validia komentoa"
    And tuloste sisältää haetun esimerkkikirjavinkin
