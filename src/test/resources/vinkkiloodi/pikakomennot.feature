Feature: Käyttäjä voi hyödyntää pikakomentoja

  Scenario: KirjaVinkki voidaan tallentaa pikakomennolla
    Given pikakomento "lk" on syötetty
    When Nimi "testinimi" on syötetty
    And Otsikko "otsikkotestikirja" on syötetty
    And ISBN "12345678isb" on syötetty
    And komento Listaa vinkit on valittu
    Then tuloste sisältää "otsikkotestikirja"

  Scenario: ArtikkeliVinkki voidaan tallentaa pikakomennolla
    Given pikakomento "la" on syötetty
    When Nimi "testinimi" on syötetty
    And Otsikko "otsikkotestiartikkeli" on syötetty
    And Julkaisija "testijulkaisija" on syötetty
    And komento Listaa vinkit on valittu
    Then tuloste sisältää "otsikkotestiartikkeli"

  Scenario: BlogiVinkki voidaan tallentaa pikakomennolla
    Given pikakomento "lb" on syötetty
    When Nimi "testinimi" on syötetty
    And Otsikko "otsikkotestiblogi" on syötetty
    And URL "testiurli.fi" on syötetty
    And komento Listaa vinkit on valittu
    Then tuloste sisältää "otsikkotestiblogi"

  Scenario: Vinkki voidaan päivittää pikakomenolla ja otsikolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And pikakomento "pv testiotsikko" on syötetty
    And "Uusi testiotsikko" on syötetty
    And "Uusi testitekija" on syötetty
    And merkitään lukemattomaksi
    And tyhjä rivi on syötetty
    And komento Listaa vinkit on valittu
    Then tuloste sisältää "Uusi testitekija"

  Scenario: Vinkki voidaan merkata tarkastetuksi pikakomennolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And pikakomento "ta testiotsikko" on syötetty
    And komento Listaa vinkit on valittu
    Then tuloste sisältää "merkattu luetuksi."

  Scenario: Vinkkejä voidaan hakea pikakomennolla ja hakusanalla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And pikakomento "s testitekija" on syötetty
    Then tuloste sisältää haetun esimerkkikirjavinkin

  Scenario: Vinkkejä voidaan hakea pikakomennolla ja otsikolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And pikakomento "so testiotsikko" on syötetty
    Then tuloste sisältää haetun esimerkkikirjavinkin

  Scenario: Vinkkejä voidaan hakea pikakomennolla ja tekijällä
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And pikakomento "st testitekija" on syötetty
    Then tuloste sisältää haetun esimerkkikirjavinkin

  Scenario: Kaikki tarkastamattomat vinkit voidaan hakea pikakomennolla
    Given esimerkkiblogivinkki on tallennettu tietokantaan
    And esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "et" on syötetty
    Then tuloste sisältää haetun esimerkkiblogivinkin
    And tuloste sisältää haetun esimerkkikirjavinkin
    And tuloste sisältää haetun esimerkkiartikkelivinkin

  Scenario: Vain Tarkastamattomat kirjaVinkit voidaan hakea pikakomennolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "et k" on syötetty
    Then tuloste sisältää haetun esimerkkikirjavinkin
    And tuloste ei sisällä esimerkkiblogivinkkiä
    And tuloste ei sisällä esimerkkiartikkelivinkkiä

  Scenario: Vain Tarkastamattomat blogiVinkit voidaan hakea pikakomennolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "et b" on syötetty
    Then tuloste sisältää haetun esimerkkiblogivinkin
    And tuloste ei sisällä esimerkkikirjavinkkiä
    And tuloste ei sisällä esimerkkiartikkelivinkkiä

  Scenario: Vain Tarkastamattomat artikkeliVinkit voidaan hakea pikakomennolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "et a" on syötetty
    Then tuloste sisältää haetun esimerkkiartikkelivinkin
    And tuloste ei sisällä esimerkkikirjavinkkiä
    And tuloste ei sisällä esimerkkiblogivinkkiä

  Scenario: Vain Tarkastamattomat blogi- ja kirjavinkit voidaan hakea pikakomennolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "et kb" on syötetty
    Then tuloste sisältää haetun esimerkkiblogivinkin
    And tuloste sisältää haetun esimerkkikirjavinkin
    And tuloste ei sisällä esimerkkiartikkelivinkkiä

  Scenario: Vain Tarkastamattomat kirja- ja artikkelivinkit voidaan hakea pikakomennolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    And pikakomento "et ka" on syötetty
    And tuloste sisältää haetun esimerkkikirjavinkin
    And tuloste sisältää haetun esimerkkiartikkelivinkin
    And tuloste ei sisällä esimerkkiblogivinkkiä

  Scenario: Vain Tarkastamattomat blogi- ja artikkelivinkit voidaan hakea pikakomennolla
    Given esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    And esimerkkiblogivinkki on tallennettu tietokantaan
    And pikakomento "et ab" on syötetty
    Then tuloste sisältää haetun esimerkkiblogivinkin
    And tuloste sisältää haetun esimerkkiartikkelivinkin
    And tuloste ei sisällä esimerkkikirjavinkkiä

  Scenario: Kaikki tarkastamattomat vinkit voidaan hakea yhdistelmä-pikakomennolla
    Given esimerkkiblogivinkki on tallennettu tietokantaan
    And esimerkkikirjavinkki on tallennettu tietokantaan
    And esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "et kab" on syötetty
    Then tuloste sisältää haetun esimerkkiblogivinkin
    And tuloste sisältää haetun esimerkkikirjavinkin
    And tuloste sisältää haetun esimerkkiartikkelivinkin

  Scenario: Kaikki tarkastetut vinkit voidaan hakea pikakomennolla
    Given tarkastettu esimerkkiblogivinkki on tallennettu tietokantaan
    And tarkastettu esimerkkikirjavinkki on tallennettu tietokantaan
    And tarkastettu esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "kt" on syötetty
    Then tuloste sisältää haetun esimerkkiartikkelivinkin
    And tuloste sisältää haetun esimerkkiblogivinkin
    And tuloste sisältää haetun esimerkkikirjavinkin

  Scenario: Vain tarkastetut kirjaVinkit voidaan hakea pikakomennolla
    Given tarkastettu esimerkkiblogivinkki on tallennettu tietokantaan
    And tarkastettu esimerkkikirjavinkki on tallennettu tietokantaan
    And tarkastettu esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "kt k" on syötetty
    Then tuloste sisältää haetun esimerkkikirjavinkin
    And tuloste ei sisällä esimerkkiblogivinkkiä
    And tuloste ei sisällä esimerkkiartikkelivinkkiä

  Scenario: Vain Tarkastetut blogiVinkit voidaan hakea pikakomennolla
    Given tarkastettu esimerkkiblogivinkki on tallennettu tietokantaan
    And tarkastettu esimerkkikirjavinkki on tallennettu tietokantaan
    And tarkastettu esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "kt b" on syötetty
    Then tuloste sisältää haetun esimerkkiblogivinkin
    And tuloste ei sisällä esimerkkiartikkelivinkkiä
    And tuloste ei sisällä esimerkkikirjavinkkiä

  Scenario: Vain tarkastetut artikkeliVinkit voidaan hakea pikakomennolla
    Given tarkastettu esimerkkiblogivinkki on tallennettu tietokantaan
    And tarkastettu esimerkkikirjavinkki on tallennettu tietokantaan
    And tarkastettu esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "kt a" on syötetty
    Then tuloste sisältää haetun esimerkkiartikkelivinkin
    And tuloste ei sisällä esimerkkiblogivinkkiä
    And tuloste ei sisällä esimerkkikirjavinkkiä

  Scenario: Vain Tarkastetut kirja- ja artikkeliVinkit voidaan hakea pikakomennolla
    Given tarkastettu esimerkkiblogivinkki on tallennettu tietokantaan
    And tarkastettu esimerkkikirjavinkki on tallennettu tietokantaan
    And tarkastettu esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "kt ka" on syötetty
    Then tuloste sisältää haetun esimerkkiartikkelivinkin
    And tuloste sisältää haetun esimerkkikirjavinkin
    And tuloste ei sisällä esimerkkiblogivinkkiä

  Scenario: Vain Tarkastetut kirja- ja blogiVinkit voidaan hakea pikakomennolla
    Given tarkastettu esimerkkiblogivinkki on tallennettu tietokantaan
    And tarkastettu esimerkkikirjavinkki on tallennettu tietokantaan
    And tarkastettu esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "kt kb" on syötetty
    Then tuloste sisältää haetun esimerkkiblogivinkin
    And tuloste sisältää haetun esimerkkikirjavinkin
    And tuloste ei sisällä esimerkkiartikkelivinkkiä

  Scenario: Vain Tarkastetut artikkeli- ja blogiVinkit voidaan hakea pikakomennolla
    Given tarkastettu esimerkkiblogivinkki on tallennettu tietokantaan
    And tarkastettu esimerkkikirjavinkki on tallennettu tietokantaan
    And tarkastettu esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "kt ab" on syötetty
    Then tuloste sisältää haetun esimerkkiblogivinkin
    And tuloste sisältää haetun esimerkkiartikkelivinkin
    And tuloste ei sisällä esimerkkikirjavinkkiä


  Scenario: Kaikki tarkastetut vinkit voidaan hakea yhdistelmä-pikakomennolla
    Given tarkastettu esimerkkiblogivinkki on tallennettu tietokantaan
    And tarkastettu esimerkkikirjavinkki on tallennettu tietokantaan
    And tarkastettu esimerkkiartikkelivinkki on tallennettu tietokantaan
    And pikakomento "kt kab" on syötetty
    Then tuloste sisältää haetun esimerkkiartikkelivinkin
    And tuloste sisältää haetun esimerkkiblogivinkin
    And tuloste sisältää haetun esimerkkikirjavinkin

