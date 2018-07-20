package manejopersonas;

import datos.PersonaJDBC;
import domain.Persona;
import java.util.List;

public class ManejoPersonas {

    public static void main(String[] args) {
        PersonaJDBC personaJDBC = new PersonaJDBC();
        
        // Prueba del método Insert
        personaJDBC.insert("Cesar", "Morales");
        
        // Prueba del método update
        personaJDBC.update(1, "Juan", "Osorio");
        
        // Prueba del método delete
        personaJDBC.delete(2);
        
        // Prueba del método select
        List<Persona> list = personaJDBC.select();
        list.forEach(p -> {
            System.out.println(p);
        });
    }
}
