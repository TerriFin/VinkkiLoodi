package vinkkiloodi.database;

import java.util.ArrayList;
import java.util.List;
import vinkkiloodi.domain.Tyyppi;
import vinkkiloodi.domain.Vinkki;

/**
 *
 * @author sami
 */
public class InMemoryDAO implements VinkkiDAO {

    public ArrayList<Vinkki> vinkkiDB;

    public InMemoryDAO() {
        this.vinkkiDB = new ArrayList<>();
    }

    @Override
    public void add(Vinkki vinkki) {
        vinkkiDB.add(vinkki);
        vinkki.setId(vinkkiDB.indexOf(vinkki));
    }

    @Override
    public List<Vinkki> getAll() {
        return vinkkiDB;
    }

    @Override
    public Vinkki getById(int id) {
        for (Vinkki v : vinkkiDB) {
            if (v.getId() == id) {
                return v;
            }
        }

        return null;
    }

    public List<Vinkki> getKirjaByTekija(String tekija) {
        return getTypeofByTekija(Tyyppi.Kirja, tekija);
    }

    public List<Vinkki> getBlogiByTekija(String tekija) {
        return getTypeofByTekija(Tyyppi.Blog, tekija);
    }

    public List<Vinkki> getArtikkeliByTekija(String tekija) {
        return getTypeofByTekija(Tyyppi.Artikkeli, tekija);
    }

    private List<Vinkki> getTypeofByTekija(Tyyppi tyyppi, String tekija){
        List<Vinkki> vinkit = new ArrayList<>();

        for (Vinkki v : vinkkiDB) {
            if (v.getTyyppi() == tyyppi && v.getTekija().contains(tekija)) {
                vinkit.add(v);
            }
        }

        return vinkit;
    }
    
    @Override
    public List<Vinkki> getByTekija(String tekija) {
        List<Vinkki> vinkit = new ArrayList<>();

        vinkit.addAll(getKirjaByTekija(tekija));
        vinkit.addAll(getBlogiByTekija(tekija));
        vinkit.addAll(getArtikkeliByTekija(tekija));

        return vinkit;
    }

    public List<Vinkki> getKirjaByNimi(String nimi) {
        return getTypeofByNimi(Tyyppi.Kirja, nimi);
    }

    public List<Vinkki> getBlogiByNimi(String nimi) {
        return getTypeofByNimi(Tyyppi.Blog, nimi);
    }

    public List<Vinkki> getArtikkeliByNimi(String nimi) {
        return getTypeofByNimi(Tyyppi.Artikkeli, nimi);
    }

    private List<Vinkki> getTypeofByNimi(Tyyppi tyyppi, String nimi) {
        List<Vinkki> vinkit = new ArrayList<>();

        for (Vinkki v : vinkkiDB) {
            if (v.getTyyppi() == tyyppi && v.getNimi().contains(nimi)) {
                vinkit.add(v);
            }
        }

        return vinkit;
    }
    
    @Override
    public List<Vinkki> getByNimi(String nimi) {
        List<Vinkki> vinkit = new ArrayList<>();

        vinkit.addAll(getKirjaByNimi(nimi));
        vinkit.addAll(getBlogiByNimi(nimi));
        vinkit.addAll(getArtikkeliByNimi(nimi));

        return vinkit;
    }
    
    public boolean sisaltaaVinkin(List<Vinkki> vinkit, Vinkki vinkki) {
        for (Vinkki v : vinkit) {
            if (v.getId() == vinkki.getId()) {
                return true;
            }
        }
        
        return false;
    }
    
    public void lisaaIlmanDuplikaatteja(List<Vinkki> vinkit, List<Vinkki> lisattavat) {
        for (Vinkki v : lisattavat) {
            if (!sisaltaaVinkin(vinkit, v)) {
                vinkit.add(v);
            }
        }
    }
    
    @Override
    public List<Vinkki> megaHaku(String hakusana) {
        List<Vinkki> tulokset = new ArrayList<>();
        
        lisaaIlmanDuplikaatteja(tulokset, this.getByNimi(hakusana));
        lisaaIlmanDuplikaatteja(tulokset, this.getByTekija(hakusana));
        
        return tulokset;
    }
    
    @Override
    public void update(int id, Vinkki vinkki) {
        Vinkki v = getById(id);

        if (v == null) {
            return;
        }

        v.setTekija(vinkki.getTekija());
        v.setNimi(vinkki.getNimi());
        v.setTarkastettu(vinkki.getTarkastettu());
    }

    @Override
    public List<Vinkki> getKaikkiKirjat() {
        return getKaikkiOfType(Tyyppi.Kirja);
    }

    @Override
    public List<Vinkki> getKaikkiBlogit() {
        return getKaikkiOfType(Tyyppi.Blog);
    }

    @Override
    public List<Vinkki> getKaikkiArtikkelit() {
        return getKaikkiOfType(Tyyppi.Artikkeli);
    }
    
    private List<Vinkki> getKaikkiOfType(Tyyppi tyyppi){
        List<Vinkki> vinkit = new ArrayList<>();
        
        for (Vinkki v : this.vinkkiDB) {
            if (v.getTyyppi() == tyyppi) {
                vinkit.add(v);
            }
        }
        
        return vinkit;
    }

    @Override
    public List<Vinkki> getKaikkiTarkastamattomat() {
        List<Vinkki> vinkit = new ArrayList<>();
        
        for (Vinkki v : this.vinkkiDB) {
            if (v.getTarkastettu() == 0) {
                vinkit.add(v);
            }
        }
        
        return vinkit;
    }

    @Override
    public List<Vinkki> getKaikkitarkastetut() {
        List<Vinkki> vinkit = new ArrayList<>();
        
        for (Vinkki v : this.vinkkiDB) {
            if (v.getTarkastettu() != 0) {
                vinkit.add(v);
            }
        }
        
        return vinkit;
    }

}
