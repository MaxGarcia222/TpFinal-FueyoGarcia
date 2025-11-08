import Herramientas.*;
import Usuarios.*;
import Enums.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Estudiante e = new Estudiante("Max", "max@gmail.com", "1234", TipoUsuario.ESTUDIANTE);

        System.out.println("=== HERRAMIENTAS DISPONIBLES ===");
        for (Herramienta h : e.getHerramientas()) {
            System.out.println("- " + h.getClass().getSimpleName());
        }

        System.out.print("Elegí una herramienta: ");
        String eleccion = sc.nextLine();

        Herramienta herramientaSeleccionada = e.getHerramientaPorNombre(eleccion);

        if (herramientaSeleccionada != null) {
            System.out.println("Usando " + herramientaSeleccionada.getClass().getSimpleName() + "...");
            // Ejemplo: casteamos para usar sus métodos específicos
            if (herramientaSeleccionada instanceof Notas notas) {
                try {
                    notas.agregarNota("Ejemplo", "Esta es una nota de prueba");
                    notas.listarNotas();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else {
            System.out.println("Herramienta no encontrada.");
        }
    }
}
