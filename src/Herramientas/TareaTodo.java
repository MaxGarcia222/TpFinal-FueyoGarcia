package Herramientas;

import java.util.ArrayList;
import java.util.List;

public class TareaTodo extends Herramienta{
    private String contenido;
    private boolean completada;
    private List<String> tareas;


    public TareaTodo(String contenido) {
        this.contenido = contenido;
        this.completada = false;
        tareas = new ArrayList<>();
    }

    public String getContenido() {
        return contenido;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void completar() {
        this.completada = true;
    }

    public void desmarcar() {
        this.completada = false;
    }

    @Override
    public String toString() {
        return (completada ? "[✔] " : "[ ] ") + contenido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TareaTodo)) return false;
        TareaTodo tarea = (TareaTodo) o;
        return contenido.equalsIgnoreCase(tarea.contenido);
    }

    @Override
    public int hashCode() {
        return contenido.toLowerCase().hashCode();
    }

    //METODOS PARA LA LIST
    public void agregarTarea(String tarea) {
        tareas.add(tarea);
    }

    public void mostrarTareas() {
        System.out.println("Tus tareas:");
        for (String t : tareas) {
            System.out.println("- " + t);
        }
    }

}
