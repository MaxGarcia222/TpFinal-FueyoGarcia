import Herramientas.*;
import Usuarios.*;
import Enums.TipoUsuario;
import Exceptions.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Creamos un estudiante de ejemplo
        Estudiante estudiante = new Estudiante("Max", "max@gmail.com", "1234", TipoUsuario.ESTUDIANTE);

        // Instancias de herramientas
        Notas notas = new Notas();
        GestorTodo gestor = new GestorTodo();
        Calendario calendario = new Calendario();

        boolean salir = false;
        while (!salir) {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Notas");
            System.out.println("2. Gestor ToDo");
            System.out.println("3. Calendario");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1" -> menuNotas(notas, sc);
                case "2" -> menuToDo(gestor, sc);
                case "3" -> menuCalendario(calendario, sc);
                case "0" -> salir = true;
                default -> System.out.println("Opción no válida.");
            }
        }

        System.out.println("¡Hasta luego!");
    }

    // ================= MENÚ NOTAS =================
    private static void menuNotas(Notas notas, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENÚ NOTAS ---");
            System.out.println("1. Agregar nota");
            System.out.println("2. Editar nota");
            System.out.println("3. Continuar nota");
            System.out.println("4. Cambiar título");
            System.out.println("5. Eliminar nota");
            System.out.println("6. Ver nota");
            System.out.println("7. Listar notas");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine();

            try {
                switch (op) {
                    case "1" -> {
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Contenido: ");
                        String contenido = sc.nextLine();
                        notas.agregarNota(titulo, contenido);
                    }
                    case "2" -> {
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Nuevo contenido: ");
                        String nuevo = sc.nextLine();
                        notas.editarNota(titulo, nuevo);
                    }
                    case "3" -> {
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Texto adicional: ");
                        String extra = sc.nextLine();
                        notas.continuarNota(titulo, extra);
                    }
                    case "4" -> {
                        System.out.print("Título actual: ");
                        String viejo = sc.nextLine();
                        System.out.print("Nuevo título: ");
                        String nuevo = sc.nextLine();
                        notas.cambiarTitulo(viejo, nuevo);
                    }
                    case "5" -> {
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        notas.eliminarNota(titulo);
                    }
                    case "6" -> {
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        notas.listarNota(titulo);
                    }
                    case "7" -> notas.listarNotas();
                    case "0" -> volver = true;
                    default -> System.out.println("Opción inválida.");
                }
            } catch (NotaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ================= MENÚ TODO =================
    private static void menuToDo(GestorTodo gestor, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENÚ TODO ---");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Completar tarea");
            System.out.println("3. Desmarcar tarea");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Listar todas");
            System.out.println("6. Listar completadas");
            System.out.println("7. Listar pendientes");
            System.out.println("8. Buscar por texto");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine();

            try {
                switch (op) {
                    case "1" -> {
                        System.out.print("Contenido: ");
                        String contenido = sc.nextLine();
                        gestor.agregar(contenido);
                    }
                    case "2" -> {
                        System.out.print("Tarea: ");
                        String t = sc.nextLine();
                        gestor.completar(t);
                    }
                    case "3" -> {
                        System.out.print("Tarea: ");
                        String t = sc.nextLine();
                        gestor.desmarcar(t);
                    }
                    case "4" -> {
                        System.out.print("Tarea: ");
                        String t = sc.nextLine();
                        gestor.eliminar(t);
                    }
                    case "5" -> gestor.listar();
                    case "6" -> gestor.listarCompletadas();
                    case "7" -> gestor.listarPendientes();
                    case "8" -> {
                        System.out.print("Texto: ");
                        gestor.buscarPorTexto(sc.nextLine());
                    }
                    case "0" -> volver = true;
                    default -> System.out.println("Opción inválida.");
                }
            } catch (TareaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ================= MENÚ CALENDARIO =================
    private static void menuCalendario(Calendario calendario, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENÚ CALENDARIO ---");
            System.out.println("1. Agregar evento");
            System.out.println("2. Mostrar eventos de una fecha");
            System.out.println("3. Mostrar todos los eventos");
            System.out.println("4. Mostrar calendario mensual");
            System.out.println("5. Mostrar semana");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine();

            try {
                switch (op) {
                    case "1" -> {
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Fecha (YYYY-MM-DD): ");
                        LocalDate fecha = LocalDate.parse(sc.nextLine());
                        System.out.print("Descripcion: ");
                        String descripcion = sc.nextLine();
                        calendario.agregarEvento(new Evento(titulo, descripcion, fecha));
                    }
                    case "2" -> {
                        System.out.print("Fecha (YYYY-MM-DD): ");
                        LocalDate fecha = LocalDate.parse(sc.nextLine());
                        calendario.mostrarEventosDe(fecha);
                    }
                    case "3" -> calendario.mostrarTodos();
                    case "4" -> {
                        System.out.print("Año: ");
                        int anio = Integer.parseInt(sc.nextLine());
                        System.out.print("Mes (1-12): ");
                        int mes = Integer.parseInt(sc.nextLine());
                        calendario.mostrarCalendarioMensual(anio, mes);
                    }
                    case "5" -> {
                        System.out.print("Ingrese una fecha (YYYY-MM-DD): ");
                        LocalDate fecha = LocalDate.parse(sc.nextLine());
                        calendario.mostrarSemana(fecha);
                    }
                    case "0" -> volver = true;
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
