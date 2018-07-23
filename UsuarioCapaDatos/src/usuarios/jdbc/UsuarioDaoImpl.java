package usuarios.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import usuarios.dto.UsuarioDTO;


public class UsuarioDaoImpl implements UsuarioDao {
    
    private static final String SQL_INSERT = "INSERT INTO usuario (usuario, password) values (?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET usuario = ?, password = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id = ?";
    private static final String SQL_SELECT = "SELECT id, usuario, password FROM usuario ORDERR BY id";
    
    private Connection connection;

    public UsuarioDaoImpl() {
    }

    public UsuarioDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(UsuarioDTO usuario) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        int rows = 0;
        
        try {
            
            connection = (this.connection != null) ? this.connection : Conexion.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getPassword());
            System.out.println("Ejecutando query: " + SQL_INSERT + " >>> " + usuario);
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
    public int update(UsuarioDTO usuario) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        int rows = 0;
        
        try {
            
            connection = (this.connection != null) ? this.connection : Conexion.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getPassword());
            statement.setInt(3, usuario.getId());
            System.out.println("Ejecutando query: " + SQL_UPDATE + " >>> " + usuario);
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
    public int delete(UsuarioDTO usuario) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        int rows = 0;
        
        try {
            
            connection = (this.connection != null) ? this.connection : Conexion.getConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, usuario.getId());
            System.out.println("Ejecutando query: " + SQL_DELETE + " >>> " + usuario);
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
    public List<UsuarioDTO> select() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        
        List<UsuarioDTO> list = new ArrayList<>();
        
        try {
            
            connection = (this.connection != null) ? this.connection : Conexion.getConnection();
            statement = connection.prepareStatement(SQL_SELECT);
            
            System.out.println("Ejecutando query: " + SQL_INSERT);
            result = statement.executeQuery();
            while (result.next()) {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setId(result.getInt(1));
                usuario.setUsuario(result.getString(2));
                usuario.setPassword(result.getString(3));
                list.add(usuario);
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
