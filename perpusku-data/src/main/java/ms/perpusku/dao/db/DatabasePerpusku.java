package ms.perpusku.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MS
 */
public class DatabasePerpusku {
    private static Connection connection;
    
    private static String url = "";
    private static String user = "";
    private static String password = "";

    private DatabasePerpusku() {    
    }
    
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePerpusku.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return connection;
    }
    
}
