import Enums.TipoEvento;
import Exceptions.TareaException;
import Herramientas.*;

import java.time.LocalDate;

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

         */

        /*
        //PRUEBA CALENDARIO
        Calendario c = new Calendario();

        // crear fechas
        LocalDate f1 = LocalDate.of(2025, 11, 11);
        LocalDate f2 = LocalDate.of(2025, 11, 8);

        // crear eventos
        c.agregarEvento(new Evento("Tp Final", "Entrega tp final :'(", f1, TipoEvento.EXAMEN ));
        c.agregarEvento(new Evento("llorar", ":'(", f2, TipoEvento.OTRO ));



        // Probar vista mensual
        c.mostrarCalendarioMensual(2025, 11);

        // Probar vista semanal
        c.mostrarSemana(LocalDate.of(2025, 11, 8));

         */


    }
}
