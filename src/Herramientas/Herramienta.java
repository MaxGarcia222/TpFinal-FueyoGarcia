package Herramientas;
import org.json.JSONObject;

public abstract class Herramienta {
    private String nombre;
    private String descripcion;


    //CONSTRUCTORES
    public Herramienta(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Herramienta() {
    }

    //GETERS Y SETTERS

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //TO STRING


    @Override
    public String toString() {
        return "Herramienta{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion +
                '}';
    }

}
