package com.kalix.common.message.biz.servlet;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/6/4.
 */
public class Client {
    public static void main(String[] args) {
        String destUri = "ws://localhost:8181/websocket";
        if (args.length > 0) {
            destUri = args[0];
        }

        WebSocketClient client = new WebSocketClient();
//        KarafWebSocket socket = new KarafWebSocket();
        try {
            client.start();

            URI echoUri = new URI(destUri);
            final WebSocketAdapter socket = new WebSocketAdapter() {
                @Override
                public void onWebSocketConnect(Session session) {
                    session.getRemote().sendStringByFuture("yo man!");

                    session.close();
                }
            };
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(socket, echoUri, request);
            System.out.printf("Connecting to : %s%n", echoUri);

            // wait for closed socket connection.

        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                client.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
