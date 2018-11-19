
import java.util.Scanner;
import vinkkiloodi.database.InMemoryDAO;
import vinkkiloodi.ui.KomentoriviUI;


/**
 *
 * @author samisaukkonen
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InMemoryDAO dao = new InMemoryDAO();
        KomentoriviUI ui = new KomentoriviUI(scanner, dao);
        ui.start();
    }
    
}
