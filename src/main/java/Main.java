
import java.util.Scanner;
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
        Scanner scanner = new Scanner(System.in);
        IO io = new KomentoriviIO(scanner);
        InMemoryDAO dao = new InMemoryDAO();
        KomentoriviUI ui = new KomentoriviUI(io, dao);
        ui.start();
    }
    
}
