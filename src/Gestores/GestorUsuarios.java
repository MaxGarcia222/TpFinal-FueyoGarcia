package Gestores;

import Usuarios.Estudiante;
import Usuarios.Usuario;

import java.util.HashMap;

public class GestorUsuarios {
    HashMap<Integer, Usuario> usuarios;
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
        }//ACA PONER EXCEPCION
        System.out.println("Error: credenciales inválidas");
        return null;
    }

    // Eliminar usuario por ID (para admin)
    public boolean eliminarUsuario(int id) {
        if (usuarios.containsKey(id)) {
            usuarios.remove(id);
            return true;
        }
        return false;
    }

    // Actualizar tareas por carrera (para admin)
    public void actualizarTareasPorCarrera(String carrera) {
        for (Usuario u : usuarios.values()) {
            if (u instanceof Estudiante e && e.getCarrera().equalsIgnoreCase(carrera)) {
                e.actualizarTareas(); //HACERLO EN TAREASTODO
            }
        }
    }

    //Mostrar todos los usuarios
    public void listarUsuarios() {
        for (Usuario u : usuarios.values()) {
            System.out.println(u.toString());
        }
    }
}

