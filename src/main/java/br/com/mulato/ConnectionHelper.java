package br.com.mulato;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Christian Mulato
 */
public class ConnectionHelper
{
    private static String drv = "";
    private static String url = "";
    private static String usr = "";
    private static String pwd = "";
    private static ConnectionHelper instance;

    private ConnectionHelper()
    {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("config");
            drv = bundle.getString("database.jdbc.driver");
            url = bundle.getString("database.jdbc.url");
            usr = bundle.getString("database.jdbc.usr");
            pwd = bundle.getString("database.jdbc.pwd");
            Class.forName(drv);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (instance == null) {
            instance = new ConnectionHelper();
        }
        try {
            return DriverManager.getConnection(url, usr, pwd);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void close(Connection connection)
    {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}