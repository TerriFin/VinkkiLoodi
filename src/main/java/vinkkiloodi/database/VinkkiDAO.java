/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.database;

import java.util.List;
import vinkkiloodi.domain.Kirjavinkki;

/**
 *
 * @author sami
 */
public interface VinkkiDAO {
    public void add(Kirjavinkki kirja);
    public List<Kirjavinkki> getAll();
    public Kirjavinkki getById(int id);
}
