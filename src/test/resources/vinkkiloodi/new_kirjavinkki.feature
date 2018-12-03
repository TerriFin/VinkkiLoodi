Feature: As a user I want to be able to add a book Vinkki

  Scenario: Adding is possible with writer,title and ISBN
    Given command Lisää vinkki is selected
    And command Kirja is selected
    When writer "matti" title "testi" and ISBN "12345678" are entered
    Then system responds with "Vinkki lisätty!"

  Scenario: Adding adds vinkki into the system
    Given command Lisää vinkki is selected
    And command Kirja is selected
    When writer "matti" title "testi" and ISBN "21345678" are entered
    And all vinkkis are printed
    Then system responds with "Tyyppi: Kirja\nTekijä: matti\nNimi: testi\nTarkastettu: Ei\n"
