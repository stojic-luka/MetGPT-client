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

public class NetworkManager {

    private static final String BASE_URL = "http://127.0.0.1:8080/";
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static String getBaseUrl() {
        return BASE_URL;
    }

//        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(response -> JsonManager.stringToJson(response.body()))
//                .thenAccept(callback);
//    
    public static JsonObject sendGetRequestAsync(String urlString) throws InterruptedException, ExecutionException {
        return sendAsync(
                HttpRequest.newBuilder()
                        .uri(URI.create(urlString))
                        .timeout(Duration.ofSeconds(10))
                        .header("Content-Type", "application/json")
                        .GET()
                        .build()
        );
    }

    public static JsonObject sendPostRequestAsync(String urlString, Map objMessage) throws InterruptedException, ExecutionException {
        return sendAsync(
                HttpRequest.newBuilder()
                        .uri(URI.create(urlString))
                        .timeout(Duration.ofSeconds(10))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(JsonManager.mapToJson(objMessage)))
                        .build()
        );
    }

    public static JsonObject sendPutRequestAsync(String urlString, Map objMessage) throws InterruptedException, ExecutionException {
        return sendAsync(
                HttpRequest.newBuilder()
                        .uri(URI.create(urlString))
                        .timeout(Duration.ofSeconds(10))
                        .header("Content-Type", "application/json")
                        .PUT(HttpRequest.BodyPublishers.ofString(JsonManager.mapToJson(objMessage)))
                        .build()
        );
    }

    public static JsonObject sendDeleteRequestAsync(String urlString) throws InterruptedException, ExecutionException {
        return sendAsync(
                HttpRequest.newBuilder()
                        .uri(URI.create(urlString))
                        .timeout(Duration.ofSeconds(10))
                        .header("Content-Type", "application/json")
                        .DELETE()
                        .build()
        );
    }

//
    private static JsonObject sendAsync(HttpRequest request) throws InterruptedException, ExecutionException {
        CompletableFuture<HttpResponse<String>> requestFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<JsonObject> requestFutureString = requestFuture.thenApply(response -> JsonManager.stringToJson(response.body()));
        return requestFutureString.get();
    }
}
