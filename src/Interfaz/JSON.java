package Interfaz;

import org.json.JSONObject;

public interface JSON<T> {
    JSONObject toJson();
    T fromJson(JSONObject json);
}

