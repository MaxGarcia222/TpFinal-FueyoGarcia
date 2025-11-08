package Herramientas;
import Enums.TipoEvento;

import java.time.LocalDate;

public class Evento {
    private String titulo;
    private String descripcion;
    private LocalDate fecha;
    private TipoEvento tipo;

    public Evento(String titulo, String descripcion, LocalDate fecha, TipoEvento tipo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public LocalDate getFecha() { return fecha; }
    public TipoEvento getTipo() { return tipo; }

    @Override
    public String toString() {
        return fecha + " - [" + tipo + "] " + titulo + ": " + descripcion;
    }
}
