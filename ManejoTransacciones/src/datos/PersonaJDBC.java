package datos;

import domain.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaJDBC {
    
    private static final String SQL_INSERT = "INSERT INTO persona (nombre, apellido) values (?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id = ?";
    private static final String SQL_SELECT = "SELECT id, nombre, apellido FROM persona ORDER BY id";
    
    private Connection connection;

    public PersonaJDBC() {
    }
    
    public PersonaJDBC(Connection connection) {
        this.connection = connection;
    }
    
    public int insert(String nombre, String apellido) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        int rows = 0;
        try {
            connection = (this.connection != null) ? this.connection : Conexion.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            int index = 1;
            statement.setString(index++, nombre);
            statement.setString(index++, apellido);
            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = statement.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } finally {
            Conexion.close(statement);
            if (this.connection == null) {
                Conexion.close(connection);
            }
        }
        return rows;
    }
    
    public int update(int id, String nombre, String apellido) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        int rows = 0;
        try {
            connection = (this.connection != null) ? this.connection : Conexion.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            int index = 1;
            statement.setString(index++, nombre);
            statement.setString(index++, apellido);
            statement.setInt(index++, id);
            System.out.println("Ejecutando query: " + SQL_UPDATE);
            rows = statement.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } finally {
            Conexion.close(statement);
            if (this.connection == null) {
                Conexion.close(connection);
            }
        }
        return rows;
    }
    
    public int delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        int rows = 0;
        try {
            connection = (this.connection != null) ? this.connection : Conexion.getConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            int index = 1;
            statement.setInt(index++, id);
            System.out.println("Ejecutando query: " + SQL_DELETE);
            rows = statement.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } finally {
            Conexion.close(statement);
            if (this.connection == null) {
                Conexion.close(connection);
            }
        }
        return rows;
    }
    
    public List<Persona> select() throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Persona> list = new ArrayList<>();
        
        int rows = 0;
        try {
            connection = (this.connection != null) ? this.connection : Conexion.getConnection();
            statement = connection.prepareStatement(SQL_SELECT);
            System.out.println("Ejecutando query: " + SQL_SELECT);
            result = statement.executeQuery();
            while (result.next()) {
                Persona p = new Persona();
                p.setId(result.getInt(1));
                p.setNombre(result.getString(2));
                p.setApellido(result.getString(3));
                list.add(p);
            }
        } finally {
            Conexion.close(result);
            Conexion.close(statement);
            if (this.connection == null) {
                Conexion.close(connection);
            }
        }
        return list;
    }
}
