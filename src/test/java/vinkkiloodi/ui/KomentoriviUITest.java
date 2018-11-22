/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.ui;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import vinkkiloodi.database.InMemoryDAO;
import vinkkiloodi.database.VinkkiDAO;
import vinkkiloodi.io.StubIO;

/**
 *
 * @author samisaukkonen
 */
public class KomentoriviUITest {

    private VinkkiDAO dao;
    private List<String> input;

    private KomentoriviUI ui;

    @Before
    public void setUp() {
        dao = new InMemoryDAO();
        input = new ArrayList<>();
    }

    private void lisaaTestiKirja(String kirjoittaja, String otsikko, String ISBN) {
        input.add("1");
        input.add(kirjoittaja);
        input.add(otsikko);
        input.add(ISBN);
    }

    private void aloitaOhjelma() {
        input.add("x");
        ui = new KomentoriviUI(new StubIO(input), dao);
        ui.start();
    }

    @Test
    public void voiLisataKirjaVinkin() {
        lisaaTestiKirja("kirjoittaja", "kirja", "12345");

        int vinkkejaEnnen = dao.getAll().size();
        
        aloitaOhjelma();
        
        assertTrue(vinkkejaEnnen < dao.getAll().size());
    }
}
