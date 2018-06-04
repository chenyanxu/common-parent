package com.kalix.common.message.biz.servlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Created by Administrator on 2018/6/4.
 */
public class KarafWebSocketServlet  extends WebSocketServlet {

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.register(KarafWebSocket.class);
    }
}