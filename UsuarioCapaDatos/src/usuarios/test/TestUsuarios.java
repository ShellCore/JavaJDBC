package usuarios.test;

import java.sql.SQLException;
import java.util.List;
import usuarios.dto.UsuarioDTO;
import usuarios.jdbc.UsuarioDao;
import usuarios.jdbc.UsuarioDaoImpl;

public class TestUsuarios {
    
    public static void main(String[] args) {
        
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        
        UsuarioDTO u1 = new UsuarioDTO();
        u1.setUsuario("ShellCore");
        u1.setPassword("Pass123");
        
        UsuarioDTO u2 = new UsuarioDTO();
        u2.setId(1);
        u2.setUsuario("UsuarioActualizado");
        u2.setPassword("PasswordActualizado");
        
        try {
            usuarioDao.insert(u1);
            usuarioDao.update(u2);
            usuarioDao.delete(new UsuarioDTO(2));
            
            List<UsuarioDTO> list = usuarioDao.select();
            list.forEach(u -> {
                System.out.println(u);
            });
        } catch (SQLException e) {
            System.out.println("Error en la capa de Test");
            e.printStackTrace();
        }
        
    }
}
