package Interfaz;

import org.json.JSONObject;

public interface JSON<T> {
    JSONObject toJSON();
    T fromJson(JSONObject obj);
}

