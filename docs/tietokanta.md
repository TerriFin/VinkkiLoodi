# Tietokannan tietokantataulut ja niiden luonti

## Vinkki

### Luonti

```SQL
CREATE TABLE Vinkki (
    id INTEGER PRIMARY KEY,
    type int
)
```

### Ominaisuudet

Vinkkien supertaulu, sisältää jokaisen vinkin id:n ja tyypin

- id            - vinkin uniikki id
- type          - vinkin tyyppi, (1 = kirja, 2 = artikkeli, 3 = blogipostaus)

## Kirjavinkki

### Luonti

```SQL
CREATE TABLE Kirjavinkki (
    id INTEGER,
    title string,
    author string,
    isbn string,
    checked_out int,
    FOREIGN KEY(id) REFERENCES Vinkki(id)
)
```

### Ominaisuudet

- id            - vinkin id, viittaa Vinkki-tauluun 
- title         - vinkatun kirjan nimi, merkkijono
- author        - kirjailijan nimi, merkkijono
- isbn          - kirjan ISBN tunnus
- checked_out   - onko kirja luettu (0 = ei, 1 = kyllä)

## Blogivinkki

### Luonti

```SQL
CREATE TABLE Blogivinkki (
    id INTEGER,
    title string,
    author string,
    url string,
    checked_out int,
    FOREIGN KEY(id) REFERENCES Vinkki(id),
)
```

### Ominaisuudet

- id            - vinkin id, viittaa Vinkki-tauluun 
- title         - vinkatun blogipostauksen nimi, merkkijono
- author        - kirjoittajan nimi, merkkijono
- url           - blogipostauksen URL
- checked_out   - onko blogipostaus luettu

## Artikkelivinkki

### Luonti

```SQL
CREATE TABLE Artikkelivinkki (
    id INTEGER,
    title string,
    author string,
    published_in string,
    checked_out int,
    FOREIGN KEY(id) REFERENCES Vinkki(id),
)
```

### Ominaisuudet

- id            - vinkin id, viittaa Vinkki-tauluun 
- title         - vinkatun artikkelin nimi, merkkijono
- author        - kirjoittajan nimi, merkkijono
- published_in  - artikkelin julkaisupaikka
- checked_out   - onko artikkeli luettu
