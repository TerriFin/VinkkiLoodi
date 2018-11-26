
import java.sql.SQLException;
import java.util.Scanner;
import vinkkiloodi.database.InMemoryDAO;
import vinkkiloodi.database.VinkkiSqliteDAO;
import vinkkiloodi.io.IO;
import vinkkiloodi.io.KomentoriviIO;
import vinkkiloodi.ui.KomentoriviUI;


/**
 *
 * @author samisaukkonen
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        
        Scanner scanner = new Scanner(System.in);
        IO io = new KomentoriviIO(scanner);
        
        // Testausta varten käytetään mieluummin InMemoryDAOa
        //InMemoryDAO dao = new InMemoryDAO();
        
        VinkkiSqliteDAO dao = new VinkkiSqliteDAO("tietokanta.db");
        
        KomentoriviUI ui = new KomentoriviUI(io, dao);
        ui.start();
    }
    
}
