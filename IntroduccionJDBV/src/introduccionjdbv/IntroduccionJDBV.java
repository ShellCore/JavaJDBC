package introduccionjdbv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IntroduccionJDBV {

    public static void main(String[] args) {
        
        // Cadena de conexión a Mysql. El parámetro useSSL es opcional
        String url = "jdbc:mysql://localhost:3306/sga?useSSL=false";
        // Cargando el driver de MySQL
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Creamos el objeto conexión
            Connection conection = (Connection) DriverManager.getConnection(url, "root", "bahamut");
            // Creamos un objeto Statement
            Statement statement = conection.createStatement();
            // Creamos el query
            String sql = "SELECT id, nombre, apellido FROM persona";
            
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.print("Id: " + result.getInt(1));
                System.out.print(", Nombre: " + result.getString(2));
                System.out.println(", Apellido: " + result.getString(3));
            }
            result.close();
            statement.close();
            conection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
