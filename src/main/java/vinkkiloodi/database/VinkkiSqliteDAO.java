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
import vinkkiloodi.domain.ArtikkeliVinkki;
import vinkkiloodi.domain.BlogiVinkki;
import vinkkiloodi.domain.KirjaVinkki;
import vinkkiloodi.domain.Vinkki;
import vinkkiloodi.domain.Tyyppi;

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

    public Connection getConnection() throws SQLException {
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
        PreparedStatement statement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Vinkki (id INTEGER PRIMARY KEY, type int)");
        statement.execute();
        statement.close();

        // Kirjavinkit
        statement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Kirjavinkki (id INTEGER, title string, author string, isbn string, checked_out int, FOREIGN KEY(id) REFERENCES Vinkki(id))");
        statement.execute();
        statement.close();

        // Blogipostaukset
        statement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Blogivinkki (id INTEGER, title string, author string, url string, checked_out int, FOREIGN KEY(id) REFERENCES Vinkki(id))");
        statement.execute();
        statement.close();

        // Blogipostaukset
        statement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Artikkelivinkki (id INTEGER, title string, author string, published_in string, checked_out int, FOREIGN KEY(id) REFERENCES Vinkki(id))");
        statement.execute();
        statement.close();

        conn.close();
    }

    public int tyyppiToInt(Tyyppi tyyppi) {
        switch (tyyppi) {
            case Kirja:
                return 1;
            case Artikkeli:
                return 2;
            case Blog:
                return 3;
            default:
                return 0;
        }
    }

    public Tyyppi intToTyyppi(int tyyppi) {
        switch (tyyppi) {
            case 1:
                return Tyyppi.Kirja;
            case 2:
                return Tyyppi.Artikkeli;
            case 3:
                return Tyyppi.Blog;
            default:
                return Tyyppi.None;
        }
    }

    @Override
    public void add(Vinkki vinkki) {
        try {
            Connection conn = getConnection();

            PreparedStatement insertion = conn.prepareStatement("INSERT INTO Vinkki (type) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            insertion.setInt(1, tyyppiToInt(vinkki.getTyyppi()));

            insertion.executeUpdate();

            ResultSet id = insertion.getGeneratedKeys();

            id.next();

            vinkki.setId(id.getInt(1));

            id.close();
            insertion.close();
            conn.close();

            switch (vinkki.getTyyppi()) {
                case None:
                    break;
                case Kirja:
                    storeKirja((KirjaVinkki) vinkki);
                    break;
                case Blog:
                    storeBlogi((BlogiVinkki) vinkki);
                    break;
                case Artikkeli:
                    storeArtikkeli((ArtikkeliVinkki) vinkki);
                    break;
            }
        } catch (SQLException e) {

        }
    }

    private void storeKirja(KirjaVinkki kirja) {
        try {
            Connection conn = getConnection();

            PreparedStatement insertion = conn.prepareStatement("INSERT INTO Kirjavinkki (id, title, author, isbn, checked_out) VALUES (?, ?, ?, ?, ?)");
            insertion.setInt(1, kirja.getId());
            insertion.setString(2, kirja.getNimi());
            insertion.setString(3, kirja.getTekija());
            insertion.setString(4, kirja.getISBN());
            insertion.setInt(5, kirja.getTarkastettu());

            insertion.executeUpdate();

            insertion.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void storeBlogi(BlogiVinkki blogi) {
        try {
            Connection conn = getConnection();

            PreparedStatement insertion = conn.prepareStatement("INSERT INTO Blogivinkki (id, title, author, url, checked_out) VALUES (?, ?, ?, ?, ?)");
            insertion.setInt(1, blogi.getId());
            insertion.setString(2, blogi.getNimi());
            insertion.setString(3, blogi.getTekija());
            insertion.setString(4, blogi.getUrl());
            insertion.setInt(5, blogi.getTarkastettu());

            insertion.executeUpdate();

            insertion.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void storeArtikkeli(ArtikkeliVinkki artikkeli) {
        try {
            Connection conn = getConnection();

            PreparedStatement insertion = conn.prepareStatement("INSERT INTO ArtikkeliVinkki (id, title, author, published_in, checked_out) VALUES (?, ?, ?, ?, ?)");
            insertion.setInt(1, artikkeli.getId());
            insertion.setString(2, artikkeli.getNimi());
            insertion.setString(3, artikkeli.getTekija());
            insertion.setString(4, artikkeli.getJulkaisija());
            insertion.setInt(5, artikkeli.getTarkastettu());

            insertion.executeUpdate();

            insertion.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private KirjaVinkki getKirja(int id, Connection conn) {
        try {
            PreparedStatement haku = conn.prepareStatement("SELECT * FROM Kirjavinkki WHERE id = ?");
            haku.setInt(1, id);
            ResultSet tulokset = haku.executeQuery();

            tulokset.next();

            KirjaVinkki vinkki = new KirjaVinkki(tulokset.getString("author"), tulokset.getString("title"), tulokset.getInt("checked_out"), "");
            vinkki.setId(id);

            tulokset.close();
            haku.close();

            return vinkki;
        } catch (SQLException e) {

        }

        return null;
    }

    private BlogiVinkki getBlogi(int id, Connection conn) {
        try {
            PreparedStatement haku = conn.prepareStatement("SELECT * FROM Blogivinkki WHERE id = ?");
            haku.setInt(1, id);
            ResultSet tulokset = haku.executeQuery();

            tulokset.next();
            BlogiVinkki vinkki = new BlogiVinkki(tulokset.getString("author"), tulokset.getString("title"), tulokset.getString("url"), tulokset.getInt("checked_out"));
            vinkki.setId(id);

            tulokset.close();
            haku.close();

            return vinkki;
        } catch (SQLException e) {

        }

        return null;
    }

    private ArtikkeliVinkki getArtikkeli(int id, Connection conn) {
        try {
            PreparedStatement haku = conn.prepareStatement("SELECT * FROM Artikkelivinkki WHERE id = ?");
            haku.setInt(1, id);
            ResultSet tulokset = haku.executeQuery();

            tulokset.next();
            ArtikkeliVinkki vinkki = new ArtikkeliVinkki(tulokset.getString("author"), tulokset.getString("title"), tulokset.getString("published_in"), tulokset.getInt("checked_out"));
            vinkki.setId(id);

            tulokset.close();
            haku.close();

            return vinkki;
        } catch (SQLException e) {

        }

        return null;
    }

    @Override
    public List<Vinkki> getAll() {
        try {
            Connection conn = getConnection();

            PreparedStatement selection = conn.prepareStatement("SELECT * FROM Vinkki");
            ResultSet tulokset = selection.executeQuery();

            ArrayList<Vinkki> vinkit = new ArrayList<>();

            while (tulokset.next()) {
                int id = tulokset.getInt("id");
                Tyyppi tyyppi = intToTyyppi(tulokset.getInt("type"));
                Vinkki vinkki = null;

                switch (tyyppi) {
                    case Kirja:
                        vinkki = getKirja(id, conn);
                        break;
                    case Blog:
                        vinkki = getBlogi(id, conn);
                        break;
                    case Artikkeli:
                        vinkki = getArtikkeli(id, conn);
                        break;
                    default:
                        break;
                }

                if (vinkki != null) {
                    vinkit.add(vinkki);
                }
            }

            tulokset.close();
            selection.close();
            conn.close();

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
            PreparedStatement haku = conn.prepareStatement("SELECT * FROM Vinkki WHERE id = ?");

            haku.setInt(1, id);
            ResultSet tulokset = haku.executeQuery();

            Tyyppi tyyppi = Tyyppi.None;
            Vinkki vinkki = null;

            if (tulokset.next()) {
                tyyppi = intToTyyppi(tulokset.getInt("type"));

            }

            tulokset.close();
            haku.close();

            switch (tyyppi) {
                case Kirja:
                    vinkki = getKirja(id, conn);
                    break;
                case Blog:
                    vinkki = getBlogi(id, conn);
                    break;
                case Artikkeli:
                    vinkki = getArtikkeli(id, conn);
                    break;
                default:
                    break;
            }

            conn.close();
            return vinkki;
        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void updateKirja(int id, KirjaVinkki kirja) {
        Connection conn;
        try {
            conn = getConnection();
            PreparedStatement päivitys = conn.prepareStatement("UPDATE Kirjavinkki SET title = ?, author = ?, isbn = ?, checked_out = ? WHERE id = ?");
            päivitys.setString(1, kirja.getNimi());
            päivitys.setString(2, kirja.getTekija());
            päivitys.setString(3, kirja.getISBN());
            päivitys.setInt(4, kirja.getTarkastettu());
            päivitys.setInt(5, id);

            päivitys.executeUpdate();

            päivitys.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateBlogi(int id, BlogiVinkki blogi) {
        Connection conn;
        try {
            conn = getConnection();
            PreparedStatement päivitys = conn.prepareStatement("UPDATE Blogivinkki SET title = ?, author = ?, url = ?, checked_out = ? WHERE id = ?");
            päivitys.setString(1, blogi.getNimi());
            päivitys.setString(2, blogi.getTekija());
            päivitys.setString(3, blogi.getUrl());
            päivitys.setInt(4, blogi.getTarkastettu());
            päivitys.setInt(5, id);

            päivitys.executeUpdate();

            päivitys.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateArtikkeli(int id, ArtikkeliVinkki artikkeli) {
        Connection conn;
        try {
            conn = getConnection();
            PreparedStatement päivitys = conn.prepareStatement("UPDATE Artikkelivinkki SET title = ?, author = ?, published_in = ?, checked_out = ? WHERE id = ?");
            päivitys.setString(1, artikkeli.getNimi());
            päivitys.setString(2, artikkeli.getTekija());
            päivitys.setString(3, artikkeli.getJulkaisija());
            päivitys.setInt(4, artikkeli.getTarkastettu());
            päivitys.setInt(5, id);

            päivitys.executeUpdate();

            päivitys.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(int id, Vinkki vinkki) {
        switch (vinkki.getTyyppi()) {
            case Kirja:
                updateKirja(id, (KirjaVinkki) vinkki);
                break;
            case Blog:
                updateBlogi(id, (BlogiVinkki) vinkki);
                break;
            case Artikkeli:
                updateArtikkeli(id, (ArtikkeliVinkki) vinkki);
                break;
            default:
                break;
        }
    }

    public List<Vinkki> getKirjaByTekija(String tekija) {
        List<Vinkki> vinkit = new ArrayList<>();
        Connection conn;

        try {
            conn = getConnection();

            String hakuString = "SELECT * FROM Kirjavinkki "
                    + "WHERE author LIKE ? OR author LIKE ? OR author LIKE ? OR author LIKE ?";

            PreparedStatement haku = conn.prepareStatement(hakuString);

            haku.setString(1, tekija);
            haku.setString(2, tekija + "%");
            haku.setString(3, "%" + tekija);
            haku.setString(4, "%" + tekija + "%");

            ResultSet tulokset = haku.executeQuery();

            while (tulokset.next()) {
                Vinkki vinkki;
                vinkki = new KirjaVinkki(tulokset.getString("author"), tulokset.getString("title"),
                        tulokset.getInt("checked_out"), tulokset.getString("isbn"));
                vinkki.setId(tulokset.getInt("id"));
                vinkit.add(vinkki);
            }
            haku.close();
            tulokset.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vinkit;
    }

    public List<Vinkki> getBlogiByTekija(String tekija) {
        List<Vinkki> vinkit = new ArrayList<>();
        Connection conn;
        try {
            conn = getConnection();

            String hakuString = "SELECT * FROM Blogivinkki "
                    + "WHERE author LIKE ? OR author LIKE ? OR author LIKE ? OR author LIKE ?";

            PreparedStatement haku = conn.prepareStatement(hakuString);

            haku.setString(1, tekija);
            haku.setString(2, tekija + "%");
            haku.setString(3, "%" + tekija);
            haku.setString(4, "%" + tekija + "%");

            ResultSet tulokset = haku.executeQuery();

            while (tulokset.next()) {
                Vinkki vinkki;
                vinkki = new BlogiVinkki(tulokset.getString("author"), tulokset.getString("title"),
                        tulokset.getString("url"), tulokset.getInt("checked_out"));
                vinkki.setId(tulokset.getInt("id"));
                vinkit.add(vinkki);
            }
            haku.close();
            tulokset.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vinkit;
    }

    public List<Vinkki> getArtikkeliByTekija(String tekija) {
        List<Vinkki> vinkit = new ArrayList<>();
        Connection conn;

        try {
            conn = getConnection();

            String hakuString = "SELECT * FROM Artikkelivinkki "
                    + "WHERE author LIKE ? OR author LIKE ? OR author LIKE ? OR author LIKE ?";

            PreparedStatement haku = conn.prepareStatement(hakuString);

            haku.setString(1, tekija);
            haku.setString(2, tekija + "%");
            haku.setString(3, "%" + tekija);
            haku.setString(4, "%" + tekija + "%");

            ResultSet tulokset = haku.executeQuery();

            while (tulokset.next()) {
                Vinkki vinkki;
                vinkki = new ArtikkeliVinkki(tulokset.getString("author"), tulokset.getString("title"),
                        tulokset.getString("published_in"), tulokset.getInt("checked_out"));
                vinkki.setId(tulokset.getInt("id"));
                vinkit.add(vinkki);
            }

            haku.close();
            tulokset.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vinkit;

    }

    @Override
    public List<Vinkki> getByTekija(String tekija) {
        List<Vinkki> vinkit = new ArrayList<>();
        vinkit.addAll(getKirjaByTekija(tekija));
        vinkit.addAll(getBlogiByTekija(tekija));
        vinkit.addAll(getArtikkeliByTekija(tekija));

        return vinkit;
    }

    public List<Vinkki> getKirjaByNimi(String nimi) {
        List<Vinkki> vinkit = new ArrayList<>();
        Connection conn;

        try {
            conn = getConnection();

            String hakuString = "SELECT * FROM Kirjavinkki "
                    + "WHERE title LIKE ? OR title LIKE ? OR title LIKE ? OR title LIKE ?";

            PreparedStatement haku = conn.prepareStatement(hakuString);

            haku.setString(1, nimi);
            haku.setString(2, nimi + "%");
            haku.setString(3, "%" + nimi);
            haku.setString(4, "%" + nimi + "%");

            ResultSet tulokset = haku.executeQuery();

            while (tulokset.next()) {
                Vinkki vinkki;
                vinkki = new KirjaVinkki(tulokset.getString("author"), tulokset.getString("title"),
                        tulokset.getInt("checked_out"), tulokset.getString("isbn"));
                vinkki.setId(tulokset.getInt("id"));
                vinkit.add(vinkki);
            }
            haku.close();
            tulokset.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vinkit;
    }

    public List<Vinkki> getBlogiByNimi(String nimi) {
        List<Vinkki> vinkit = new ArrayList<>();
        Connection conn;
        try {
            conn = getConnection();

            String hakuString = "SELECT * FROM Blogivinkki "
                    + "WHERE title LIKE ? OR title LIKE ? OR title LIKE ? OR title LIKE ?";

            PreparedStatement haku = conn.prepareStatement(hakuString);

            haku.setString(1, nimi);
            haku.setString(2, nimi + "%");
            haku.setString(3, "%" + nimi);
            haku.setString(4, "%" + nimi + "%");

            ResultSet tulokset = haku.executeQuery();

            while (tulokset.next()) {
                Vinkki vinkki;
                vinkki = new BlogiVinkki(tulokset.getString("author"), tulokset.getString("title"),
                        tulokset.getString("url"), tulokset.getInt("checked_out"));
                vinkki.setId(tulokset.getInt("id"));
                vinkit.add(vinkki);
            }
            haku.close();
            tulokset.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vinkit;
    }

    public List<Vinkki> getArtikkeliByNimi(String nimi) {
        List<Vinkki> vinkit = new ArrayList<>();
        Connection conn;

        try {
            conn = getConnection();

            String hakuString = "SELECT * FROM Artikkelivinkki "
                    + "WHERE title LIKE ? OR title LIKE ? OR title LIKE ? OR title LIKE ?";

            PreparedStatement haku = conn.prepareStatement(hakuString);

            haku.setString(1, nimi);
            haku.setString(2, nimi + "%");
            haku.setString(3, "%" + nimi);
            haku.setString(4, "%" + nimi + "%");
            ResultSet tulokset = haku.executeQuery();

            while (tulokset.next()) {
                Vinkki vinkki;
                vinkki = new ArtikkeliVinkki(tulokset.getString("author"), tulokset.getString("title"),
                        tulokset.getString("published_in"), tulokset.getInt("checked_out"));
                vinkki.setId(tulokset.getInt("id"));
                vinkit.add(vinkki);
            }

            haku.close();
            tulokset.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vinkit;

    }

    @Override
    public List<Vinkki> getByNimi(String nimi) {
        List<Vinkki> vinkit = new ArrayList<>();
        vinkit.addAll(getKirjaByNimi(nimi));
        vinkit.addAll(getBlogiByNimi(nimi));
        vinkit.addAll(getArtikkeliByNimi(nimi));

        return vinkit;
    }

    @Override
    public List<Vinkki> getKaikkiKirjat() {
        List<Vinkki> vinkit = new ArrayList<>();
        Connection conn;

        try {
            conn = getConnection();

            PreparedStatement haku = conn.prepareStatement("SELECT * FROM Kirjavinkki");

            ResultSet tulokset = haku.executeQuery();

            while (tulokset.next()) {
                KirjaVinkki vinkki = new KirjaVinkki(tulokset.getString("author"), tulokset.getString("title"),
                        tulokset.getInt("checked_out"), tulokset.getString("isbn"));
                vinkki.setId(tulokset.getInt("id"));
                vinkit.add(vinkki);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vinkit;
    }

    @Override
    public List<Vinkki> getKaikkiBlogit() {
        List<Vinkki> vinkit = new ArrayList<>();
        Connection conn;

        try {
            conn = getConnection();

            PreparedStatement haku = conn.prepareStatement("SELECT * FROM Blogivinkki");

            ResultSet tulokset = haku.executeQuery();

            while (tulokset.next()) {
                BlogiVinkki vinkki = new BlogiVinkki(tulokset.getString("author"), tulokset.getString("title"),
                        tulokset.getString("url"), tulokset.getInt("checked_out"));
                vinkki.setId(tulokset.getInt("id"));
                vinkit.add(vinkki);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vinkit;
    }

    @Override
    public List<Vinkki> getKaikkiArtikkelit() {
        List<Vinkki> vinkit = new ArrayList<>();
        Connection conn;

        try {
            conn = getConnection();

            PreparedStatement haku = conn.prepareStatement("SELECT * FROM Artikkelivinkki");

            ResultSet tulokset = haku.executeQuery();

            while (tulokset.next()) {
                ArtikkeliVinkki vinkki = new ArtikkeliVinkki(tulokset.getString("author"), tulokset.getString("title"),
                        tulokset.getString("published_in"), tulokset.getInt("checked_out"));
                vinkki.setId(tulokset.getInt("id"));
                vinkit.add(vinkki);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VinkkiSqliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vinkit;
    }

    @Override
    public List<Vinkki> getKaikkiTarkastamattomat() {
        List<Vinkki> vinkit = getAll();
        List<Vinkki> tarkastamattomat = new ArrayList<>();

        for (Vinkki v : vinkit) {
            if (v.getTarkastettu() == 0) {
                tarkastamattomat.add(v);
            }
        }

        return tarkastamattomat;
    }

    @Override
    public List<Vinkki> getKaikkitarkastetut() {
        List<Vinkki> vinkit = getAll();
        List<Vinkki> tarkastamattomat = new ArrayList<>();

        for (Vinkki v : vinkit) {
            if (v.getTarkastettu() != 0) {
                tarkastamattomat.add(v);
            }
        }

        return tarkastamattomat;
    }

    public boolean sisaltaaVinkin(List<Vinkki> vinkit, Vinkki vinkki) {
        for (Vinkki v : vinkit) {
            if (v.getId() == vinkki.getId()) {
                return true;
            }
        }

        return false;
    }

    public void lisaaIlmanDuplikaatteja(List<Vinkki> vinkit, List<Vinkki> lisattavat) {
        for (Vinkki v : lisattavat) {
            if (!sisaltaaVinkin(vinkit, v)) {
                vinkit.add(v);
            }
        }
    }

    public List<Vinkki> megaHaku(String hakusana) {
        List<Vinkki> tulokset = new ArrayList<>();

        lisaaIlmanDuplikaatteja(tulokset, this.getByNimi(hakusana));
        lisaaIlmanDuplikaatteja(tulokset, this.getByTekija(hakusana));

        return tulokset;
    }
}
