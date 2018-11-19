
import vinkkiloodi.database.InMemoryDAO;
import vinkkiloodi.io.IO;
import vinkkiloodi.io.KomentoriviIO;
import vinkkiloodi.ui.KomentoriviUI;


/**
 *
 * @author samisaukkonen
 */
public class Main {

    public static void main(String[] args) {
        IO io = new KomentoriviIO();
        InMemoryDAO dao = new InMemoryDAO();
        KomentoriviUI ui = new KomentoriviUI(io, dao);
        ui.start();
    }
    
}
