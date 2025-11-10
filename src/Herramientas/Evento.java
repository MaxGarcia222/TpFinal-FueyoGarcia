package Herramientas;
import Enums.TipoEvento;

import java.time.LocalDate;
import org.json.JSONObject;

public class Evento {
    private String titulo;
    private String descripcion;
    private LocalDate fecha;

    public Evento(String titulo, String descripcion, LocalDate fecha) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public LocalDate getFecha() { return fecha; }


    @Override
    public String toString() {
        return fecha +
                " --> " + titulo +
                ": " + descripcion;
    }

}
