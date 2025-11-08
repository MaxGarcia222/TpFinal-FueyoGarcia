package Usuarios;

import Enums.TipoUsuario;
import Herramientas.Calendario;
import Herramientas.GestorTodo;
import Herramientas.Herramienta;
import Herramientas.Notas;
import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Usuario {
    private List<Herramienta> herramientas;

    public Estudiante(String nombre, String email, String contrasenia, TipoUsuario tipoUsuario) {
        super(nombre, email, contrasenia, tipoUsuario);
        this.herramientas = new ArrayList<>();
        inicializarHerramientas();
    }

    private void inicializarHerramientas() {
        // si tus clases tienen constructores sin argumentos:
        herramientas.add(new Notas());
        herramientas.add(new GestorTodo());
        herramientas.add(new Calendario());
    }

    public List<Herramienta> getHerramientas() {
        return herramientas;
    }

    public Herramienta getHerramientaPorNombre(String nombre) {
        for (Herramienta h : herramientas) {
            if (h.getClass().getSimpleName().equalsIgnoreCase(nombre)) {
                return h;
            }
        }
        return null;
    }
}
