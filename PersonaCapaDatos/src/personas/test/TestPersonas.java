package personas.test;

import java.sql.SQLException;
import java.util.List;
import personas.dto.PersonaDTO;
import personas.jdbc.PersonaDao;
import personas.jdbc.PersonaDaoJDBC;

public class TestPersonas {

    public static void main(String[] args) {
        
        PersonaDao personaDao = new PersonaDaoJDBC();
        
        PersonaDTO persona = new PersonaDTO();
        persona.setNombre("Mario");
        persona.setApellido("Mendez");
        
        PersonaDTO personaTemp = new PersonaDTO();
        personaTemp.setId(4);
        personaTemp.setNombre("Nacho");
        personaTemp.setApellido("Nuñez");
        
        try {
            personaDao.insert(persona);
            personaDao.delete(new PersonaDTO(3));
            personaDao.update(personaTemp);
            
            List<PersonaDTO> list = personaDao.select();
            list.forEach(p -> {
                System.out.println(p);
            });
        } catch (SQLException e) {
            System.out.println("Excepción en la caja de pruebas");
            e.printStackTrace();
        }
    }
    
}
