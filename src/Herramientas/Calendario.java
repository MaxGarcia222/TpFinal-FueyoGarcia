package Herramientas;
import Exceptions.EventoDuplicadoException;
import Exceptions.FechaInvalidaException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

//La proxima que hagamos un tp olvidemonos de las fechas por favor :'(

public class Calendario {
    private Map<LocalDate, List<Evento>> eventos = new HashMap<>();

    public void agregarEvento(Evento e) {
        if (e == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo.");
        }

        LocalDate fecha = e.getFecha();
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha del evento no puede ser nula.");
        }

        if (fecha.isBefore(LocalDate.now())) {
            throw new FechaInvalidaException("No se puede agregar un evento en una fecha pasada.");
        }

        eventos.putIfAbsent(fecha, new ArrayList<>());
        List<Evento> lista = eventos.get(fecha);

        for (Evento existente : lista) {
            if (existente.getTitulo().equalsIgnoreCase(e.getTitulo())) {
                throw new EventoDuplicadoException(
                        "Ya existe un evento con el mismo título en esta fecha."
                );
            }
        }

        lista.add(e);
        System.out.println("Evento agregado con éxito.");
    }

    public void mostrarEventosDe(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha ingresada es nula.");
        }

        List<Evento> lista = eventos.get(fecha);
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay eventos para esta fecha.");
            return;
        }

        for (Evento e : lista) {
            System.out.println(e);
        }
    }

    public void mostrarTodos() {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos cargados.");
            return;
        }

        for (LocalDate fecha : eventos.keySet()) {
            for (Evento e : eventos.get(fecha)) {
                System.out.println(e);
            }
        }
    }

    public void mostrarCalendarioMensual(int anio, int mes) {
        // crea una fecha con el primer día del mes
        LocalDate inicio = LocalDate.of(anio, mes, 1);

        //crea una fecha con el primer dia del mes
        int diasMes = inicio.lengthOfMonth();

        //calcula la cantidad de dias del mes
        DayOfWeek primerDiaSemana = inicio.getDayOfWeek();

        //primerDiaSemana.getValue() devuelve un número según el día, lo q hace todo es calcular
        //cuantos espacios dejar para el inicio del mes
        //ponele que asi:

        //Lu||Mar||Mier||Jue||Vie||Sab||Dom
        // -   -    -     -    -    1    2

        //lo que dice la linea es "La línea calcula cuántos espacios vacíos imprimir antes del día 1
        //restando 1 al número del día de la semana (porque el lunes empieza en columna 0)
        //o usando 6 si es domingo, de modo que el “1” quede alineado en la columna correcta del calendario."

        int espaciosIniciales = primerDiaSemana.getValue() == 7 ? 6 : primerDiaSemana.getValue() - 1;

        //imprime el nombre del mes en español y el año
        System.out.println("\n    " + inicio.getMonth().getDisplayName(TextStyle.FULL, new Locale("es")) + " " + anio);
        System.out.println("Lu Ma Mi Ju Vi Sa Do");

        for (int i = 0; i < espaciosIniciales; i++) {
            System.out.print("   ");
        }

        for (int dia = 1; dia <= diasMes; dia++) {
            //arma la fecha del día actual
            LocalDate fechaActual = LocalDate.of(anio, mes, dia);

            //pregunta si el dia tiene eventos guardados
            boolean tieneEventos = eventos.containsKey(fechaActual);

            if (tieneEventos) {
                System.out.printf("%2d*", dia); //----> * si hay evento
            } else {
                System.out.printf("%2d ", dia);
            }

            //cuando se completan 7 columnas, baja a la siguiente línea
            //si no deja un espacio para separar los días
            if ((dia + espaciosIniciales) % 7 == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }

        IO.println("\n");
    }

    public void mostrarSemana(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }

        //a partir de la fecha que recibe, calcula el lunes de esa semana y el domingo
        LocalDate lunes = fecha.with(DayOfWeek.MONDAY);
        LocalDate domingo = fecha.with(DayOfWeek.SUNDAY);

        //muestra el rango de la semana
        System.out.println("\nSemana del " + lunes + " al " + domingo);
        System.out.println("Lu Ma Mi Ju Vi Sa Do");

        LocalDate actual = lunes;

        for (int i = 0; i < 7; i++) {
            boolean tieneEventos = eventos.containsKey(actual);

            if (tieneEventos) {
                System.out.printf("%2d*", actual.getDayOfMonth());
            } else {
                System.out.printf("%2d ", actual.getDayOfMonth());
            }

            System.out.print(" ");
            actual = actual.plusDays(1);
        }

        System.out.println("\n");

        mostrarEventosDeLaSemana(lunes, domingo);
    }


    public void mostrarEventosDeLaSemana(LocalDate lunes, LocalDate domingo) {
        System.out.println("Eventos de la semana: ");

        boolean hayEventos = false;

        //recorre cada día desde el lunes hasta el domingo, avanzando de a un día.
        for (LocalDate dia = lunes; !dia.isAfter(domingo); dia = dia.plusDays(1)) {

            //busca en el mapa si ese día tiene una lista de eventos
            List<Evento> lista = eventos.get(dia);

            //si la lista existe y no está vacía, entonces ese día tiene eventos
            //tonces imprime el dia en español y el numero

            if (lista != null && !lista.isEmpty()) {
                hayEventos = true;

                System.out.println("\n" + dia.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es"))
                        + " " + dia.getDayOfMonth() + ":");

                for (Evento e : lista) {
                    System.out.println(" - " + e.getTitulo() + " (" + e.getTipo() + ")");
                }
            }
        }

        if (!hayEventos) {
            System.out.println("No hay eventos esta semana.");
        }

        System.out.println();
    }




}
