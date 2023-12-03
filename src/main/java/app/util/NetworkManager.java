package app.util;

import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class NetworkManager {

    public static void sendGetRequestAsync(String urlString, Consumer<JsonObject> callback) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .timeout(Duration.ofSeconds(10))
                .header("Content-Type", "application/json")
                .GET()
                .build();

//        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(response -> JsonManager.stringToJson(response.body()))
//                .thenAccept(callback);
        CompletableFuture<HttpResponse<String>> requestFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<JsonObject> requestFutureString = requestFuture.thenApply(response -> JsonManager.stringToJson(response.body()));
        try {
            callback.accept(requestFutureString.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void sendPostRequestAsync(String urlString, Map objMessage, Consumer<JsonObject> callback) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .timeout(Duration.ofSeconds(10))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(JsonManager.mapToJson(objMessage)))
                .build();

//        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(response -> JsonManager.stringToJson(response.body()))
//                .thenAccept(callback);
        CompletableFuture<HttpResponse<String>> requestFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<JsonObject> requestFutureString = requestFuture.thenApply(response -> JsonManager.stringToJson(response.body()));
        try {
            callback.accept(requestFutureString.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
