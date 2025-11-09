package Gestores;

import Exceptions.ContraseniaIncorrectaException;
import Usuarios.Usuario;

import java.util.HashMap;

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

}

