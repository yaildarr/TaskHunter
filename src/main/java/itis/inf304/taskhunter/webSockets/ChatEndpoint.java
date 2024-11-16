package itis.inf304.taskhunter.webSockets;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/api/chat")
public class ChatEndpoint {
    private static Set<Session> clients = new HashSet<>();

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
        System.out.println("Клиент подключен: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Получено сообщение: " + message);
        broadcastMessage("Клиент " + session.getId() + ": " + message);
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
        System.out.println("Клиент отключен: " + session.getId());
    }

    private void broadcastMessage(String message) {
        for (Session client : clients) {
            if (client.isOpen()) {
                client.getAsyncRemote().sendText(message);
            }
        }
    }
}