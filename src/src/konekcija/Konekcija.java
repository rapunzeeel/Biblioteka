package konekcija;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Konekcija {
    private static Connection instance = null;

    public static Connection getConnection() throws SQLException {
        if (instance == null || instance.isClosed()) {
            try {
                // Register JDBC driver
                Class.forName(KonekcioniParametri.DRIVER).newInstance();

                // Open a connection using DriverManager class
                instance = DriverManager.getConnection(KonekcioniParametri.LOCAL_CONNECTION_STRING,
                        KonekcioniParametri.USERNAME, KonekcioniParametri.PASSWORD);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return instance;
    }
}