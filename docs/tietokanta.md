# Tietokannan tietokantataulut ja niiden luonti

## Kirjavinkki

### Luonti

```SQL
CREATE TABLE Kirjavinkki (
    title string,
    author string,
    is_read int
)
```

### Ominaisuudet

- title     - vinkatun kirjan nimi, merkkijono
- author    - kirjailijan nimi, merkkijono
- is_read   - onko vinkki luettu, int (0: ei, 1: on)
