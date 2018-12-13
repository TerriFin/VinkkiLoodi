Feature: Käyttäjä voi lisätä uuden vinkin

  Scenario: KirjaVinkin voi lisätä
    Given valitaan komento lisää kirjaVinkki
    When argumentit "matti", "testi", "12345678" syotetaan
    Then tuloste sisältää "Vinkki lisätty!"

  Scenario: BlogiVinkin voi lisätä
    Given valitaan komento lisaa blogiVinkki
    When argumentit "pekka", "testi2", "testiurl" syotetaan
    Then tuloste sisältää "Vinkki lisätty!"


Scenario: ArtikkeliVinkin voi listata
    Given valitaan komento lisaa artikkelivinkki
    When argumentit "maija", "testi3", "julkaisija" syotetaan
    Then tuloste sisältää "Vinkki lisätty!"