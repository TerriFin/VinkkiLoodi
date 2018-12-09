package vinkkiloodi.database;

import filter.Matcher;
import java.util.List;
import vinkkiloodi.domain.Vinkki;

/**
 *
 * @author sami
 */
public interface VinkkiDAO {

    public void add(Vinkki kirja);

    public List<Vinkki> getAll();

    public Vinkki getById(int id);

    public void update(int id, Vinkki vinkki);
    
    public List<Vinkki> getKaikkiKirjat();
    
    public List<Vinkki> getKaikkiBlogit();
    
    public List<Vinkki> getKaikkiArtikkelit();

    public List<Vinkki> getByTekija(String tekija);

    public List<Vinkki> getByNimi(String tekija);
    
    public List<Vinkki> getKaikkiTarkastamattomat();
    
    public List<Vinkki> getKaikkitarkastetut();
    
    public List<Vinkki> megaHaku(String hakusana);
    
    public List<Vinkki> matches(Matcher matcher);
}
