Feature: Käyttäjä voi lisätä vinkkejä parserin avulla

  Scenario: Käyttäjä haluaa lisätä kirjavinkin käyttämällä parseria
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää kirja ("Eetu" "Eetun uusi hieno kirja" "1234")'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Uusi kirja 'Eetun uusi hieno kirja' lisätty!"

  Scenario: Käyttäjä haluaa lisätä artikkelivinkin käyttämällä parseria
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää artikkeli ("Jussi" "Jussin artikkeli" "julkaisija")'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Uusi artikkeli 'Jussin artikkeli' lisätty!"

  Scenario: Käyttäjä haluaa lisätä blogivinkin käyttämällä parseria
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää blogi ("Timo" "Timon ploki" "www.url.fi")'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Uusi blogi 'Timon ploki' lisätty!"

  Scenario: Käyttäjä haluaa lisätä kirjavinkin, mutta kirjoittaa kirjan väärin
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää krja ("Eetu" "Eetun uusi hieno kirja" "1234")'
    And käyttäjä antaa komennon 'kirja'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Komento 'krja' ei vastaa mitään validia komentoa"
    And tuloste sisältää "Uusi kirja 'Eetun uusi hieno kirja' lisätty!"

  Scenario: Käyttäjä haluaa lisätä kirjavinkin, mutta kirjoittaa Lisää väärin
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisä kirja ("Eetu" "Eetun uusi hieno kirja" "1234")'
    And käyttäjä antaa komennon 'lisää'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Komento 'lisä' ei vastaa mitään validia komentoa"
    And tuloste sisältää "Uusi kirja 'Eetun uusi hieno kirja' lisätty!"

  Scenario: Käyttäjä haluaa lisätä kirjavinkin, mutta antaa attribuutit väärin
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää kirja ("Eetu" Eetun uusi hieno kirja" "1234")'
    And käyttäjä antaa komennon '("Eetu" "Eetun uusi hieno kirja" "1234")'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Antamassasi attribuutit ovat joko väärässä muodossa tai lainausmerkit ovat väärin"
    And tuloste sisältää "Uusi kirja 'Eetun uusi hieno kirja' lisätty!"

  Scenario: Parserilla syötetyt kirjavinkit löytyvät tietokannasta
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää kirja ("Eetu" "Eetun uusi hieno kirja" "1234")'
    And käyttäjä antaa komennon 'Lisää artikkeli ("Jussi" "Jussin artikkeli" "julkaisija")'
    And käyttäjä antaa komennon 'Lisää blogi ("Timo" "Timon ploki" "www.url.fi")'
    And käyttäjä sulkee parserin
    And komento Listaa vinkit on valittu
    Then tuloste sisältää "Nimi: Eetun uusi hieno kirja"
    And tuloste sisältää "Nimi: Jussin artikkeli"
    And tuloste sisältää "Nimi: Timon ploki"
