package Usuarios;

import Enums.TipoUsuario;
import Exceptions.IdInvalidoException;
import Gestores.GestorUsuarios;
import org.json.JSONObject;
import org.json.JSONArray;


import java.sql.SQLOutput;

public class Administrador extends Usuario  {
    public Administrador(String nombre, String email, String contrasenia, TipoUsuario tipoUsuario) {
        super(nombre, email, contrasenia, tipoUsuario);
    }

    public Administrador() {
    }

    public Administrador(String nombre, String email, String contrasenia, int id, TipoUsuario tipoUsuario) {
        super(nombre, email, contrasenia, id, tipoUsuario);
    }
    //CONTRUCTOR PARA JSON


    //METODOS
    public void eliminarUsuario(int id, GestorUsuarios gestor) {
        boolean eliminado = gestor.eliminarUsuario(id);
        if (eliminado) {
            System.out.println("Usuario con ID " + id + " eliminado correctamente.");
        } else {
            throw new IdInvalidoException("ERROR: id no encontrado");
        }
    }


    // Cambiar tipo de usuario
    public void cambiarTipoUsuario(int id, GestorUsuarios gestor, TipoUsuario nuevoTipo) {
        Usuario u = gestor.buscarUsuarioPorId(id);
        if (u instanceof Estudiante e) {
            e.setTipoUsuario(nuevoTipo);
            System.out.println("El usuario " + e.getNombre() + " ahora es " + nuevoTipo);
        } else {
            System.out.println("No se puede cambiar el tipo de este usuario.");
        }
    }

    //METODOS JSON


    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("tipo", "Administrador");
        return json;
    }

    @Override
    public Administrador fromJSON(JSONObject obj) {
        super.fromJSON(obj);
        return this;
    }

}