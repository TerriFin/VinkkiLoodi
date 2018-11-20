Feature: As a user I want to be able to add a book Vinkki

  Scenario: Adding is possible with writer,title and ISBN
    Given command Lisää vinkki is selected
    When writer "matti" title "testi" and ISBN "12345678" are entered
    Then system responds with "Vinkki lisätty!"
