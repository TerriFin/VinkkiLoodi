package vinkkiloodi.database;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import vinkkiloodi.domain.ArtikkeliVinkki;
import vinkkiloodi.domain.BlogiVinkki;
import vinkkiloodi.domain.KirjaVinkki;
import vinkkiloodi.domain.Tyyppi;
import vinkkiloodi.domain.Vinkki;

public class InMemoryDAOTest {

    private InMemoryDAO dao;

    @Before
    public void setUp() {
        dao = new InMemoryDAO();
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

        KirjaVinkki vinkki = new KirjaVinkki("Testi3", "Testi3");
        KirjaVinkki vinkki2 = new KirjaVinkki("Testi4", "Testi4");

        dao.add(vinkki);
        dao.add(vinkki2);

        Vinkki tulos = dao.getById(vinkki.getId());

        assertEquals(tulos, vinkki);
    }

    @Test
    public void olematonIdPalauttaaNull() {

        KirjaVinkki vinkki = new KirjaVinkki("Testi5", "Testi5");

        dao.add(vinkki);

        Vinkki tulos = dao.getById(Integer.MAX_VALUE);

        assertEquals(tulos, null);
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

        List<Vinkki> haku = dao.getKirjaByTekija("Uniikkitekija");

        assertEquals(1, haku.size());
    }

    @Test
    public void BlogiLoytyyTekijalla() {

        Vinkki vinkki = new BlogiVinkki("Uniikkitekija", "testi", "hienptestnurl.net", 0);
        dao.add(vinkki);

        List<Vinkki> haku = dao.getBlogiByTekija("Uniikkitekija");

        assertEquals(1, haku.size());
    }

    @Test
    public void ArtikkeliLoytyyTekijalla() {

        Vinkki vinkki = new ArtikkeliVinkki("Uniikkitekija", "testi", "julkaisija", 0);
        dao.add(vinkki);

        List<Vinkki> haku = dao.getArtikkeliByTekija("Uniikkitekija");

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

        List<Vinkki> haku = dao.getByTekija("Uniikkitekija");

        assertEquals(3, haku.size());
    }

    @Test
    public void kirjaLoytyyNimella() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkiinimi", 0, "12345678");
        dao.add(vinkki);

        List<Vinkki> haku = dao.getKirjaByNimi("Uniikkiinimi");

        assertEquals(1, haku.size());
    }

    @Test
    public void BlogiLoytyyNimella() {

        Vinkki vinkki = new BlogiVinkki("testi", "Uniikkiinimi", "hienptestnurl.net", 0);
        dao.add(vinkki);

        List<Vinkki> haku = dao.getBlogiByNimi("Uniikkiinimi");

        assertEquals(1, haku.size());
    }

    @Test
    public void ArtikkeliLoytyyNimella() {

        Vinkki vinkki = new ArtikkeliVinkki("testi", "Uniikkiinimi", "julkaisija", 0);
        dao.add(vinkki);

        List<Vinkki> haku = dao.getArtikkeliByNimi("Uniikkiinimi");

        assertEquals(1, haku.size());
    }

    @Test
    public void VinkitLoytyvatNimella() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkiinimi", 0, "12345678");
        Vinkki vinkki2 = new BlogiVinkki("testi", "Uniikkiinimi", "www.url.ei", 0);
        Vinkki vinkki3 = new ArtikkeliVinkki("testi", "Uniikkiinimi", "julkaisija", 0);

        dao.add(vinkki);
        dao.add(vinkki2);
        dao.add(vinkki3);

        List<Vinkki> haku = dao.getByNimi("Uniikkiinimi");

        assertEquals(3, haku.size());
    }
    
    @Test
    public void VinkitLoytyvatMegaHaulla() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 0, "12345678");
        Vinkki vinkki2 = new BlogiVinkki("testi", "Uniikkinimi", "www.url.ei", 0);
        Vinkki vinkki3 = new ArtikkeliVinkki("testi", "Uniikkinimi", "julkaisija", 0);

        dao.add(vinkki);
        dao.add(vinkki2);
        dao.add(vinkki3);

        List<Vinkki> haku = dao.megaHaku("Uniikkinimi");

        assertEquals(3, haku.size());
    }
    
    @Test
    public void VinkitLoytyvatMegaHaullaPrefix() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 0, "12345678");
        Vinkki vinkki2 = new BlogiVinkki("testi", "Uniikkinimi", "www.url.ei", 0);
        Vinkki vinkki3 = new ArtikkeliVinkki("testi", "Uniikkinimi", "julkaisija", 0);

        dao.add(vinkki);
        dao.add(vinkki2);
        dao.add(vinkki3);

        List<Vinkki> haku = dao.megaHaku("Uniikki");

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
        
        List<Vinkki> haku = dao.getKaikkiKirjat();
        
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
        
        List<Vinkki> haku = dao.getKaikkiBlogit();
        
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
        
        List<Vinkki> haku = dao.getKaikkiArtikkelit();
        
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
        
        List<Vinkki> haku = dao.getKaikkiTarkastamattomat();
        
        assert(haku.size() == 1);
    }
    
    @Test
    public void tarkastettuVinkkiLoytyy() {
        Vinkki vinkki = new KirjaVinkki("testi", "Uniikkinimi", 1, "12345678");
        
        Vinkki vinkkiBlogi = new BlogiVinkki("testi", "Uniikkinimi", "www.url.ei", 0);
        Vinkki vinkkiBlogi2 = new BlogiVinkki("testi2", "Uniikkinimi2", "www.url.joo", 0);
        
        dao.add(vinkki);
        dao.add(vinkkiBlogi);
        
        List<Vinkki> haku = dao.getKaikkitarkastetut();
        
        assert(haku.size() == 1);
    }
}
