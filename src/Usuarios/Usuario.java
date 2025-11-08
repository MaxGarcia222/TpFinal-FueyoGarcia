package Usuarios;

import Enums.TipoUsuario;

import java.util.Objects;

public abstract class Usuario {
    private String nombre;
    private String email;
    private String contrasenia;
    private int id;
    private static int contador = 0;
    private TipoUsuario tipoUsuario;


    //CONSTRUCTORES
    public Usuario(String nombre, String email, String contrasenia, TipoUsuario tipoUsuario) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.id = contador++;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario() {
    }

    //GETTERS Y SETTERS

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
    public void cambiarContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }




}
