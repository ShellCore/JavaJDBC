package usuarios.jdbc;

import java.sql.SQLException;
import java.util.List;
import usuarios.dto.UsuarioDTO;

public interface UsuarioDao {
    
    int insert(UsuarioDTO usuario) throws SQLException;
    int update(UsuarioDTO usuario) throws SQLException;
    int delete(UsuarioDTO usuario) throws SQLException;
    List<UsuarioDTO> select() throws SQLException;
}
