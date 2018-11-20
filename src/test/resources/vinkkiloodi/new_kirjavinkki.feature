Feature: As a user I want to be able to add a book Vinkki

  Scenario: Adding is possible with writer,title and ISBN
    Given command Lisää vinkki is selected
    When writer "matti" title "testi" and ISBN "12345678" are entered
    Then system responds with "Vinkki lisätty!"

  Scenario: Adding adds vinkki into the system
    given command Lisää vinkki is selected
    when writer "matti" title "testi" and ISBN "21345678" are entered
    and all vinkkis are printed
    then system responds with "'testi', matti, ei ole luettu, tagit: [] ISBN: 12345678"