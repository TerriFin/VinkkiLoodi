Feature: Käyttäjä voi hyödyntää pikakomentoja

    Scenario: Kirja voidaan tallentaa pikakomennolla
        Given  pikakomento "lk" on syötetty
        When Nimi "testinimi" on syötetty
        And Otsikko "otsikkotesti" on syötetty
        And ISBN "12345678isb" on syötetty
        And komento Listaa vinkit on valittu
        Then tuloste sisältää "testinimi"


