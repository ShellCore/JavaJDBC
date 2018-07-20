package datos;

import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosJDBC {
    
    private static final String SQL_INSERT = "INSERT INTO usuario (usuario, password) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET usuario = ?, password = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id = ?";
    private static final String SQL_SELECT = "SELECT id, usuario, password FROM usuario ORDER BY id";
    
    public int insert(String usuario, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        
        int rows = 0;
        try {
            connection = Conexion.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            int index = 1;
            statement.setString(index++, usuario);
            statement.setString(index++, password);
            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = statement.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(statement);
            Conexion.close(connection);
        }
        return rows;
    }
    
    public int update(int id, String usuario, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        
        int rows = 0;
        try {
            connection = Conexion.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            int index = 1;
            statement.setString(index++, usuario);
            statement.setString(index++, password);
            statement.setInt(index++, id);
            System.out.println("Ejecutando query: " + SQL_UPDATE);
            rows = statement.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(statement);
            Conexion.close(connection);
        }
        return rows;
    }
    
    public int delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        
        int rows = 0;
        try {
            connection = Conexion.getConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            int index = 1;
            statement.setInt(index++, id);
            System.out.println("Ejecutando query: " + SQL_DELETE);
            rows = statement.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(statement);
            Conexion.close(connection);
        }
        return rows;
    }
    
    public List<Usuario> select() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        
        List<Usuario> list = new ArrayList<>();
        
        try {
            connection = Conexion.getConnection();
            statement = connection.prepareStatement(SQL_SELECT);
            System.out.println("Ejecutando query: " + SQL_SELECT);
            result = statement.executeQuery();
            Usuario u = null;
            while(result.next()) {
                u = new Usuario();
                u.setId(result.getInt(1));
                u.setUsuario(result.getString(2));
                u.setPassword(result.getString(3));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(result);
            Conexion.close(statement);
            Conexion.close(connection);
        }
        
        return list;
    }
}
