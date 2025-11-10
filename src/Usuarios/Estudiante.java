package Usuarios;

import Enums.TipoUsuario;
import Herramientas.Calendario;
import Herramientas.GestorTodo;
import Herramientas.Herramienta;
import Herramientas.Notas;
import Interfaz.JSON;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;

public class Estudiante extends Usuario implements JSON<Usuario> {
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
        JSONObject json = super.toJSON();
        json.put("tipo", "Estudiante");
        return json;
    }

    @Override
    public Usuario fromJson(JSONObject obj) {
        Estudiante e = new Estudiante(
                obj.getString("nombre"),
                obj.getString("email"),
                obj.getString("contrasenia"),
                (TipoUsuario.valueOf(obj.getString("tipoUsuario")))
        );

        //restaura el ID
        try {
            var field = Usuario.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(e, obj.getInt("id"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return e;
    }

}
