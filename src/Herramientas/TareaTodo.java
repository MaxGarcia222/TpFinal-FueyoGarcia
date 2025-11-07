package Herramientas;

public class TareaTodo extends Herramienta{
    private String contenido;
    private boolean completada;

    public TareaTodo(String contenido) {
        this.contenido = contenido;
        this.completada = false;
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
}
