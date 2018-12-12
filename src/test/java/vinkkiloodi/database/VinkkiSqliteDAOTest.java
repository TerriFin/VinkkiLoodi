package vinkkiloodi.database;

import filter.HakuBuilder;
import filter.Matcher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import vinkkiloodi.domain.ArtikkeliVinkki;
import vinkkiloodi.domain.BlogiVinkki;
import vinkkiloodi.domain.KirjaVinkki;
import vinkkiloodi.domain.Tyyppi;
import vinkkiloodi.domain.Vinkki;

public class VinkkiSqliteDAOTest {

    private VinkkiSqliteDAO dao;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        try {
            dao = new VinkkiSqliteDAO(folder.newFile("test.db").getPath());
        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*@After
    public void tearDown() {
        try {
            dao.dropDeadAndDie();
        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        File db = new File("test.db");
        
        if (db.exists()) {
            db.delete();
        }
    }*/
    @Test
    public void sqliteDAOMuodostaaTietokantaYhteyden() {
        try {
            assert (dao.getConnection() != null);
        } catch (SQLException ex) {
            assert (false);
        }
    }

    @Test
    public void lisaysLisaaElementinTietokantaan() {
        int alkuKoko = dao.getAll().size();

        KirjaVinkki vinkki = new KirjaVinkki("Testi", "Testi");

        dao.add(vinkki);

        assert (dao.getAll().size() > alkuKoko);
    }

    @Test
    public void elementtiLoytyyListaltaLisayksenJalkeen() {
        int alkuKoko = dao.getAll().size();

        KirjaVinkki vinkki = new KirjaVinkki("Testi2", "Testi2");

        dao.add(vinkki);

        boolean loytyi = false;

        for (Vinkki v : dao.getAll()) {
            if (v.getNimi().equals(vinkki.getNimi())) {
                loytyi = true;
            }
        }

        assert (loytyi);
    }

    @Test
    public void idHakuLoytaaOikeanElementin() {
        int alkuKoko = dao.getAll().size();

        KirjaVinkki vinkki = new KirjaVinkki("Testi3", "Testi3");
        KirjaVinkki vinkki2 = new KirjaVinkki("Testi4", "Testi4");

        dao.add(vinkki);
        dao.add(vinkki2);

        Vinkki tulos = dao.getById(vinkki.getId());

        assertEquals(tulos.getId(), vinkki.getId());
    }

    @Test
    public void olematonIdPalauttaaNull() {
        int alkuKoko = dao.getAll().size();

        KirjaVinkki vinkki = new KirjaVinkki("Testi5", "Testi5");

        dao.add(vinkki);

        Vinkki tulos = dao.getById(Integer.MAX_VALUE);

        assertEquals(tulos, null);
    }

    @Test
    public void kirjanPaivitysMuuttaaKirjoittajaa() {
        KirjaVinkki vinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen");

        dao.add(vinkki);

        KirjaVinkki uusiVinkki = new KirjaVinkki("Uusi Kirjoittaja", "Alkuperäinen");

        dao.update(vinkki.getId(), uusiVinkki);

        Vinkki tulos = dao.getById(vinkki.getId());

        assertEquals(tulos.getTekija(), "Uusi Kirjoittaja");
    }

    @Test
    public void kirjanPaivitysMuuttaaOtsikkoa() {
        KirjaVinkki vinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen");

        dao.add(vinkki);

        KirjaVinkki uusiVinkki = new KirjaVinkki("Alkuperäinen", "Uusi Otsikko");

        dao.update(vinkki.getId(), uusiVinkki);

        Vinkki tulos = dao.getById(vinkki.getId());

        assertEquals(tulos.getNimi(), "Uusi Otsikko");
    }

    @Test
    public void kirjanPaivitysMuuttaaLuettua() {
        KirjaVinkki vinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen");

        dao.add(vinkki);

        KirjaVinkki uusiVinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen", 1, "");

        dao.update(vinkki.getId(), uusiVinkki);

        Vinkki tulos = dao.getById(vinkki.getId());

        assertEquals(tulos.getTarkastettu(), 1);
    }
    
    @Test
    public void kirjanPaivitysMuuttaaISBN() {
        KirjaVinkki vinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen", 0, "4321");

        dao.add(vinkki);

        KirjaVinkki uusiVinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen", 0, "1234");

        dao.update(vinkki.getId(), uusiVinkki);

        KirjaVinkki tulos = (KirjaVinkki) dao.getById(vinkki.getId());

        assertEquals("1234", tulos.getISBN());
    }

    @Test
    public void blogipostauksenLisaysLisaaTietokantaan() {
        int alkuKoko = dao.getAll().size();

        BlogiVinkki vinkki = new BlogiVinkki("BlogiOtsikko", "BlogiKirjoittaja", "www.blogi.osoite", 0);

        dao.add(vinkki);

        assert (alkuKoko < dao.getAll().size());
    }

    @Test
    public void blogiSaaIDnLisayksessa() {
        BlogiVinkki vinkki = new BlogiVinkki("BlogiOtsikko", "BlogiKirjoittaja", "www.blogi.osoite", 0);

        dao.add(vinkki);

        assert (vinkki.getId() != 0);
    }

    @Test
    public void blogiLoytyyIDlla() {
        BlogiVinkki vinkki = new BlogiVinkki("BlogiOtsikko", "BlogiKirjoittaja", "www.blogi.osoite", 0);

        dao.add(vinkki);

        BlogiVinkki tulos = (BlogiVinkki) dao.getById(vinkki.getId());

        assert (tulos != null && tulos.getId() == vinkki.getId());
    }

    @Test
    public void bloginOtsikkoPaivittyy() {
        BlogiVinkki vinkki = new BlogiVinkki("BlogiOtsikko", "BlogiKirjoittaja", "www.blogi.osoite", 0);

        dao.add(vinkki);

        BlogiVinkki paivitys = new BlogiVinkki("Uusi", "Uusi", "Uusi", 0);

        dao.update(vinkki.getId(), paivitys);

        BlogiVinkki paivitetty = (BlogiVinkki) dao.getById(vinkki.getId());

        assert (paivitetty.getNimi().equals("Uusi"));
    }

    @Test
    public void bloginKirjoittajaPaivittyy() {
        BlogiVinkki vinkki = new BlogiVinkki("BlogiOtsikko", "BlogiKirjoittaja", "www.blogi.osoite", 0);

        dao.add(vinkki);

        BlogiVinkki paivitys = new BlogiVinkki("Uusi", "Uusi", "Uusi", 0);

        dao.update(vinkki.getId(), paivitys);

        BlogiVinkki paivitetty = (BlogiVinkki) dao.getById(vinkki.getId());

        assert (paivitetty.getTekija().equals("Uusi"));
    }

    @Test
    public void bloginURLPaivittyy() {
        BlogiVinkki vinkki = new BlogiVinkki("BlogiOtsikko", "BlogiKirjoittaja", "www.blogi.osoite", 0);

        dao.add(vinkki);

        BlogiVinkki paivitys = new BlogiVinkki("Uusi", "Uusi", "Uusi", 0);

        dao.update(vinkki.getId(), paivitys);

        BlogiVinkki paivitetty = (BlogiVinkki) dao.getById(vinkki.getId());

        assert (paivitetty.getUrl().equals("Uusi"));
    }

    @Test
    public void bloginTarkistettuPaivittyy() {
        BlogiVinkki vinkki = new BlogiVinkki("BlogiOtsikko", "BlogiKirjoittaja", "www.blogi.osoite", 0);

        dao.add(vinkki);

        BlogiVinkki paivitys = new BlogiVinkki("Uusi", "Uusi", "Uusi", 1);

        dao.update(vinkki.getId(), paivitys);

        BlogiVinkki paivitetty = (BlogiVinkki) dao.getById(vinkki.getId());

        assertEquals(paivitetty.getTarkastettu(), 1);
    }

    @Test
    public void artikkelinLisaysLisaaTietokantaan() {
        int alkuKoko = dao.getAll().size();

        ArtikkeliVinkki vinkki = new ArtikkeliVinkki("ArtikkeliOtsikko", "ArtikkeliKirjoittaja", "Julkaisija", 0);

        dao.add(vinkki);

        assertTrue(alkuKoko < dao.getAll().size());
    }

    @Test
    public void artikkeliSaaIDnLisayksessa() {
        ArtikkeliVinkki vinkki = new ArtikkeliVinkki("ArtikkeliOtsikko", "ArtikkeliKirjoittaja", "Julkaisija", 0);

        dao.add(vinkki);

        assert (vinkki.getId() != 0);
    }

    @Test
    public void artikkeliLoytyyIDlla() {
        ArtikkeliVinkki vinkki = new ArtikkeliVinkki("ArtikkeliOtsikko", "ArtikkeliKirjoittaja", "Julkaisija", 0);

        dao.add(vinkki);

        ArtikkeliVinkki tulos = (ArtikkeliVinkki) dao.getById(vinkki.getId());

        assert (tulos != null && tulos.getId() == vinkki.getId());
    }

    @Test
    public void artikkelinOtsikkoPaivittyy() {
        ArtikkeliVinkki vinkki = new ArtikkeliVinkki("ArtikkeliOtsikko", "ArtikkeliKirjoittaja", "Julkaisija", 0);

        dao.add(vinkki);

        ArtikkeliVinkki paivitys = new ArtikkeliVinkki("Uusi", "Uusi", "Uusi", 0);

        dao.update(vinkki.getId(), paivitys);

        ArtikkeliVinkki paivitetty = (ArtikkeliVinkki) dao.getById(vinkki.getId());

        assert (paivitetty.getNimi().equals("Uusi"));
    }

    @Test
    public void artikkelinTekijaPaivittyy() {
        ArtikkeliVinkki vinkki = new ArtikkeliVinkki("ArtikkeliOtsikko", "ArtikkeliKirjoittaja", "Julkaisija", 0);

        dao.add(vinkki);

        ArtikkeliVinkki paivitys = new ArtikkeliVinkki("Uusi", "Uusi", "Uusi", 0);

        dao.update(vinkki.getId(), paivitys);

        ArtikkeliVinkki paivitetty = (ArtikkeliVinkki) dao.getById(vinkki.getId());

        assert (paivitetty.getTekija().equals("Uusi"));
    }

    @Test
    public void artikkelinJulkaisijaPaivittyy() {
        ArtikkeliVinkki vinkki = new ArtikkeliVinkki("ArtikkeliOtsikko", "ArtikkeliKirjoittaja", "Julkaisija", 0);

        dao.add(vinkki);

        ArtikkeliVinkki paivitys = new ArtikkeliVinkki("Uusi", "Uusi", "Uusi", 0);

        dao.update(vinkki.getId(), paivitys);

        ArtikkeliVinkki paivitetty = (ArtikkeliVinkki) dao.getById(vinkki.getId());

        assert (paivitetty.getJulkaisija().equals("Uusi"));
    }

    @Test
    public void artikkelinTarkistusPaivittyy() {
        ArtikkeliVinkki vinkki = new ArtikkeliVinkki("ArtikkeliOtsikko", "ArtikkeliKirjoittaja", "Julkaisija", 0);

        dao.add(vinkki);

        ArtikkeliVinkki paivitys = new ArtikkeliVinkki("Uusi", "Uusi", "Uusi", 1);

        dao.update(vinkki.getId(), paivitys);

        ArtikkeliVinkki paivitetty = (ArtikkeliVinkki) dao.getById(vinkki.getId());

        assertEquals(paivitetty.getTarkastettu(), 1);
    }

    @Test
    public void paivitysMuuttaaKirjoittajaa() {
        KirjaVinkki vinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen");

        dao.add(vinkki);

        KirjaVinkki uusiVinkki = new KirjaVinkki("Uusi Kirjoittaja", "Alkuperäinen");

        dao.update(vinkki.getId(), uusiVinkki);

        Vinkki tulos = dao.getById(vinkki.getId());

        assertEquals(tulos.getTekija(), "Uusi Kirjoittaja");
    }

    @Test
    public void päivitysMuuttaaOtsikkoa() {
        KirjaVinkki vinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen");

        dao.add(vinkki);

        KirjaVinkki uusiVinkki = new KirjaVinkki("Alkuperäinen", "Uusi Otsikko");

        dao.update(vinkki.getId(), uusiVinkki);

        Vinkki tulos = dao.getById(vinkki.getId());

        assertEquals(tulos.getNimi(), "Uusi Otsikko");
    }

    @Test
    public void paivitysMuuttaaLuettua() {
        KirjaVinkki vinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen");

        dao.add(vinkki);

        KirjaVinkki uusiVinkki = new KirjaVinkki("Alkuperäinen", "Alkuperäinen", 1, "");

        dao.update(vinkki.getId(), uusiVinkki);

        Vinkki tulos = dao.getById(vinkki.getId());

        assertEquals(tulos.getTarkastettu(), 1);
    }

    @Test
    public void kirjaLoytyyTekijalla() {
        Vinkki vinkki = new KirjaVinkki("Uniikkitekija", "testi", 0, "12345678");
        dao.add(vinkki);

        Matcher kirjanHaku = new HakuBuilder().tekijaSisaltaa("Uniikkitekija").onTyyppia(Tyyppi.Kirja).build();
        List<Vinkki> haku = dao.matches(kirjanHaku);

        assertEquals(1, haku.size());
    }

    @Test
    public void BlogiLoytyyTekijalla() {

        Vinkki vinkki = new BlogiVinkki("Uniikkitekija", "testi", "hienptestnurl.net", 0);
        dao.add(vinkki);

        Matcher bloginHaku = new HakuBuilder().tekijaSisaltaa("Uniikkitekija").onTyyppia(Tyyppi.Blog).build();
        List<Vinkki> haku = dao.matches(bloginHaku);

        assertEquals(1, haku.size());
    }

    @Test
    public void ArtikkeliLoytyyTekijalla() {

        Vinkki vinkki = new ArtikkeliVinkki("Uniikkitekija", "testi", "julkaisija", 0);
        dao.add(vinkki);

        Matcher artikkelinHaku = new HakuBuilder().tekijaSisaltaa("Uniikkitekija").onTyyppia(Tyyppi.Artikkeli).build();
        List<Vinkki> haku = dao.matches(artikkelinHaku);

        assertEquals(1, haku.size());
    }

    @Test
    public void VinkitLoytyvatTekijalla() {
        Vinkki vinkki = new KirjaVinkki("Uniikkitekija", "testi", 0, "12345678");
        Vinkki vinkki2 = new BlogiVinkki("Uniikkitekija", "testi", "www.url.ei", 0);
        Vinkki vinkki3 = new ArtikkeliVinkki("Uniikkitekija", "testi", "julkaisija", 0);

        dao.add(vinkki);
        dao.add(vinkki2);
        dao.add(vinkki3);

        Matcher hakuTekijalla = new HakuBuilder().tekijaSisaltaa("Uniikkitekija").build();
        List<Vinkki> haku = dao.matches(hakuTekijalla);

        assertEquals(3, haku.size());
    }

    @Test
    public void kirjaLoytyyNimella() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 0, "12345678");
        dao.add(vinkki);
        
        Matcher kirjanHaku = new HakuBuilder().nimiSisaltaa("Uniikkinimi").onTyyppia(Tyyppi.Kirja).build();
        List<Vinkki> haku = dao.matches(kirjanHaku);

        assertEquals(1, haku.size());
    }

    @Test
    public void BlogiLoytyyNimella() {

        Vinkki vinkki = new BlogiVinkki("testi", "Uniikkinimi", "hienptestnurl.net", 0);
        dao.add(vinkki);

        Matcher bloginHaku = new HakuBuilder().nimiSisaltaa("Uniikkinimi").onTyyppia(Tyyppi.Blog).build();
        List<Vinkki> haku = dao.matches(bloginHaku);

        assertEquals(1, haku.size());
    }

    @Test
    public void ArtikkeliLoytyyNimella() {

        Vinkki vinkki = new ArtikkeliVinkki("testi", "Uniikkinimi", "julkaisija", 0);
        dao.add(vinkki);

        Matcher artikkelinHaku = new HakuBuilder().nimiSisaltaa("Uniikkinimi").onTyyppia(Tyyppi.Artikkeli).build();
        List<Vinkki> haku = dao.matches(artikkelinHaku);

        assertEquals(1, haku.size());
    }

    @Test
    public void VinkitLoytyvatNimella() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 0, "12345678");
        Vinkki vinkki2 = new BlogiVinkki("testi", "Uniikkinimi", "www.url.ei", 0);
        Vinkki vinkki3 = new ArtikkeliVinkki("testi", "Uniikkinimi", "julkaisija", 0);

        dao.add(vinkki);
        dao.add(vinkki2);
        dao.add(vinkki3);

        Matcher kysely = new HakuBuilder().nimiSisaltaa("Uniikkinimi").build();
        List<Vinkki> haku = dao.matches(kysely);

        assertEquals(3, haku.size());
    }
    
    @Test
    public void kaikkiKirjaVinkitLoytyvat() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 0, "12345678");
        Vinkki vinkki2 = new KirjaVinkki("testi2", "Uniikkinimi2", 0, "12345679");
        Vinkki vinkki3 = new KirjaVinkki("testi3", "Uniikkinimi3", 0, "12345680");
        
        Vinkki vinkkiBlogi = new BlogiVinkki("testi", "Uniikkinimi", "www.url.ei", 0);
        Vinkki vinkkiArtikkeli = new ArtikkeliVinkki("testi", "Uniikkinimi", "julkaisija", 0);
        
        dao.add(vinkki);
        dao.add(vinkki2);
        dao.add(vinkki3);
        dao.add(vinkkiBlogi);
        dao.add(vinkkiArtikkeli);
        
        Matcher kysely = new HakuBuilder().onTyyppia(Tyyppi.Kirja).build();
        List<Vinkki> haku = dao.matches(kysely);
        
        for (Vinkki v : haku) {
            if (v.getTyyppi() != Tyyppi.Kirja) {
                assert(false);
            }
        }
        
        assert(haku.size() == 3);
    }
    
    @Test
    public void kaikkiBlogiVinkitLoytyvat() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 0, "12345678");
        Vinkki vinkki2 = new KirjaVinkki("testi2", "Uniikkinimi2", 0, "12345679");
        Vinkki vinkki3 = new KirjaVinkki("testi3", "Uniikkinimi3", 0, "12345680");
        
        Vinkki vinkkiBlogi = new BlogiVinkki("testi", "Uniikkinimi", "www.url.ei", 0);
        Vinkki vinkkiArtikkeli = new ArtikkeliVinkki("testi", "Uniikkinimi", "julkaisija", 0);
        Vinkki vinkkiArtikkeli2 = new ArtikkeliVinkki("testi1", "Uniikkinimi2", "julkaisija1", 0);
        
        dao.add(vinkki);
        dao.add(vinkki2);
        dao.add(vinkki3);
        dao.add(vinkkiBlogi);
        dao.add(vinkkiArtikkeli);
        dao.add(vinkkiArtikkeli2);
        
        Matcher kysely = new HakuBuilder().onTyyppia(Tyyppi.Blog).build();
        List<Vinkki> haku = dao.matches(kysely);
        
        for (Vinkki v : haku) {
            if (v.getTyyppi() != Tyyppi.Blog) {
                assert(false);
            }
        }
        
        assert(haku.size() == 1);
    }
    
    @Test
    public void kaikkiArtikkeliVinkitLoytyvat() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 0, "12345678");
        Vinkki vinkki2 = new KirjaVinkki("testi2", "Uniikkinimi2", 0, "12345679");
        Vinkki vinkki3 = new KirjaVinkki("testi3", "Uniikkinimi3", 0, "12345680");
        
        Vinkki vinkkiBlogi = new BlogiVinkki("testi", "Uniikkinimi", "www.url.ei", 0);
        Vinkki vinkkiArtikkeli = new ArtikkeliVinkki("testi", "Uniikkinimi", "julkaisija", 0);
        Vinkki vinkkiArtikkeli2 = new ArtikkeliVinkki("testi1", "Uniikkinimi2", "julkaisija1", 0);
        
        dao.add(vinkki);
        dao.add(vinkki2);
        dao.add(vinkki3);
        dao.add(vinkkiBlogi);
        dao.add(vinkkiArtikkeli);
        dao.add(vinkkiArtikkeli2);
        
        Matcher kysely = new HakuBuilder().onTyyppia(Tyyppi.Artikkeli).build();
        List<Vinkki> haku = dao.matches(kysely);
        
        for (Vinkki v : haku) {
            if (v.getTyyppi() != Tyyppi.Artikkeli) {
                assert(false);
            }
        }
        
        assert(haku.size() == 2);
    }
    
    @Test
    public void tarkastamatonVinkkiLoytyy() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 1, "12345678");
        Vinkki vinkki2 = new KirjaVinkki("testi2", "Uniikkinimi2", 1, "14345678");
        
        Vinkki vinkkiBlogi = new BlogiVinkki("testi", "Uniikkinimi", "www.url.ei", 0);
        
        dao.add(vinkki);
        dao.add(vinkki2);
        dao.add(vinkkiBlogi);
        
        Matcher kysely = new HakuBuilder().tarkastamaton().build();
        List<Vinkki> haku = dao.matches(kysely);
        
        assert(haku.size() == 1);
    }
    
    @Test
    public void tarkastettuVinkkiLoytyy() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 1, "12345678");
        
        Vinkki vinkkiBlogi = new BlogiVinkki("testi", "Uniikkinimi", "www.url.ei", 0);
        Vinkki vinkkiBlogi2 = new BlogiVinkki("testi2", "Uniikkinimi2", "www.url.joo", 0);
        
        dao.add(vinkki);
        dao.add(vinkkiBlogi);
        
        Matcher kysely = new HakuBuilder().tarkastettu().build();
        List<Vinkki> haku = dao.matches(kysely);
        
        assert(haku.size() == 1);
    }
    
    @Test
    public void hakuBuilderiLoytaaKirjat() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 0, "123123");
        
        dao.add(vinkki);
        
        Matcher haku = new HakuBuilder().onTyyppia(Tyyppi.Kirja).build();
        
        List<Vinkki> vinkit = dao.matches(haku);
        
        assert(vinkit.size() == 1);
    }
    
    @Test
    public void hakuBuilderiLoytaaLukemattomatKirjat() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 0, "123123");
        Vinkki vinkki2 = new KirjaVinkki("testi2", "Uniikkinimi", 1, "432112");
        Vinkki vinkki3 = new KirjaVinkki("testi3", "Uniikkinimi", 1, "7654123");
        
        dao.add(vinkki);
        dao.add(vinkki2);
        dao.add(vinkki3);
        
        Matcher haku = new HakuBuilder().tarkastamaton().build();
        
        List<Vinkki> vinkit = dao.matches(haku);
        
        assertEquals(1, vinkit.size());
    }
    
    @Test
    public void hakuBuilderiLoytaaLuetutKirjat() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 0, "123123");
        Vinkki vinkki2 = new KirjaVinkki("testi2", "Uniikkinimi", 1, "432112");
        Vinkki vinkki3 = new KirjaVinkki("testi3", "Uniikkinimi", 1, "7654123");
        
        dao.add(vinkki);
        dao.add(vinkki2);
        dao.add(vinkki3);
        
        Matcher haku = new HakuBuilder().tarkastettu().build();
        
        List<Vinkki> vinkit = dao.matches(haku);
        
        assertEquals(2, vinkit.size());
    }
}
