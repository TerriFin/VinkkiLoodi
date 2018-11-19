/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.database;

import java.util.ArrayList;
import java.util.List;
import vinkkiloodi.domain.Kirjavinkki;

/**
 *
 * @author sami
 */
public class InMemoryDAO implements VinkkiDAO {
    
    public ArrayList<Kirjavinkki> kirjaDB;
    
    public InMemoryDAO() {
        this.kirjaDB = new ArrayList<>();
    }
    
    @Override
    public void add(Kirjavinkki kirja) {
        this.kirjaDB.add(kirja);
    }

    @Override
    public List<Kirjavinkki> getAll() {
        return this.kirjaDB;
    }

    @Override
    public Kirjavinkki getById(int id) {
        for (Kirjavinkki k : this.kirjaDB) {
            if (k.getId() == id) {
                return k;
            }
        }
        
        return null;
    }
    
}
