package vinkkiloodi.database;

import filter.Matcher;
import java.util.ArrayList;
import java.util.List;
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
    public List<Vinkki> matches(Matcher matcher) {
        List<Vinkki> vinkit = getAll();
        List<Vinkki> tulokset = new ArrayList<>();
        
        for (Vinkki v : vinkit) {
            if (matcher.matches(v)) {
                tulokset.add(v);
            }
        }
        
        return tulokset;
    }

}
