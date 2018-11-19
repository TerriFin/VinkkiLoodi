/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkiloodi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vinkkiloodi.domain.Kirjavinkki;
import vinkkiloodi.domain.Vinkki;

/**
 *
 * @author sami
 */
public class VinkkiSqliteDAO implements VinkkiDAO {
    private String db;
    
    public VinkkiSqliteDAO(String db) throws SQLException {
        this.db = db;
        
        createTables();
    }
    
    private Connection getConnection() throws SQLException {
        Connection conn = null;
        
        // Try to get a connection, return null on fail
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found!");
        }
        conn = DriverManager.getConnection("jdbc:sqlite:" + db);
        
        return conn;
    }
    
    public void createTables() throws SQLException {
        Connection conn = getConnection();
        
        // Create Kirjavinkki Table
        PreparedStatement statement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Kirjavinkki (id INTEGER PRIMARY KEY, title string, author string, is_read int)");
        statement.execute();
    }

    @Override
    public void add(Vinkki kirja) {
        try {
            Connection conn = getConnection();
            
            PreparedStatement insertion = conn.prepareStatement("INSERT INTO Kirjavinkki (title, author, is_read) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertion.setString(1, kirja.getKirjoittaja());
            insertion.setString(2, kirja.getOtsikko());
            insertion.setInt(3, kirja.getLuettu());
            
            insertion.executeUpdate();
            
            ResultSet id = insertion.getGeneratedKeys();
            
            id.next();
            
            kirja.setId(id.getInt(1));
            
            id.close();
        } catch (SQLException e) {
            System.out.println("Failed to insert into database: " + e.getMessage());
        }
    }

    @Override
    public List<Vinkki> getAll() {
        try {
            Connection conn = getConnection();
            
            PreparedStatement selection = conn.prepareStatement("SELECT * FROM Kirjavinkki");
            ResultSet tulokset = selection.executeQuery();
            
            ArrayList<Vinkki> vinkit = new ArrayList<>();
            
            while (tulokset.next()) {
                Kirjavinkki uusi = new Kirjavinkki(tulokset.getString("author"), tulokset.getString("title"), tulokset.getInt("is_read"), "");
                uusi.setId(tulokset.getInt("id"));
                vinkit.add(uusi);
            }
            
            tulokset.close();
            
            return vinkit;
        } catch (SQLException e) {
            System.out.println("Failed to select from database: " + e.getMessage());
        }
        
        return null;
    }

    @Override
    public Vinkki getById(int id) {
        Connection conn;
        try {
            conn = getConnection();
            PreparedStatement haku = conn.prepareStatement("SELECT * FROM Kirjavinkki WHERE id = ?");
            
            haku.setInt(1, id);
            ResultSet tulokset = haku.executeQuery();
            
            while(tulokset.next()) {
                Kirjavinkki vinkki = new Kirjavinkki(tulokset.getString("author"), tulokset.getString("title"), tulokset.getInt("is_read"), "");
                vinkki.setId(tulokset.getInt("id"));
                
                tulokset.close();
                return vinkki;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
