package Herramientas;

import Exceptions.NotaException;

import java.util.HashMap;
import java.util.Map;
public class Notas extends Herramienta{

    private Map<String, String> notas;

    public Notas() {
        notas = new HashMap<>();
    }

    public void agregarNota(String titulo, String contenido) throws NotaException {
        if (notas.containsKey(titulo)) {
            throw new NotaException("La nota con título '" + titulo + "' ya existe.");
        }
        notas.put(titulo, contenido);
    }

    public void editarNota(String titulo, String nuevoContenido) throws NotaException {
        if (!notas.containsKey(titulo)) {
            throw new NotaException("No existe una nota con el título '" + titulo + "'");
        }
        notas.put(titulo, nuevoContenido);
    }

    public void continuarNota(String titulo, String textoExtra) throws NotaException {
        if (!notas.containsKey(titulo)) {
            throw new NotaException("No existe la nota '" + titulo + "'");
        }
        notas.put(titulo, notas.get(titulo) + "\n" + textoExtra);
    }

    //Cambia la key del map
    public void cambiarTitulo(String viejo, String nuevo) throws NotaException {
        if (!notas.containsKey(viejo)) {
            throw new NotaException("No existe la nota '" + viejo + "'");
        }
        if (notas.containsKey(nuevo)) {
            throw new NotaException("El título '" + nuevo + "' ya está en uso.");
        }

        String contenido = notas.remove(viejo);
        notas.put(nuevo, contenido);
    }

    public void eliminarNota(String titulo) throws NotaException {
        if (!notas.containsKey(titulo)) {
            throw new NotaException("No existe la nota '" + titulo + "'");
        }
        notas.remove(titulo);
    }


    public String obtenerNota(String titulo) throws NotaException {
        if (!notas.containsKey(titulo)) {
            throw new NotaException("No existe la nota '" + titulo + "'");
        }
        return notas.get(titulo);
    }

    //muestra todos los titulos de las notas (podemos usarlo para el menu)
    public void listarNotas() {
        if (notas.isEmpty()) {
            System.out.println("No hay notas guardadas.");
            return;
        }

        for (Map.Entry<String, String> entry : notas.entrySet()) {
            System.out.println("---------------------------");
            System.out.println("Título: " + entry.getKey());
            System.out.println("---------------------------");
            System.out.println("");
        }
    }

    public void listarNota(String titulo) throws NotaException {
        if (!notas.containsKey(titulo)) {
            throw new NotaException("No existe la nota '" + titulo + "'");
        }

        System.out.println(titulo);
        System.out.println("---------------------------");
        System.out.println(notas.get(titulo));
        System.out.println("---------------------------");
        //IO.println(""); io?
    }
}



