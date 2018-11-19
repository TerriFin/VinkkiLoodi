
import java.util.Scanner;
import vinkkiloodi.database.VinkkiSqliteDAO;
import vinkkiloodi.ui.KomentoriviUI;


/**
 *
 * @author samisaukkonen
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VinkkiSqliteDAO dao = new VinkkiSqliteDAO("db/testitietokanta.db");
        KomentoriviUI ui = new KomentoriviUI(scanner, dao);
        ui.start();
    }
    
}
