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



       //PRUEBAS TAREAS TO DO

        GestorTodo tareas = new GestorTodo();
        try {
            tareas.agregar("Hacer el tp final");
        } catch (TareaException e) {
            System.out.println("⚠ Error: " + e.getMessage());
        }

        IO.println("");

        try {
            tareas.agregar("Hacer el tp final");
        } catch (TareaException e) {
            System.out.println("⚠ Error: " + e.getMessage());
        }

        IO.println("");


        try {
            tareas.agregar("Saludar al taigo");
        } catch (TareaException e) {
            System.out.println("⚠ Error: " + e.getMessage());
        }

        IO.println("");

        try {
            tareas.agregar("Hacer el segundo TP");
        } catch (TareaException e) {
            System.out.println("⚠ Error: " + e.getMessage());
        }

        IO.println("");

        tareas.listar();

        IO.println("");

        try {
            tareas.completar("Hacer el tp final");
        } catch (TareaException e) {
            throw new RuntimeException(e);
        }

        IO.println("");

        tareas.listar();

        IO.println("");

        //aca probe desmarcarlo escribiendolo en minusculas
        try {
            tareas.desmarcar("hacer el tp final");
        } catch (TareaException e) {
            throw new RuntimeException(e);
        }

        IO.println("");

        //aca probe buscar si decia de tp a ver si aparecian todos
        tareas.buscarPorTexto("tp");



    }
}
