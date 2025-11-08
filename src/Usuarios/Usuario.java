package Usuarios;

import java.util.Objects;

public abstract class Usuario {
    private int nombre;
    private String email;
    private String contrasenia;
    //id autoincremental
    private int id;
    private static int contador = 0;


    //CONSTRUCTORES
    public Usuario(int nombre, String email, String contrasenia, int id) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.id = contador++;
    }

    public Usuario() {
    }

    //GETTERS Y SETTERS


    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Usuario.contador = contador;
    }

    //TO STRING

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre=" + nombre +
                ", email='" + email + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", id=" + id +
                '}';
    }

    //EQUALS Y HASHCODE SEGUN ID

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    //METODOS

    public boolean iniciarSesion() {
        return false;
    }
}
