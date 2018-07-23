package manejopersonas;

import datos.Conexion;
import datos.PersonaJDBC;
import domain.Persona;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ManejoPersonas {

    public static void main(String[] args) {
        PersonaJDBC personaJDBC = new PersonaJDBC();
        
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
            // Revisamos si la conexión está en modo "Autocommit"
            // Por default, el modo "Autocommit" está en TRUE
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            
            personaJDBC = new PersonaJDBC(connection);

            // Empezamos a ejecutar las sentencias.
            // Recordar que una transacción agrupa varias sentencias SQL. Si 
            // algo falla, no se realizan los cambios en la base de datos.
            
            // Cambio correcto
            personaJDBC.update(3, "CambioNombre", "CambioApellido");
            
            // Provocamos un error, superando los 45 caracteres del campo 
            // apellido
            personaJDBC.insert("Miguel", "Apellido12345678901234567890123456789012345678");
            
            // Guardamos los cambios
            connection.commit();
            
        } catch (SQLException e) {
            // Hacemos Rollback en caso de error
            try {
                System.out.println("Entramos al rollback");
                // Imprimimos el error en consola
                e.printStackTrace(System.out);
                // HAcemos el Rollback
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace(System.out);
            }
        }
    }
}
