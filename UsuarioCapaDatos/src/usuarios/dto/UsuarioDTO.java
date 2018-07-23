package usuarios.dto;

public class UsuarioDTO {
    
    private int id;
    private String usuario;
    private String password;

    public UsuarioDTO() {
    }

    public UsuarioDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario {"
                + "Id: " + id
                + ", Usuario: " + usuario
                + ", Password: " + password
                + "}";
    }
}
