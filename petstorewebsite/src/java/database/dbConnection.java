/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jeff
 */
public class dbConnection {

    private static Connection conn;
    static String  connectionURL = "jdbc:mysql://s215.eatj.com:3307/infsci2710";
    //static String  connectionURL = "jdbc:mysql://localhost:3306/petstorev3";
    static int i=0;

    public static Connection getConnection() throws SQLException {

        if (conn == null||conn.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = (Connection) DriverManager.getConnection(connectionURL, "infsci2710", "2710");
                return conn;
            } catch (InstantiationException ex) {
                Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Close connection "+ (--i));
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
