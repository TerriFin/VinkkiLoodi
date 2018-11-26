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

    @Override
    public List<Vinkki> getKirjaByTekija(String tekija) {
        List<Vinkki> vinkit = new ArrayList<>();

        for (Vinkki v : vinkkiDB) {
            if (v.getTyyppi() == Tyyppi.Kirja && v.getTekija().equals(tekija)) {
                vinkit.add(v);
            }
        }

        return vinkit;
    }

    @Override
    public List<Vinkki> getBlogiByTekija(String tekija) {
        List<Vinkki> vinkit = new ArrayList<>();

        for (Vinkki v : vinkkiDB) {
            if (v.getTyyppi() == Tyyppi.Blog && v.getTekija().equals(tekija)) {
                vinkit.add(v);
            }
        }

        return vinkit;
    }

    @Override
    public List<Vinkki> getArtikkeliByTekija(String tekija) {
        List<Vinkki> vinkit = new ArrayList<>();

        for (Vinkki v : vinkkiDB) {
            if (v.getTyyppi() == Tyyppi.Artikkeli && v.getTekija().equals(tekija)) {
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

}
