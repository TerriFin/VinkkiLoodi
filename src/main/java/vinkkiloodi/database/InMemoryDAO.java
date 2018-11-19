/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.database;

import java.util.ArrayList;
import java.util.List;
import vinkkiloodi.domain.Vinkki;

/**
 *
 * @author sami
 */
public class InMemoryDAO implements VinkkiDAO {
    
    public ArrayList<Vinkki> kirjaDB;
    
    public InMemoryDAO() {
        this.kirjaDB = new ArrayList<>();
    }
    
    @Override
    public void add(Vinkki kirja) {
        this.kirjaDB.add(kirja);
    }

    @Override
    public List<Vinkki> getAll() {
        return this.kirjaDB;
    }

    @Override
    public Vinkki getById(int id) {
        for (Vinkki k : this.kirjaDB) {
            if (k.getId() == id) {
                return k;
            }
        }
        
        return null;
    }
    
}
