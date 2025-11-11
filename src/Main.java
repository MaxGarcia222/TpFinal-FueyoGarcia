import Gestores.GestorUsuarios;
import Herramientas.*;
import Usuarios.*;
import Enums.TipoUsuario;
import Exceptions.*;
import Gestores.GestorJSON;
import Gestores.JsonUtiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorUsuarios gestorUsuarios = new GestorUsuarios();

        // ====== USUARIOS DE PRUEBA ======
        Estudiante est1 = new Estudiante("Max", "max@gmail.com", "1234", TipoUsuario.ESTUDIANTE);
        Estudiante est2 = new Estudiante("Tiago", "tiago@gmail.com", "5678", TipoUsuario.ESTUDIANTE);
        Administrador admin = new Administrador("Profe", "admin@gmail.com", "admin", TipoUsuario.ADMINISTRADOR);

        gestorUsuarios.agregarUsuario(est1);
        gestorUsuarios.agregarUsuario(est2);
        gestorUsuarios.agregarUsuario(admin);

        // ====== GESTOR JSON ======
        GestorJSON<Usuario> gestorJSON = new GestorJSON<>("usuarios.json", obj -> {
            Usuario u;
            String tipo = obj.getString("tipoUsuario");
            if (tipo.equalsIgnoreCase("ADMIN")) {
                u = new Administrador();
            } else {
                u = new Estudiante();
            }
            u.fromJSON(obj);
            return u;
        });

        Usuario usuarioActual = null;
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Registrarse");
            System.out.println("3. Guardar usuarios en JSON");
            System.out.println("4. Cargar usuarios desde JSON");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1" -> {
                    try {
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Contraseña: ");
                        String pass = sc.nextLine();

                        usuarioActual = gestorUsuarios.iniciarSesion(email, pass);

                        if (usuarioActual instanceof Administrador adminLog) {
                            menuAdmin(adminLog, gestorUsuarios, sc);
                        } else if (usuarioActual instanceof Estudiante est) {
                            menuEstudiante(est, sc);
                        }
                    } catch (ContraseniaIncorrectaException e) {
                        System.out.println("x " + e.getMessage());
                    }
                }

                case "2" -> registrarse(sc, gestorUsuarios);

                case "3" -> {
                    List<Usuario> lista = new ArrayList<>(gestorUsuarios.getUsuarios().values());
                    gestorJSON.guardar(lista);
                    System.out.println("Usuarios guardados correctamente en JSON.");
                }

                case "4" -> {
                    List<Usuario> cargados = gestorJSON.cargar();
                    System.out.println("Usuarios cargados desde JSON:");
                    for (Usuario u : cargados) {
                        System.out.println("- " + u.getNombre() + " (" + u.getTipoUsuario() + ")");
                    }
                }

                case "0" -> salir = true;

                default -> System.out.println("Opcion no valida.");
            }
        }

        System.out.println("¡Nos vemos en Disney Channel!");
    }

    // ================= REGISTRARSE =================
    private static void registrarse(Scanner sc, GestorUsuarios gestor) {
        System.out.println("\n--- REGISTRO DE NUEVO USUARIO ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = sc.nextLine();

        if (gestor.buscarUsuarioPorEmail(email) != null) {
            System.out.println("Error, Ya existe un usuario con ese correo.");
            return;
        }

        Estudiante nuevo = new Estudiante(nombre, email, contrasenia, TipoUsuario.ESTUDIANTE);
        gestor.agregarUsuario(nuevo);
        System.out.println("Registro exitoso ¡Bienvenido/a, " + nombre + "!");
    }

    // ================= MENÚ ADMIN =================
    private static void menuAdmin(Administrador admin, GestorUsuarios gestor, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Cambiar tipo de usuario");
            System.out.println("3. Eliminar usuario");
            System.out.println("0. Cerrar sesion");
            System.out.print("Opcion: ");
            String op = sc.nextLine();

            try {
                switch (op) {
                    case "1" -> gestor.listarUsuarios();
                    case "2" -> {
                        System.out.print("ID del usuario: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nuevo tipo (1=ESTUDIANTE, 2=ADMIN): ");
                        int tipo = Integer.parseInt(sc.nextLine());
                        TipoUsuario nuevoTipo = (tipo == 1)
                                ? TipoUsuario.ESTUDIANTE
                                : TipoUsuario.ADMINISTRADOR;
                        admin.cambiarTipoUsuario(id, gestor, nuevoTipo);
                    }
                    case "3" -> {
                        System.out.print("ID del usuario: ");
                        int id = Integer.parseInt(sc.nextLine());
                        admin.eliminarUsuario(id, gestor);
                    }
                    case "0" -> volver = true;
                    default -> System.out.println("Opcion invalida.");
                }
            } catch (IdInvalidoException e) {
                System.out.println("x " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }

    // ================= MENÚ ESTUDIANTE =================
    private static void menuEstudiante(Estudiante estudiante, Scanner sc) {
        boolean volver = false;
        Notas notas = new Notas();
        GestorTodo gestor = new GestorTodo();
        Calendario calendario = new Calendario();

        while (!volver) {
            System.out.println("\n===== MENU ESTUDIANTE(no es comida) =====");
            System.out.println("1. Notas");
            System.out.println("2. Gestor ToDo");
            System.out.println("3. Calendario");
            System.out.println("0. Cerrar sesion");
            System.out.print("Opcion: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1" -> menuNotas(notas, sc);
                case "2" -> menuToDo(gestor, sc);
                case "3" -> menuCalendario(calendario, sc);
                case "0" -> volver = true;
                default -> System.out.println("Opcion no valida.");
            }
        }
    }

    // ================= MENÚ NOTAS =================
    private static void menuNotas(Notas notas, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENU NOTAS ---");
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
                        System.out.print("Titulo: ");
                        String titulo = sc.nextLine();
                        System.out.print("Contenido: ");
                        String contenido = sc.nextLine();
                        notas.agregarNota(titulo, contenido);
                    }
                    case "2" -> {
                        System.out.print("Titulo: ");
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
                    default -> System.out.println("Opcion invalida.");
                }
            } catch (NotaException e) {
                System.out.println("x" + e.getMessage());
            }
        }
    }

    // ================= MENU TODO =================
    private static void menuToDo(GestorTodo gestor, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENU ToDo ---");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Completar tarea");
            System.out.println("3. Desmarcar tarea");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Listar todas");
            System.out.println("6. Listar completadas");
            System.out.println("7. Listar pendientes");
            System.out.println("8. Buscar por texto");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
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
                    default -> System.out.println("Opcion invalida.");
                }
            } catch (TareaException e) {
                System.out.println("x" + e.getMessage());
            }
        }
    }

    // ================= MENU CALENDARIO =================
    private static void menuCalendario(Calendario calendario, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENU CALENDARIO ---");
            System.out.println("1. Agregar evento");
            System.out.println("2. Mostrar eventos de una fecha");
            System.out.println("3. Mostrar todos los eventos");
            System.out.println("4. Mostrar calendario mensual");
            System.out.println("5. Mostrar semana");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            String op = sc.nextLine();

            try {
                switch (op) {
                    case "1" -> {
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Fecha (YYYY-MM-DD): ");
                        LocalDate fecha = LocalDate.parse(sc.nextLine());
                        System.out.print("Descripción: ");
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
                    default -> System.out.println("Opcion invalida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
