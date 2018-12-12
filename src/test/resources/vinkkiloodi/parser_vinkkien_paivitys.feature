Feature: Käyttäjä voi päivittää lisäämiään vinkkejä

  Scenario: Käyttäjä syöttää blogivinkin ja huomaa linkin olevan väärä
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää blogi ("Timo" "Ploki" "osoite")'
    And käyttäjä antaa komennon 'päivitä Ploki ("Timo" "Ploki" "oikeaosoite" "false")'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Blogi Ploki onnistuneesti päivitetty!"

  Scenario: Käyttäjän pitää päivittää kirjan nimeä
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää kirja ("Matti" "kirjannimi" "12345")'
    And käyttäjä antaa komennon 'päivitä kirjannimi ("Matti" "kirjannimi" "1234567" "false")'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Kirja kirjannimi onnistuneesti päivitetty!"

  Scenario: Käyttäjä haluaa merkata artikkelin tarkastetuksi
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää artikkeli ("Art" "ikkeli" "julkaisija")'
    And käyttäjä antaa komennon 'päivitä ikkeli ("Art" "ikkeli" "julkaisija" "true")'
    And käyttäjä antaa komennon 'listaa'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Artikkeli ikkeli onnistuneesti päivitetty!"
    And tuloste sisältää "Tarkastettu: Kyllä"

  Scenario: Käyttäjälle tarjotaan mahdollisuus antaa uudet attribuutit jos annetut ovat väärässä muodossa
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää artikkeli ("Art" "ikkeli" "julkaisija")'
    And käyttäjä antaa komennon 'päivitä ikkeli ("Art" "ikkeli" "julkaisija" "true)'
    And käyttäjä antaa komennon '("Art" "ikkeli" "julkaisija" "true")'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Artikkeli ikkeli onnistuneesti päivitetty!"
