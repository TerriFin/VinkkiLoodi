# Tietokannan tietokantataulut ja niiden luonti

## Kirjavinkki

### Luonti

```SQL
CREATE TABLE Kirjavinkki (
    id int,
    title string,
    author string,
    is_read int
)
```

### Ominaisuudet

- id        - uniikki id kirjavinkille
- title     - vinkatun kirjan nimi, merkkijono
- author    - kirjailijan nimi, merkkijono
- is_read   - onko vinkki luettu, int (0: ei, 1: on)
