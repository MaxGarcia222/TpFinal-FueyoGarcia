package Herramientas;

public abstract class Herramienta {
    private String nombre;
    private String descripcion;    //Que hace masomenos, hola mabsia
    private boolean requierePremium;


    //CONSTRUCTORES
    public Herramienta(String nombre, String descripcion, boolean requierePremium) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.requierePremium = requierePremium;
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

    public boolean isRequierePremium() {
        return requierePremium;
    }

    public void setRequierePremium(boolean requierePremium) {
        this.requierePremium = requierePremium;
    }

    //TO STRING


    @Override
    public String toString() {
        return "Herramienta{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", requierePremium=" + requierePremium +
                '}';
    }


}
