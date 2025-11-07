import Herramientas.Notas;

public class Main {
    public static void main(String[] args) {
        Notas notas = new Notas();
        notas.agregarNota("Prueba", "Hola esto es una prueba");
        notas.agregarNota("Prueba2", "Hola esto es otra prueba");

        notas.listarNota("Prueba");
        notas.continuarNota("Prueba", ", y ahora la sigo.");
        notas.listarNota("Prueba");

        notas.listarNotas();
    }
}
