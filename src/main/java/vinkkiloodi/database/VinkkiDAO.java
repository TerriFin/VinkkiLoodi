/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.database;

import java.util.List;
import vinkkiloodi.domain.Vinkki;

/**
 *
 * @author sami
 */
public interface VinkkiDAO {
    public void add(Vinkki kirja);
    public List<Vinkki> getAll();
    public Vinkki getById(int id);
    public void update(int id, Vinkki vinkki);
}
