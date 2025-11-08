package Usuarios;

import Enums.TipoUsuario;
import Gestores.GestorUsuarios;

import java.sql.SQLOutput;

public class Administrador extends Usuario {
    //pensar atributos
    // despues agrego los metodos para eliminar estudiante, cambiarle el tipo
    // ver uysuarios, ver herramientas, subirle tareas, etc


    //METODOS
    public void eliminarUsuario(int id, GestorUsuarios gestor) {
        boolean eliminado = gestor.eliminarUsuario(id);
        if (eliminado) {
            System.out.println("Usuario con ID " + id + " eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún usuario con ese ID.");
        }
    }

    // Actualizar tareas a todos los estudiantes de una carrera
    public void actualizarTareasPorCarrera(String carrera, GestorUsuarios gestor, String tarea) {
        gestor.actualizarTareasPorCarrera(carrera, tarea);
        System.out.println("Tareas actualizadas para los estudiantes de " + carrera + ".");
    }

    // Cambiar tipo de usuario (Básico ↔ Premium)
    public void cambiarTipoUsuario(int id, GestorUsuarios gestor, TipoUsuario nuevoTipo) {
        Usuario u = gestor.buscarUsuarioPorId(id);
        if (u instanceof Estudiante e) {
            e.setTipoUsuario(nuevoTipo);
            System.out.println("El usuario " + e.getNombre() + " ahora es " + nuevoTipo);
        } else {
            System.out.println("No se puede cambiar el tipo de este usuario.");
        }
    }

    @Override
    public String getTipoUsuario() {
        return "";
    }
}
