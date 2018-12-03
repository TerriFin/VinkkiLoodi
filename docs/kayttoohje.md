# Käyttöohje

Tällä ohjelmalla voidaan lisätä lukuvinkkejä järjestelmään, listata lisätyt vinkit ja merkata vinkkejä luetuksi halutessaan.

## Asennus

Lataa projekti Githubista ja käynnistä ohjelma komennolla _gradle run_.

## Ohjelman käyttö

### Käyttöliittymä

Ohjelmaa ohjataan komentorivikäyttöliittymällä, eli kaikki kommunikointi ohjelman kanssa tapahtuu tekstin välityksellä. Kun käyttäjä haluaa edetä ohjelmassa, hän kirjoittaa näppäimistöllään oikean komennon ja painaa ENTER/RETURN. Muuta ei tarvitse!

### Ohjelman peruskäyttö

Niin sanotussa päävalikossa on kahdeksan vaihtoehtoa:

**1 - Lisää vinkki** Kuten käyttöliittymä viestii, "1"-komennolla pääsee lisäämään uuden lukuvinkin järjestelmään.

**2 - Listaa vinkit** Tällä komennolla listaat kaikki lisätyt lukuvinkit.

**3 - Nopea haku** Hae järjestelmästä hakusanalla vinkkejä.

**4 - Tarkka haku** Hae järjestelmästä hakusanalla vinkkejä, mutta tässä voit tarkemmin valita, minkälaisia vinkkejä haet. Voit esimerkiksi hakea kirjoja, blogeja, ja artikkeleita, sekä vinkkejä tekijällä ja otsikolla. Voit myös hakea tarkastettamattomia vinkkejä ja tarkastettuja vinkkejä erikseen.

**5 - Päivitä vinkki** Tällä komennolla voit päivittää jo lisätyn vinkin. Tämä tapahtuu syöttämällä lukuvinkin otsikon hakukenttään. Ei tarvitse välittää suurista tai pienistä kirjamista tai syötteen edeltävistä tai jälkeisistä välilyönneistä. Täytyy kuitenkin varmistaa, että jos otsikon sisäiset välilyönnit ovat oikein syötteessä, muuten järjestelmä ei tunnista sitä. Löydettyään vinkin järjestelmästä, voit muuttaa vinkin jokaista osa-aluetta erikseen. Voit muuttaa otsikon, tekijän, merkitä luetuksi yms.

**6 - Listaa pikakomennot** Tässä näet kaikki käytettävissä olevat pikakomennot. Niitä syöttämällä päävalikosta voit tehdä tiettyjä asioita nopeasti ilman, että tarvitsee navigoida valikkojen kautta. Lista pikakomennoista ja niiden merkityksistä löytyy alla.

**par - Avaa parseri**

**X - Sammuta ohjelma** Tällä komennolla sammutat ohjelman. Tiedot tallentuvat automaattisesti, eli sitä ei tarvitse erikseen tehdä.

### Pikakomennot

Näitä syöttämällä päävalikkoon (ilman hipsuja) pääset nopeasti ohjelman eri kohtiin vaivatta:

**Lisää vinkkejä:**

\*'lk' = lisää kirjavinkki

'la' = lisää artikkelivinkki

'lb' = lisää blogivinkki\*

**Hae vinkkejä:**

\*'s' + hakusana = hae vinkkejä hakusanalla

'so' + hakusana = hae vinkkejä otsikolla

'st' + hakusana = hae vinkkejä tekijällä

'et' = listaa tarkastamattomat vinkit (et = "ei-tarkastetut")

'kt' = listaa tarkastetut vinkit (kt = "kyllä-tarkastetut")\*
