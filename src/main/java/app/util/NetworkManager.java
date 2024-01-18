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

    /**
     * Makes an asynchronous GET request to the specified URL and returns the response as a JsonObject.
     *
     * @param urlString the URL to send the request to
     * @return the response as a JsonObject
     * @throws InterruptedException if the request is interrupted
     * @throws ExecutionException   if an error occurs during execution of the request
     */
    public static JsonObject sendGetRequestAsync(String urlString) throws InterruptedException, ExecutionException {
        return sendAsync(
            HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .timeout(Duration.ofSeconds(10))
                .header("Content-Type", "application/json")
                .GET()
                .build());
    }

    /**
     * Sends an asynchronous POST request to the specified URL with the provided message object.
     *
     * @param  urlString    the URL to send the request to
     * @param  objMessage   the message object to include in the request body
     * @return              the JSON response received from the server
     * @throws InterruptedException if the request is interrupted while waiting
     * @throws ExecutionException   if an error occurs while executing the request
     */
    public static JsonObject sendPostRequestAsync(String urlString, Map objMessage) throws InterruptedException, ExecutionException {
        return sendAsync(
            HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .timeout(Duration.ofSeconds(10))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(JsonManager.mapToJson(objMessage)))
                .build());
    }

    /**
     * Sends a PUT request asynchronously to the specified URL with the provided message object.
     *
     * @param  urlString   the URL to send the request to
     * @param  objMessage  the message object to include in the request body
     * @return             a JsonObject representing the response from the request
     * @throws InterruptedException  if the thread is interrupted while waiting for the request to complete
     * @throws ExecutionException    if an error occurs while executing the request
     */
    public static JsonObject sendPutRequestAsync(String urlString, Map objMessage) throws InterruptedException, ExecutionException {
        return sendAsync(
            HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .timeout(Duration.ofSeconds(10))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(JsonManager.mapToJson(objMessage)))
                .build());
    }

    /**
     * Sends a DELETE request asynchronously to the specified URL.
     *
     * @param  urlString  the URL to send the request to
     * @return            the JSON response from the server
     * @throws InterruptedException  if the thread is interrupted while waiting for the request to complete
     * @throws ExecutionException    if the computation of the request threw an exception
     */
    public static JsonObject sendDeleteRequestAsync(String urlString) throws InterruptedException, ExecutionException {
        return sendAsync(
            HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .timeout(Duration.ofSeconds(10))
                .header("Content-Type", "application/json")
                .DELETE()
                .build());
    }

    /**
     * Sends an asynchronous HTTP request and returns the response as a JsonObject.
     *
     * @param  request  the HTTP request to be sent
     * @return          the response as a JsonObject
     * @throws InterruptedException  if the current thread is interrupted while waiting
     * @throws ExecutionException    if the computation of the response encounters an exception
     */
    private static JsonObject sendAsync(HttpRequest request) throws InterruptedException, ExecutionException {
        CompletableFuture<HttpResponse<String>> requestFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<JsonObject> requestFutureString = requestFuture.thenApply(response -> JsonManager.stringToJson(response.body()));
        return requestFutureString.get();
    }
}
