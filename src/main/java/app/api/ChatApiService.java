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
    private static final String BASE_URL = "http://127.0.0.1:8080/api/v1";

    /**
     * Loads chats asynchronously and adds each chat to the provided addChatFunc.
     *
     * @param  addChatFunc  the consumer function used to add each chat
     */
    public static void loadChats(Consumer<Chat> addChatFunc) {
        new Thread(() -> {
            String reqUrl = BASE_URL + "/chats";

            try {
                JsonObject respObject = NetworkManager.sendGetRequestAsync(reqUrl);
                for (JsonElement responseJsonElement : respObject.getAsJsonArray("chats")) {
                    JsonObject responseJsonObject = responseJsonElement.getAsJsonObject();
                    String createdAtString = responseJsonObject.getAsJsonPrimitive("createdAt").getAsString();

                    Chat chat = new Chat(
                        responseJsonObject.get("chatId").getAsString(),
                        responseJsonObject.get("title").getAsString(),
                        LocalDateTime.parse(createdAtString, DateTimeFormatter.ISO_DATE_TIME));

                    Platform.runLater(() -> addChatFunc.accept(chat));
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            } catch (ExecutionException e) {
                System.out.println("ExecutionException");
            }
        }).start();
    }

    /**
     * Adds a new chat to the chat list.
     *
     * @param  addChatFunc     a BiConsumer that adds a chat with its id and name to the chat list
     * @param  setCurrentChat  a Consumer that sets the current chat to the specified chat id
     */
    public static void addChat(BiConsumer<String, String> addChatFunc, Consumer<String> setCurrentChat) {
        try {
            Thread thread = new Thread(() -> {
                String newChatName = "New Chat";

                String reqUrl = BASE_URL + "/chats";
                Map body = Map.ofEntries(Map.entry("title", newChatName));

                try {
                    JsonObject respObject = NetworkManager.sendPostRequestAsync(reqUrl, body);
                    String chatId = respObject.get("chatId").getAsString();
                    setCurrentChat.accept(chatId);
                    Platform.runLater(() -> addChatFunc.accept(chatId, newChatName));
                } catch (InterruptedException ex) {
                    System.out.println("InterruptedException");
                } catch (ExecutionException e) {
                    System.out.println("ExecutionException");
                }
            });
            thread.start();
            thread.join();
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException");
        }
    }

    /**
     * Renames the chat with the given chat ID to the specified chat name.
     *
     * @param  chatId    the ID of the chat to be renamed
     * @param  chatName  the new name for the chat
     * @return           None
     */
    public static void renameChat(String chatId, String chatName) {
        new Thread(() -> {
            String reqUrl = String.format("%s/chats/%s", BASE_URL, chatId);
            Map body = Map.ofEntries(Map.entry("title", chatName));

            try {
                NetworkManager.sendPutRequestAsync(reqUrl, body);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            } catch (ExecutionException e) {
                System.out.println("ExecutionException");
            }
        }).start();
    }

    /**
     * Deletes a chat with the given chat ID.
     *
     * @param  chatId  the ID of the chat to be deleted
     */
    public static void deleteChat(String chatId) {
        new Thread(() -> {
            String reqUrl = String.format("%s/chats/%s", BASE_URL, chatId);

            try {
                NetworkManager.sendDeleteRequestAsync(reqUrl);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            } catch (ExecutionException e) {
                System.out.println("ExecutionException");
            }
        }).start();
    }

    /**
     * Retrieves messages from a chat and adds them using the add message function.
     *
     * @param  addMessageFunc  the function used to add messages
     * @param  chatId          the ID of the chat
     */
    public static void getMessages(Consumer<Message> addMessageFunc, String chatId) {
        new Thread(() -> {
            String reqUrl = String.format("%s/chats/%s/messages", BASE_URL, chatId);

            try {
                JsonObject respObject = NetworkManager.sendGetRequestAsync(reqUrl);
                for (JsonElement responseJsonElement : respObject.getAsJsonArray("messages")) {
                    JsonObject responseJsonObject = responseJsonElement.getAsJsonObject();
                    String createdAtString = responseJsonObject.getAsJsonPrimitive("createdAt").getAsString();

                    Message message = new Message(
                        responseJsonObject.get("content").getAsString(),
                        responseJsonObject.get("senderBot").getAsBoolean(),
                        LocalDateTime.parse(createdAtString, DateTimeFormatter.ISO_DATE_TIME));

                    Platform.runLater(() -> addMessageFunc.accept(message));
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            } catch (ExecutionException e) {
                System.out.println("ExecutionException");
            }
        }).start();
    }

    /**
     * Sends a message to a chat.
     *
     * @param  addMessageFunc  the function to add the message to the chat
     * @param  chatId          the ID of the chat to send the message to
     * @param  messageString   the content of the message to send
     */
    public static void sendMessage(BiConsumer<String, Boolean> addMessageFunc, String chatId, String messageString) {
        new Thread(() -> {
            String reqUrl = String.format("%s/chats/%s/chat", BASE_URL, chatId);
            Map body = Map.ofEntries(Map.entry("content", messageString));

            try {
                JsonObject respObject = NetworkManager.sendPostRequestAsync(reqUrl, body);
                if (!respObject.get("success").getAsBoolean()) {
                    Platform.runLater(() -> addMessageFunc.accept("An error occured!", true));
                } else {
                    String msg = respObject.get("response").getAsString();
                    Platform.runLater(() -> addMessageFunc.accept(msg, true));
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            } catch (ExecutionException e) {
                System.out.println("ExecutionException");
            }
        }).start();
    }
}
