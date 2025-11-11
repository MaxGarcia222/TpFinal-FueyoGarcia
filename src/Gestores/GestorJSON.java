package Gestores;

import Interfaz.ConvertibleaJSON;
import Interfaz.DesdeJSON;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;



public class GestorJSON<T extends ConvertibleaJSON> {

    private final DesdeJSON<T> convertidor;
    private final String rutaArchivo; // por ejemplo: "usuarios.json"

    public GestorJSON(String rutaArchivo, DesdeJSON<T> convertidor) {
        this.rutaArchivo = rutaArchivo;
        this.convertidor = convertidor;
    }

    public void guardar(List<T> lista) {
        JSONArray arr = new JSONArray();
        for (T obj : lista) arr.put(obj.toJSON());
        // usa Gestores.JsonUtiles del profe
        JsonUtiles.grabarUnJson(arr, rutaArchivo);
    }

    public List<T> cargar() {
        List<T> lista = new ArrayList<>();
        JSONTokener tokener = JsonUtiles.leerUnJson(rutaArchivo);
        if (tokener == null) return lista;

        JSONArray arr = new JSONArray(tokener);
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            lista.add(convertidor.fromJSON(obj));
        }
        return lista;
    }
}
