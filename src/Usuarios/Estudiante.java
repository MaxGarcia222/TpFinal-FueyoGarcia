package Usuarios;

import Enums.TipoUsuario;
import Herramientas.Calendario;
import Herramientas.GestorTodo;
import Herramientas.Herramienta;
import Herramientas.Notas;
import Interfaz.ConvertibleaJSON;
import Interfaz.DesdeJSON;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;

public class Estudiante extends Usuario {
    private List<Herramienta> herramientas;

    //CONSTRUCTORES
    public Estudiante(String nombre, String email, String contrasenia, TipoUsuario tipoUsuario) {
        super(nombre, email, contrasenia, tipoUsuario);
        this.herramientas = new ArrayList<>();
        inicializarHerramientas();
    }
    public Estudiante() {}

    //CONSTRUCTOR PARA JSON


    public Estudiante(String nombre, String email, String contrasenia, TipoUsuario tipoUsuario, List<Herramienta> herramientas) {
        super(nombre, email, contrasenia, tipoUsuario);
        this.herramientas = herramientas;
    }

    public Estudiante(List<Herramienta> herramientas) {
        this.herramientas = herramientas;
    }

    public Estudiante(String nombre, String email, String contrasenia, int id, TipoUsuario tipoUsuario, List<Herramienta> herramientas) {
        super(nombre, email, contrasenia, id, tipoUsuario);
        this.herramientas = herramientas;
    }

    private void inicializarHerramientas() {
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

    public void setHerramientas(List<Herramienta> herramientas) {
        this.herramientas = herramientas;
    }

    //METODOS JSON


    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        // (opcional: agregar herramientas en el futuro)
        return obj;
    }

    @Override
    public Estudiante fromJSON(JSONObject obj) {
        super.fromJSON(obj);
        return this;
    }

}
