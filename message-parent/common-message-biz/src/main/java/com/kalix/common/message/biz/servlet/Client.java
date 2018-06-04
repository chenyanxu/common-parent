package com.kalix.common.message.biz.servlet;

import org.eclipse.jetty.util.component.LifeCycle;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

/**
 * Created by Administrator on 2018/6/4.
 */
public class Client {
    public static void main(String[] args) {
//        String destUri = "ws://localhost:8181/websocket";
        URI uri = URI.create("ws://localhost:8181/websocket");

        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();

            try {
                // Attempt Connect
                Session session = container.connectToServer(KarafWebSocket.class, uri);
                // Send a message
                session.getBasicRemote().sendText("Hello");
                // Close session
                session.close();
            } finally {
                // Force lifecycle stop when done with container.
                // This is to free up threads and resources that the
                // JSR-356 container allocates. But unfortunately
                // the JSR-356 spec does not handle lifecycles (yet)
                if (container instanceof LifeCycle) {
                    ((LifeCycle) container).stop();
                }
            }
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }

    }
}
