/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import vinkkiloodi.domain.Kirjavinkki;

/**
 *
 * @author sami
 */
public class VinkkiSqliteDAO implements VinkkiDAO {
    private String db;
    
    public VinkkiSqliteDAO(String db) {
        this.db = db;
        
        createTables();
    }
    
    public Connection getConnection() {
        Connection conn = null;
        
        // Try to get a connection, return null on fail
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + db);
            
        } catch (SQLException e) {
            System.out.println("Couldn't establish connection: " + e.getMessage());
        }
        
        return conn;
    }
    
    public void createTables() {
        try {
            Connection conn = getConnection();
            
            // Create Kirjavinkki Table
            PreparedStatement statement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Kirjavinkki (id int, title string, author string, is_read int)");
            statement.execute();
            
        } catch (SQLException e) {
            System.out.println("Failed to create tables: " + e.getMessage());
        }
    }

    @Override
    public void add(Kirjavinkki kirja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Kirjavinkki> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Kirjavinkki getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
