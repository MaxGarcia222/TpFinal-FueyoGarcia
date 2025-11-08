package Usuarios;

import Herramientas.Herramienta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estudiante extends Usuario {
    //manada de chinos (-(-(-(-(-.-)-)-)-)-)
    private Map<String, Herramienta> herramientas;
    /*TIENE QUE SER UN MAP PARA QUE PERMITA SOLO UNA INSTANCIA DE HERRAMINENTA POR TIPO
    PORQUE NO TENDRIA SENTIDO QUE POR EJEMPLO UN USUARIO TENGA DOS CALENDARIOS
    LA KEY VBA A SER EL NOMBRE DE LA HERRAMIENTA
    PORQ GRITO NOSE*/
    private List<String> tareas;

    //CONSTRUCTORES

    public Estudiante(String nombre, String email, String contrasenia, int id, Map<String, Herramienta> herramientas) {
        super(nombre, email, contrasenia, id);
        herramientas = new HashMap<String, Herramienta>();
        tareas = new ArrayList<>();
    }

    public Estudiante(Map<String, Herramienta> herramientas) {
        this.herramientas = herramientas;
    }

    //METODOS PARA LA LISTA DE TAREAS
    public void agregarTarea(String tarea) {
        tareas.add(tarea);
    }

    public void mostrarTareas() {
        System.out.println("Tus tareas:");
        for (String t : tareas) {
            System.out.println(t + "\n");
        }
    }

    //METODOS
    public void agregarHerramienta(Herramienta h) {
        herramientas.put(h.getNombre(), h);
    }

    public Herramienta getHerramienta(String nombre) {
        return herramientas.get(nombre);
    }

    public void usarHerramienta(String nombre) {
        Herramienta h = herramientas.get(nombre);
        if (h != null) {
            h.usar(); // metodo abstracto o interfaz para que cada heramineta lo aplique segun su necesidad
        } else {
            System.out.println("No tienes esa herramienta disponible.");
        }
    }


    @Override
    public String getTipoUsuario() {
        return "ESTUDIANTE";
    }
}
