package vinkkiloodi.io;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Niko
 */
public class KomentoriviIOTest {

    @Test
    public void osaaLukeaJaPalauttaaStringin() {
        String input = "Test";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);
        KomentoriviIO io = new KomentoriviIO(scanner);
        assertEquals(io.nextLine(), input);
    }

    @Test
    public void palauttaaSyotteestaIntin() {
        String input = "6";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);
        KomentoriviIO io = new KomentoriviIO(scanner);
        assertEquals(io.nextInt(), 6);
    }

}
