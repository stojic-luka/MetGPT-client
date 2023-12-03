package app.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Map;

public class JsonManager {

    private static final Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }

    public static String jsonToString(JsonObject obj) {
        return gson.toJson(obj);
    }

    public static JsonObject stringToJson(String obj) {
        return gson.fromJson(obj, JsonObject.class);
    }

    public static String mapToJson(Map obj) {
        return gson.toJson(obj);
    }
}
