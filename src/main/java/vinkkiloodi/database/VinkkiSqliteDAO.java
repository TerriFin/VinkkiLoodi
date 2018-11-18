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

/**
 *
 * @author sami
 */
public class VinkkiSqliteDAO {
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
            PreparedStatement statement = conn.prepareStatement("CREATE Table Kirjavinkki (title string, author string, is_read int)");
            statement.execute();
            
        } catch (SQLException e) {
            System.out.println("Failed to create tables: " + e.getMessage());
        }
    }
}
