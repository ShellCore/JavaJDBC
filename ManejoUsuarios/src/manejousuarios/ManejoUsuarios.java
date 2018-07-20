package manejousuarios;

import datos.UsuariosJDBC;
import domain.Usuario;
import java.util.List;

/**
 *
 * @author shell
 */
public class ManejoUsuarios {

    public static void main(String[] args) {
        UsuariosJDBC usuariosJDBC = new UsuariosJDBC();
        
        // Prueba función insert
        usuariosJDBC.insert("shell", "pass");
        
        // Prueba función update
        usuariosJDBC.update(1, "mogc", "abc123");
        
        // Prueba función delete
        usuariosJDBC.delete(2);
        
        
        // Prueba función select
        List<Usuario> list = usuariosJDBC.select();
        list.forEach(u -> {
            System.out.println(u);
        });
    }
    
}
