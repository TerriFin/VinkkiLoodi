# Definition of Done

Jokin ohjelmiston toiminto todetaan valmiiksi Definition of Donen mukaisesti seuraavin perustein:

## Testaus

- Jokaisen komponentin jokainen metodi, jonka logiikka ei ole suoraan itsestäänselvä on testattu JUnit testeillä siten, että testattuna on ainakin onnistunut käyttötapaus ja oleelliset epäonnistuvat tapaukset.
- Jokaisen komponenttiparin kanssakäymiseen on oltava integraatiotestit, joilla testataan komponenttien toimiva käyttötapaus ja oleelliset epäonnistuvat tapaukset.
- Testien rivikattavuus toteutetusta koodista on 80% tai tarpeen mukaan enemmän
- Jokaisen user storyn yhteydessä määriteltyjä hyväksymistestejä vastaamaan on tehty Cucumber-testit
- JUnit-testit ja Cucumber-testit ajetaan CI palvelimella jokaisen commitin yhteydessä
- Kaikki testit onnistuvat

## Arkkitehtuuri

- Ohjelmisto on rakennettu kerrosarkkitehtuurin mukaisesti
- Ohjelmiston osista on olemassa yleinen, ajantasainen dokumentaatio luokkakaavion tai vastaavan muodossa

## Tyyli

- Ohjelmiston komponentit, ja komponenttien osat, on nimetty selkeästi ja nimien merkitys on selkeä 
