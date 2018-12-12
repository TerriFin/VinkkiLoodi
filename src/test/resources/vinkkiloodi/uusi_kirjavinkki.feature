Feature: As a user I want to be able to add a book Vinkki

  Scenario: Adding is possible with writer,title and ISBN
    Given command Lisää vinkki is selected
    And command Kirja is selected
    When writer "matti" title "testi" and ISBN "12345678" are entered
    Then system responds with "Vinkki lisätty!"

  Scenario: Adding adds vinkki into to system
    Given command Lisää vinkki is selected
    And command Kirja is selected
    When writer "matti" title "testi2" and ISBN "21345678" are entered
    Then system saves the Vinkki titled "testi2"
