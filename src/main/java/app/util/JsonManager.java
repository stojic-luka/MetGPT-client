package app.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonManager {
    private static final Gson gson = new Gson();
    
    public static String jsonToString(JsonObject obj) {
        return gson.toJson(obj);
    }
    
    public static JsonObject stringToJson(String obj) {
        return gson.fromJson(obj, JsonObject.class);
    }
}
