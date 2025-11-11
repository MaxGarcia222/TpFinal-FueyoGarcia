package Interfaz;
import org.json.JSONObject;

public interface DesdeJSON<T>{
    T fromJSON(JSONObject obj);
}
