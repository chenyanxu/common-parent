package com.kalix.common.message.biz.servlet;

import org.eclipse.jetty.websocket.api.Session;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/6/4.
 */
public class KarafWebSocketActivator {
    public static final long WEBSOCKET_TIMEOUT = -1;
    private static final Set<Session> clientSessions = Collections.synchronizedSet(new HashSet<Session>());

    public static void registerConnection(Session session) {
        session.setIdleTimeout(KarafWebSocketActivator.WEBSOCKET_TIMEOUT);
        clientSessions.add(session);

    }

    public static void unregisterConnection(Session session) {
        clientSessions.remove(session);
    }

    public static void onMessage(String message, Session client) {
//        hazelcastTopic.publish(message);
    }

}
