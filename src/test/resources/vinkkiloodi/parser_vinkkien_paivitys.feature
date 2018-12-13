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

  Scenario: Käyttäjälle tarjotaan mahdollisuus antaa uudet attribuutit artikkelille jos annetut ovat väärässä muodossa
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää artikkeli ("Art" "ikkeli" "julkaisija")'
    And käyttäjä antaa komennon 'päivitä ikkeli ("Art" "ikkeli" "julkaisija" "true)'
    And käyttäjä antaa komennon '("Art" "ikkeli" "julkaisija" "true")'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Artikkeli ikkeli onnistuneesti päivitetty!"
    And tuloste sisältää "Antamasi attribuutit ovat joko väärässä muodossa tai lainausmerkit ovat väärin"

  Scenario: Käyttäjälle tarjotaan mahdollisuus antaa uudet attribuutit kirjalle jos annetut ovat väärässä muodossa
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää kirja ("Teppo" "muistelmat" "123123")'
    And käyttäjä antaa komennon 'päivitä muistelmat ("Teppo" "muistelmat" "123123" "true)'
    And käyttäjä antaa komennon '("Teppo" "muistelmat" "123123" "true")'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Kirja muistelmat onnistuneesti päivitetty!"
    And tuloste sisältää "Antamasi attribuutit ovat joko väärässä muodossa tai lainausmerkit ovat väärin"

  Scenario: Käyttäjälle tarjotaan mahdollisuus antaa uudet attribuutit blogille jos annetut ovat väärässä muodossa
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää blog ("Timo" "ploki" "osoite")'
    And käyttäjä antaa komennon 'päivitä ploki ("Timo" "ploki" "osoite" "true)'
    And käyttäjä antaa komennon '("Timo" "ploki" "osoite" "true")'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Blogi ploki onnistuneesti päivitetty!"
    And tuloste sisältää "Antamasi attribuutit ovat joko väärässä muodossa tai lainausmerkit ovat väärin"

  Scenario: Jos päivitettävää vinkkiä ei löydy, siitä ilmoitetaan käyttäjälle
    When komento Avaa parser on valittu
    And käyttäjä antaa komennon 'Lisää artikkeli ("Art" "ikkeli" "julkaisija")'
    And käyttäjä antaa komennon 'päivitä JokinOlematon ("Art" "ikkeli" "julkaisija" "true)'
    And käyttäjä sulkee parserin
    Then tuloste sisältää "Ei löytynyt vinkkiä hakusanalla"
