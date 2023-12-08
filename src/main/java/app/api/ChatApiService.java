package app.api;

import app.model.chat.Chat;
import app.model.chat.Message;
import app.util.NetworkManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.application.Platform;

public class ChatApiService {

    public static void loadChats(Consumer<Chat> addChatFunc) {
        String reqUrl = "http://127.0.0.1:8080/api/v1/chats";

        try {
            JsonObject respObject = NetworkManager.sendGetRequestAsync(reqUrl);
            for (JsonElement responseJsonElement : respObject.getAsJsonArray("chats")) {
                JsonObject responseJsonObject = responseJsonElement.getAsJsonObject();
                String createdAtString = responseJsonObject.getAsJsonPrimitive("createdAt").getAsString();

                Chat chat = new Chat(
                        responseJsonObject.get("chatId").getAsString(),
                        responseJsonObject.get("title").getAsString(),
                        LocalDateTime.parse(createdAtString, DateTimeFormatter.ISO_DATE_TIME)
                );

                System.out.println(chat);
                Platform.runLater(() -> addChatFunc.accept(chat));
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
        }
    }

    public static void addChat(BiConsumer<String, String> addChatFunc, Consumer<String> setCurrentChat) {
        String newChatName = "New Chat";

        String reqUrl = "http://127.0.0.1:8080/api/v1/chats";
        Map body = Map.ofEntries(Map.entry("title", newChatName));

        try {
            JsonObject respObject = NetworkManager.sendPostRequestAsync(reqUrl, body);
            String chatId = respObject.get("chatId").getAsString();
            setCurrentChat.accept(chatId);
            Platform.runLater(() -> {
                addChatFunc.accept(chatId, newChatName);
            });
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
        }
    }

    public static void renameChat(String chatId, String chatName) {
        String reqUrl = String.format("http://127.0.0.1:8080/api/v1/chats/%s", chatId);
        Map body = Map.ofEntries(Map.entry("title", chatName));

        try {
            NetworkManager.sendPutRequestAsync(reqUrl, body);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
        }
    }

    public static void deleteChat(String chatId) {
        String reqUrl = String.format("http://127.0.0.1:8080/api/v1/chats/%s", chatId);

        try {
            NetworkManager.sendDeleteRequestAsync(reqUrl);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
        }
    }

    public static void getMessages(Consumer<Message> addMessageFunc, String chatId) {
        String reqUrl = String.format("http://127.0.0.1:8080/api/v1/chats/%s/messages", chatId);

        try {
            JsonObject respObject = NetworkManager.sendGetRequestAsync(reqUrl);
            for (JsonElement responseJsonElement : respObject.getAsJsonArray("messages")) {
                JsonObject responseJsonObject = responseJsonElement.getAsJsonObject();
                String createdAtString = responseJsonObject.getAsJsonPrimitive("createdAt").getAsString();

                Message message = new Message(
                        responseJsonObject.get("content").getAsString(),
                        responseJsonObject.get("senderBot").getAsBoolean(),
                        LocalDateTime.parse(createdAtString, DateTimeFormatter.ISO_DATE_TIME)
                );

                Platform.runLater(() -> addMessageFunc.accept(message));
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
        }
    }

    public static void sendMessage(BiConsumer<String, Boolean> addMessageFunc, String chatId, String messageString) {
        String reqUrl = String.format("http://127.0.0.1:8080/api/v1/chats/%s/chat", chatId);
        Map body = Map.ofEntries(Map.entry("input", messageString));

        try {
            JsonObject respObject = NetworkManager.sendPostRequestAsync(reqUrl, body);
            String msg = respObject.get("response").getAsString();
            Platform.runLater(() -> addMessageFunc.accept(msg, true));
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
        }
    }
}
