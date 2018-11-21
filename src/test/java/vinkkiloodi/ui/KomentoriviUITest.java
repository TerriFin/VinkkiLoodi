package vinkkiloodi.ui;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import vinkkiloodi.database.InMemoryDAO;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.io.StubIO;

/**
 *
 * @author samisaukkonen
 */
public class KomentoriviUITest {
    
    private VinkkiDAO dao;
    private List<String> komennot;

    @Before
    public void setUp() {
        dao = new InMemoryDAO();
        komennot = new ArrayList<>();
    }
    
    
}
