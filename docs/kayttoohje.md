# Käyttöohje

Tällä ohjelmalla voit tallettaa erilaisia lukuvinkkejä järjestelmään, ja hakea niitä järjestelmästä tietyillä hakukriteereillä. Voit myös merkata niitä luetuksi/tarkastetuksi. Lukuvinkki voi olla kirja, artikkeli tai blogi.

## Sisällysluettelo

1. [Asennus](#asennus)
2. [Käyttöliittymä](#kayttoliittyma)
    1. [Päävalikko](#paavalikko)
    2. [Pikakomennot](#pikakomennot)
    3. [Parseriohje](#parseriohje)

## Asennus <a name="asennus"></a>

Lataa ohjelman julkaisu [Githubista](https://github.com/TerriFin/VinkkiLoodi/releases) ja käynnistä ohjelma komennolla _java -jar VinkkiLoodi-all.jar_.

## Käyttöliittymä <a name="kayttoliittyma"></a>

Ohjelmaa ohjataan komentorivikäyttöliittymällä, eli kaikki kommunikointi ohjelman kanssa tapahtuu tekstin välityksellä. Kun käyttäjä haluaa edetä ohjelmassa, hän kirjoittaa näppäimistöllään oikean komennon ja painaa ENTER/RETURN. Muuta ei tarvitse!

### Päävalikko <a name="paavalikko"></a>

Päävalikossa on kahdeksan vaihtoehtoa:

**1 - Lisää vinkki** - Kuten käyttöliittymä viestii, "1"-komennolla pääset lisäämään uuden lukuvinkin järjestelmään. Tässä sinun pitää ensin valita, onko vinkki kirja, blogipostaus, vai artikkeli. Sitten järjestelmä pyytää käyttäjää lisäämään seuraavat asiat: tekijä, otsikko, ISBN (mikäli kirja), URL (mikäli blogipostaus) ja julkaisija (mikäli artikkeli).

**2 - Listaa vinkit** - Tällä komennolla listaat kaikki lisätyt lukuvinkit.

**3 - Nopea haku** - Hae järjestelmästä hakusanalla vinkkejä.

**4 - Tarkka haku** - Hae järjestelmästä hakusanalla vinkkejä, mutta tässä voit tarkemmin valita, minkälaisia vinkkejä haet. Voit esimerkiksi hakea kirjoja, blogeja, ja artikkeleita, sekä vinkkejä tekijällä ja otsikolla. Voit myös hakea tarkastettamattomia vinkkejä ja tarkastettuja vinkkejä erikseen.

**5 - Päivitä vinkki** - Tällä komennolla voit päivittää jo lisätyn vinkin. Tämä tapahtuu syöttämällä lukuvinkin otsikon hakukenttään. Ei tarvitse välittää suurista tai pienistä kirjamista tai syötteen edeltävistä tai jälkeisistä välilyönneistä. Täytyy kuitenkin varmistaa, että jos otsikon sisäiset välilyönnit ovat oikein syötteessä, muuten järjestelmä ei tunnista sitä. Löydettyään vinkin järjestelmästä, voit muuttaa vinkin jokaista osa-aluetta erikseen. Voit muuttaa otsikon, tekijän, merkitä luetuksi yms.

**6 - Listaa pikakomennot** - Tässä näet kaikki käytettävissä olevat pikakomennot. Niitä syöttämällä päävalikosta voit tehdä tiettyjä asioita nopeasti ilman, että tarvitsee navigoida valikkojen kautta. Lista pikakomennoista ja niiden merkityksistä löytyy alla.

**par - Avaa parseri** - Tällä komennolla pääset ns. "parseriin", minne voit kirjoittaa pidempiä, monisanaisia komentoja, joiden avulla voit suorittaa monimutkaisempia asioita yhdellä komennolla. Tarkemmat ohjeet parserista löytyvät "Parseriohje"-kohdasta.

**X - Sammuta ohjelma** - Tällä komennolla sammutat ohjelman. Tiedot tallentuvat automaattisesti, eli sitä ei tarvitse erikseen tehdä.

### Pikakomennot <a name="pikakomennot"></a>

Näitä syöttämällä päävalikkoon (ilman hipsuja) pääset nopeasti ohjelman eri kohtiin vaivatta:

**Lisää vinkkejä:**

*'lk' = lisää kirjavinkki*

*'la' = lisää artikkelivinkki*

*'lb' = lisää blogivinkki*

**Päivitä vinkkejä:**

*'pv' + hakusana = päivitä vinkki hakusanalla (hakusanaan laitetaan vinkin otsikko)*

*'ta' + hakusana = merkitse vinkki tarkastetuksi (hakusanaan laitetaan vinkin otsikko)*

**Hae vinkkejä:**

*'s' + hakusana = hae vinkkejä hakusanalla*

*'so' + hakusana = hae vinkkejä otsikolla*

*'st' + hakusana = hae vinkkejä tekijällä*

*'et' = listaa tarkastamattomat vinkit (et = "ei-tarkastetut")*

*'kt' = listaa tarkastetut vinkit (kt = "kyllä-tarkastetut")*

### Parseriohje

Parserissa voit kirjoittaa pidempiä, monisanaisia komentoja, joiden avulla voit suorittaa monimutkaisempia asioita yhdellä komennolla. Pienillä/isoilla kirjaimilla ja ääkkösillä ei ole väliä.
Komennot erotetaan toisistaan välilyönneillä, ja attribuutit laitetaan
omien sulkujen sisään viittausmerkkien sisään.

Parseri pyrkii myös kysymään käyttäjältä korjauksia virheellisiin syötteisiin,
mutta aina se ei ole mahdollista ja käyttäjä joutuu tällöin syöttämään komentonsa uudestaan.

**Aloituskomennot ovat seuraavat:**

- [**Lisää**](#lisaa)
- [**Listaa**](#listaa)
- [**Nopeahaku**](#nopeahaku)
- [**Tarkkahaku**](#tarkkahaku)
- [**Päivitä**](#paivita)

#### Lisää <a name="lisaa"></a>

Lisää komento pyrkii lisäämään uuden vinkin systeemiin.
Sen jälkeen tarkennetaan vinkkityyppi jota halutaan lisätä (kirja, blogi, artikkeli),
Jonka jälkeen annetaan suluissa ja lainausmerkeissä vinkin ominaisuudet seuraavassa järjestyksessä:
("Tekijä" "Vinkin nimi" "Vinkin oma ominaisuus")

Esimerkki: *Lisää Kirja ("Eetu" "Eetun superkirja" "3124232")*

Esimerkissä kirjan ominaisuus on ISBN, joka on numerosarja. blogeissa se on URL ja artikkeleissa julkaisija.

#### Listaa <a name="listaa"></a>

Listaa komento listaa kaikki säilötyt vinkit. Se ei tarvitse muuta.

#### Nopeahaku <a name="nopeahaku"></a>

Nopeahaku ottaa yhden hakusanan sellaisenaan (ilman sulkuja ja lainausmerkkejä) itsensä jälkeen. Tätä muuttujaa käytetään hakusanana, jonka tulokset listataan.

Esimerkki: *Nopeahaku eetu*

Esimerkissä tulostetaan kaikki vinkit joiden tekijä tai nimi sisältää sanan "eetu"

#### Tarkkahaku <a name="tarkkahaku"></a>

Tarkkahaku tarvitsee joko yhden muuttujan jossa määritellään mitä haetaan tarkasti, ja joissain hauissa se tarvitsee vielä kolmannen hakusanan

Mahdolliset tarkat haut ovat:

- Kirjoja
- Blogeja
- Artikkeleita
- Tekijä
- Nimi
- Tarkastamattomat
- Tarkastetut

Kaikki muut paitsi -Tekijä ja -Nimi haku toimivat sellaisenaan, ja listaavat kukin oman joukkonsa vinkkejä.
-Tekijä ja -Nimi hauissa pitää vielä antaa kolmanneksi hakusanaksi jokin hakusana jolla etsitään vinkkejä,
samalla tavalla kuin nopeassa haussa.

Esimerkki 1: *Tarkkahaku kirjoja*

Tämä esimerkkihaku tulostaa kaikki kirjat.

Esimerkki 2: *Tarkkahaku Nimi Fire*

Tämä haku etsii kaikki vinkit joiden nimessä on sana "Fire"

#### Päivitä <a name="paivita"></a>

Päivitä pyrkii päivittämään vinkin. Sille annetaan tokaksi komennoksi hakusana, jolla pyritään etsimään vinkkiä sen nimen kautta (paras tapa lienee listata vinkit ja copy/pastea halutun vinkin nimi tähän kohtaan).
Kolmanneksi muuttujaksi sille annetaan uudet ominaisuudet sulkujen ja lainausmerkkien sisällä
samalla tavalla kuin vinkin lisäyksessä. Tässä annetaan vielä yksi ylimääräinen muuttuja sulkujen sisällä,
joka kuvaa onko kyseistä vinkkiä tarkistettu. Voit antaa joko arvon "1"/"0", "true"/"false" tai "tarkistettu"/"tarkastamaton".

Jos tämä komento ei löydä haluttua vinkkiä, komennon suoritus keskeytyy.

Esimerkki: *Päivitä Eetun blogi("uusiTekijänNimi" "uusiBloginNimi" "uusiURL" "false")*

Tässä esimerkki komennossa etsitään vinkki jonka nimi on "Eetun blogi", tämän jälkeen siihen päivitetään annetut arvot.
