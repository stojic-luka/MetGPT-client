package app.util;

import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.function.Consumer;

public class NetworkManager {

//    public static JsonObject sendPostRequest(JsonObject objMessage, String urlString) {
//        HttpClient httpClient = HttpClient.newHttpClient();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(urlString))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(JsonManager.jsonToString(objMessage)))
//                .build();
//
//        try {
//            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//            if (response.statusCode() == 200) {
//                return JsonManager.stringToJson(response.body());
//            }
//        } catch (IOException | InterruptedException e) {
//        }
//        return new JsonObject();
//    }

    public static void sendPostRequestAsync(String urlString, Map objMessage, Consumer<JsonObject> callback) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(JsonManager.mapToJson(objMessage)))
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> JsonManager.stringToJson(response.body()))
                .thenAccept(callback);
    }
}
