package Herramientas;
import Exceptions.TareaException;
import java.util.HashSet;
import java.util.Set;

public class GestorTodo {

    private Set<TareaTodo> tareas;

    public GestorTodo() {
        tareas = new HashSet<>();
    }

    public void agregar(String contenido) throws TareaException {
        TareaTodo nueva = new TareaTodo(contenido);

        if (!tareas.add(nueva)) {
            throw new TareaException("La tarea '" + contenido + "' ya existe.");
        }
    }

    public void completar(String contenido) throws TareaException {
        TareaTodo tarea = buscar(contenido);
        tarea.completar();
    }

    public void desmarcar(String contenido) throws TareaException {
        TareaTodo tarea = buscar(contenido);
        tarea.desmarcar();
    }

    public void eliminar(String contenido) throws TareaException {
        TareaTodo tarea = buscar(contenido);

        if (!tareas.remove(tarea)) {
            throw new TareaException("No se pudo eliminar la tarea '" + contenido + "'");
        }
    }

    // Busca la tarea (uso interno)
    private TareaTodo buscar(String contenido) throws TareaException {
        for (TareaTodo t : tareas) {
            if (t.getContenido().equalsIgnoreCase(contenido)) {
                return t;
            }
        }
        throw new TareaException("La tarea '" + contenido + "' no existe.");
    }

    // Listar todas
    public void listar() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
            return;
        }
        tareas.forEach(System.out::println);
    }

    // Listar solo completadas
    public void listarCompletadas() {
        tareas.stream()
                .filter(TareaTodo::isCompletada)
                .forEach(System.out::println);
    }

    // Listar solo pendientes
    public void listarPendientes() {
        tareas.stream()
                .filter(t -> !t.isCompletada())
                .forEach(System.out::println);
    }

    public void buscarPorTexto(String texto) {
        boolean found = false;

        //el for each recorre el hashSet, t.getContenido() te da el texto de la tarea (String)
        //
        //.toLowerCase() pasa a minúsculas
        //
        //.contains(texto.toLowerCase()) pregunta si la tarea contiene el texto buscado

        for (TareaTodo t : tareas) {
            if (t.getContenido().toLowerCase().contains(texto.toLowerCase())) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No se encontraron tareas con: " + texto);
        }
    }

}
