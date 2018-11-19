
package vinkkiloodi.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import vinkkiloodi.database.VinkkiSqliteDAO;
import vinkkiloodi.domain.Vinkki;

/**
 *
 * @author tumppi
 */

public class KomentoriviUI {
    Scanner scanner;
    VinkkiSqliteDAO dao;

    public KomentoriviUI(Scanner scanner, VinkkiSqliteDAO dao) {
        this.scanner = scanner;
        this.dao = dao;
        dao.getConnection();
    }
    
    public void start() {
        System.out.println("Tervetuloa vinkkiloodiin!\n----------------------"
                + "Vinkkiloodi on lukuvinkkikirjasto, johon voit lisätä lukuvinkkejä.\n");
        paavalikko();
    }
    
    public void paavalikko() {
        System.out.println("\nMitä haluat tehdä?"
                + "\n1 - Lisää vinkki"
                + "\n2 - Listaa vinkit"
                + "\n3 - Merkitse vinkki luetuksi"
                + "\nX - Sammuta ohjelma");
        
        String komento = scanner.nextLine();
        
        if (komento.toLowerCase().equals("x")) {
            System.exit(0);
        } else if (komento.equals("1")) {
            lisaaVinkki();
        } else if (komento.equals("2")) {
            listaaKaikki();
        } else if (komento.equals("3")) {
            merkitseVinkkiLuetuksi();
        }
    }
    
    
    public void lisaaVinkki() {
        System.out.println("Lisää vinkki\n-----------\n");
        System.out.print("Nimi: ");
        String nimi = scanner.nextLine();
        System.out.print("Otsikko: ");
        String otsikko = scanner.nextLine();
        
        // TODO: vinkin lisäys tietokantaan.
        paavalikko();
    }
    
    public void listaaKaikki() {
        System.out.println("Kaikki vinkit\n------------\n");
        List<Vinkki> vinkit = new ArrayList<>(); 
        
        //TODO daosta saatu lista vinkit = dao.esimesim();
        
        for (int i = 0; i < vinkit.size(); i++) {
            System.out.println(vinkit.get(i));
        }
        paavalikko();
    }
    
    public void merkitseVinkkiLuetuksi() {
        System.out.println("Merkitse vinkki luetuksi\n--------------------\n");
        System.out.print("Syötä lukuvinkin otsikko: ");
        String hakuOtsikko = scanner.nextLine();
        
        // TODO haku tietokannasta daon avulla.
        boolean onkoOlemassa = true;
        
        if (onkoOlemassa) {
            System.out.println("Vinkki " + hakuOtsikko + " merkittiin luetuksi.");
        } else {
            System.out.println("Vinkki " + hakuOtsikko + " ei löytynyt järjestelmästä.");
        }
        paavalikko();
    }
    
    

}
