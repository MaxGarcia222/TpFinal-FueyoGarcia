import Exceptions.TareaException;
import Herramientas.GestorTodo;
import Herramientas.Notas;
import Herramientas.TareaTodo;

public class Main {
    public static void main(String[] args) {

        /* PRUEBAS DE LAS NOTAS
        Notas notas = new Notas();
        notas.agregarNota("Prueba", "Hola esto es una prueba");
        notas.agregarNota("Prueba2", "Hola esto es otra prueba");

        notas.listarNota("Prueba");
        notas.continuarNota("Prueba", ", y ahora la sigo.");
        notas.listarNota("Prueba");

        notas.listarNotas();
         */

        /*
        PRUEBAS TAREAS TO DO

        GestorTodo tareas = new GestorTodo();
        try {
            tareas.agregar("Hacer el tp final");
        } catch (TareaException e) {
            System.out.println("⚠ Error: " + e.getMessage());
        }

        tareas.listar();
        try {
            tareas.completar("Hacer el tp final");
        } catch (TareaException e) {
            throw new RuntimeException(e);
        }

        tareas.listar();

        //aca probe desmarcarlo escribiendolo en minusculas
        try {
            tareas.desmarcar("hacer el tp final");
        } catch (TareaException e) {
            throw new RuntimeException(e);
        }
        tareas.listar();


         */
    }
}
