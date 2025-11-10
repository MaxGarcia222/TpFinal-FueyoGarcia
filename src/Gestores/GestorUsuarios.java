package Gestores;

import Exceptions.ContraseniaIncorrectaException;
import Usuarios.Administrador;
import Usuarios.Estudiante;
import Usuarios.Usuario;

import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;

public class GestorUsuarios {
    private HashMap<Integer, Usuario> usuarios;

    public GestorUsuarios() {
        usuarios = new HashMap<>();
    }

    //METODOS
    public void agregarUsuario(Usuario u) {
        usuarios.put(u.getId(), u);
    }


    public Usuario buscarUsuarioPorId(int id) {
        return usuarios.get(id);
    }


    public Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    //Iniciar sesión
    public Usuario iniciarSesion(String email, String contrasenia) {
        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getContrasenia().equals(contrasenia)) {
                System.out.println("Inicio de sesión exitoso: " + u.getNombre());
                return u;
            }
        } throw new ContraseniaIncorrectaException("ERROR: Credenciales incorrectes");
    }

    // Eliminar usuario por ID (para admin)
    public boolean eliminarUsuario(int id) {
        return usuarios.remove(id) != null;
    }


    //Mostrar todos los usuarios
    public void listarUsuarios() {
        for (Usuario u : usuarios.values()) {
            System.out.println(u.toString());
        }
    }

    //si mas adelante necesitamos recorrer el map
    public HashMap<Integer, Usuario> getUsuarios() {
        return usuarios;
    }

    //METODOS PARA JSON

    public void guardarUsuariosJSON(String archivo) {
        JSONArray array = new JSONArray();
        for (Usuario u : usuarios.values()) {
            array.put(u.toJSON());
        }
        JsonUtiles.grabarUnJson(array, archivo);
        System.out.println("Usuarios guardados en " + archivo);
    }

    public void cargarUsuariosJSON(String archivo) {
        try {
            String contenido = JsonUtiles.leer(archivo);
            JSONArray array = new JSONArray(contenido);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String tipo = obj.optString("tipo");

                Usuario u = null;
                if (tipo.equalsIgnoreCase("Administrador")) {
                    u = Administrador.fromJSON(obj);
                } else if (tipo.equalsIgnoreCase("Estudiante")) {
                    u = Estudiante.fromJSON(obj);
                }

                if (u != null) usuarios.put(u.getId(), u);
            }
            System.out.println("Usuarios cargados desde " + archivo);
        } catch (Exception e) {
            System.out.println("⚠Error al leer usuarios: " + e.getMessage());
        }
    }
}

