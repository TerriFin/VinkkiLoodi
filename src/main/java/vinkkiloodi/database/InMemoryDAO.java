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
    
    public ArrayList<Vinkki> vinkkiDB;
    
    public InMemoryDAO() {
        this.vinkkiDB = new ArrayList<>();
    }
    
    @Override
    public void add(Vinkki kirja) {
        this.vinkkiDB.add(kirja);
        kirja.setId(vinkkiDB.indexOf(kirja));
    }

    @Override
    public List<Vinkki> getAll() {
        return this.vinkkiDB;
    }

    @Override
    public Vinkki getById(int id) {
        for (Vinkki v : vinkkiDB) {
            if (v.getId() == id) {
                return v;
            }
        }
        
        return null;
    }
    
}
