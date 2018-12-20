# Raportti projektin kulusta

## Kehittäjätiimi

Sami Lahtinen, Sami Saukkonen, Niko Hernesniemi, Joona Halonen, Thomas Stenius

## Sprinttien kulku

### Sprintti 1

Ensimmäinen sprintti käytettiin pääasiassa projektin setup- ja konfigurointipuolen valmisteluun. Sprintin tavoitteena oli luoda ohjelmasta yksinkertainen versio, joka käsitteli vain kirjavinkkien tallentamisen ja näyttämisen käyttäjälle. Koska meidän sprintti jäi hiukan lyhyeksi aloitustilaisuuden ja asiakastapaamisten aikatauluttamisen takia, työhön käytettävä aika jäi melko vähäiseksi.

JUnit ja Cucumber muodostuivat ensimmäisen sprintin pääongelmiksi ja Cucumber-testaus jouduttiin poistamaan käytöstä, koska testit eivät toimineet, ja testikattavuus yllättäen putosi voimakkaasti ilman selkeää syytä. Myöhemmin havaittiin että tämä johtui ```System.exit()``` kutsusta testien aikana.

### Sprintti 2

Toinen sprintti oli kaikista sprinteistä onnistunein. Kaikki tavoitteet saavutettiin, ja saatiin sen lisäksi korjattua edellisen sprintin ongelmat. Lukuvinkkien lisäksi saatiin artikkelit ja blogit mukaan. Samalla saatiin Windowsin ja Macin tiedostojenkäsittelyongelmat, jotka havaittiin sprintin aikana, korjattua. Cucumberit jätettiin tässä vaiheessa vielä pois, koska niissä oli niin paljon ongelmia edellisessä sprintissä, ja haluttiin keskittyä asiakkaan antamiin toimintotavoitteisiin. JUnit-testaus pidettiin kuitenkin hyvällä tasolla.

### Sprintti 3

Kolmas sprintti seurasi edellisen menestystä ja suuri määri toimintoja saatin toteuttettua. Sprintin aikana Cucumberin ongelmat saatin selvitettyä ja pystyimme integroimaan vanhat Cucumber-testit onnistuneesti tuotantoon; lisäksi Cucumberin testejä saatin laajennettua vaikka muutaman user storyn testit jäivätkin vajaiksi ja storyt eivät täyttäneet sovittua Definition of Donea. Tästä huolimatta Cucumber-testien laajentaminen jatkui työläänä ja aikaa syövänä.

### Sprintti 4

Viimeinen sprintti keskittyi ohjelman refaktorointiin ja paranteluun, sekä testauksen viimeistelyyn. Erityisesti tietokantaluokan koko kutistui huomattavasti sen jälkeen kun käyttöön otettiin uusi filtteröintiluokka. Edellisestä sprintistä jäljelle jääneitä Cucumber-testejä viimeisteltiin ja täydennettiin. Likimain kaikki tekniset ongelmat oli tähän mennessä ratkaistu, joten työssä ei ilmennyt varsinaisia hankaluuksia. Käyttöliittymä sai myös vähän väriä ja näytti melko hyvältä.

## Projektin kulun yhteenveto

Projektissa oli paljon hyviä asioita, mutta myös jonkun verran huonoja. Ryhmä toteutti toimivaa ja onnestunutta ohjelmistoa, joissa painotetiin hyvään testikattavuutten sekä yksikkö- että Cucumber-puolella. Tietokanta ja DAO-toteutukset toimivat projektin aikana moitteetta, ainoan poikkeuksena demotilaisuuden testitietokannan katastrofaalinen häviäminen ja Windowsilla testitietokannan poistaminen vaati erilaisen tiedestopolun. 

**Hyviin asioihin kuului seuraavat asiat:**
- Tuotettiin toimivaa softaa, joka on itsessään mielekästä.
- Hyvä testikattavuus, sekä yksikkö- että Cucumber-puolella.
- Ainoa kerta kun tietokanta ei toiminut oli demossa.
- Pääasiassa priorisoidut user storyt toteuttiin kussakin sprintissä.
- Asiakas oli mukava ja asiakkaan kanssa toimiminen onnistui hyvin.
- Sprint backlog oli selkeä ja edisti työtehtävien kommunikointia.

**Huonoihin asioihin taas kuului seuraavat asiat:**
- Työt aloitettiin usein liian myöhään.
- Kaikilla olisi voinut olla sama kehitysympäristö. Nyt oli ryhmäläisillä Mac, Windows ja Linux, joka aiheutti yllättävän paljon ongelmia, joiden korjaamiseen meni paljon aikaa.
- Työskentely olisi voinut alkaa samaan aikaan ja/tai työskennellä samassa paikassa. Ellei ole sovittuja tapaamisia, menee helposti niin, että ihmiset koodavat eri aikoihin, eikä kommunikointi oikein toimi.
- Sprintin backlogia olisi voinut päivittää aktiivisemmin. Paikoittain meni niin, että joku aloitti jonkun asian työstämisen päivittämättä backlogia, ja sitten tuli tehtyä päällekkäistä työtä.
- Projektin masteri hajoili vähän liikaa, ja yleensä vika oli joku erittäin pieni asia, esim. käyttämätön import.

## Mitä opittiin

**Cucumber:**
- On hankala käyttää ja tämän kokoiseen projektiin melko raskas
- Testejä on hidas kirjoittaa
- Stepdefs.java on hankala ylläpitää, ja sen koko kasvaa rajusti projektin edetessä
- Cucumberin perusidea on epätäydellinen
- Featureiden kirjoittaminen vaatii käytännössä teknistä kykyä
- Kirjoittajan pitää tietää mitä steppejä on määritelty

**Setup:**
- Vie aikaa, vielä enemmän kun arvattiin
- Erityisesti testausframeworkit olivat mutkikkaita ja aiheuttivat alussa ongelmia

**Käyttöjärjestelmät:**
- Käyttöjärjestelmät toimivat eritavoilla ja aiheuttavat kaikenlaisia bugeja.

**Scrum:**
- Scrumia käytännössä ja sovellettuna
- Daily scrumin soveltaminen Telegram-kommunikointikanaville, muuten toimiva, mutta työaikojen vaihtelu heikensi saatua hyötyä
- Retrospektiiveistä saatiin oikeaa hyötyä ja niiden avulla voitiin parantaa omaa työskentelyä

**Testaus:**
- Testien ajaminen usein kannattaa
- Monesti pienet tyylivirheet johtivat siihen että masterin buildit epäonnistuivat

## Mitä oltaisiin haluttu oppia
- Feature branchit olisi voinut olla mukava oppia paremmin.
- Ohjattua retrospektiiviä tai sprint planningiä.
- Ohjattua product backlogin ja/tai sprint backlogin suunnittelua.
- Ohjelmointipatternien hyödyntäminen projektissa.

## Mikä oli turhaa
- Testaukseen kulutettu määrä suhteessa oikeasti tehtyyn työhön. Tuntui, että tavallista enemmän aikaa meni testaukseen, osittain Cucumberin syystä.
- Vaatimus 80% testikattavuudesta.
- Viikon 4 laskarit olivat _turhan_ raskaat.


## Sekalaista

[Ryhmän keskimääräinen reaktio Cucumber-testeihin](https://www.youtube.com/watch?v=pXv44YL_Gio)
