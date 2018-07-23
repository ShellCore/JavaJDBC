package personas.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import personas.dto.PersonaDTO;

public class PersonaDaoJDBC implements PersonaDao {
    
    private static final String SQL_INSERT = "INSERT INTO persona (nombre, apellido) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id = ?";
    private static final String SQL_SELECT = "SELECT id, nombre, apellido FROM persona ORDER BY id";

    private Connection connection;

    public PersonaDaoJDBC() {
    }

    public PersonaDaoJDBC(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public int insert(PersonaDTO persona) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        int rows = 0;
        try {
            connection = (this.connection != null) ? this.connection : Conexion.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            int index = 1;
            statement.setString(index++, persona.getNombre());
            statement.setString(index++, persona.getApellido());
            System.out.println("Ejecutando query: " + SQL_INSERT + " >> " + persona);
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

    @Override
    public int update(PersonaDTO persona) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        int rows = 0;
        try {
            connection = (this.connection != null) ? this.connection : Conexion.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            int index = 1;
            statement.setString(index++, persona.getNombre());
            statement.setString(index++, persona.getApellido());
            statement.setInt(index++, persona.getId());
            System.out.println("Ejecutando query: " + SQL_UPDATE + " >>> " + persona);
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

    @Override
    public int delete(PersonaDTO persona) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        int rows = 0;
        try {
            connection = (this.connection != null) ? this.connection : Conexion.getConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            int index = 1;
            statement.setInt(index++, persona.getId());
            System.out.println("Ejecutando query: " + SQL_DELETE + " >>> " + persona);
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

    @Override
    public List<PersonaDTO> select() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<PersonaDTO> list = new ArrayList<>();
        
        int rows = 0;
        try {
            connection = (this.connection != null) ? this.connection : Conexion.getConnection();
            statement = connection.prepareStatement(SQL_SELECT);
            System.out.println("Ejecutando query: " + SQL_SELECT);
            result = statement.executeQuery();
            while (result.next()) {
                PersonaDTO p = new PersonaDTO();
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
