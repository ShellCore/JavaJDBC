package datos;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author shell
 */
public class Conexion {
    
    private static final String SQL_DRIVER = "com.mysql.jdbc.Driver";
    private static final String SQL_URL = "jdbc:mysql://localhost/sga?useSSL=false";
    private static final String SQL_USER = "root";
    private static final String SQL_PASS = "bahamut";
    
    private static Driver driver = null;
    
    public static synchronized Connection getConnection() throws SQLException {
        if (driver == null) {
            try {
                Class jdbcDriverClass = Class.forName(SQL_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("Fallo en cargar el driver JDBC");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(SQL_URL, SQL_USER, SQL_PASS);
    }
    
    public static void close(ResultSet result) {
        try {
            if (result != null) {
                result.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
