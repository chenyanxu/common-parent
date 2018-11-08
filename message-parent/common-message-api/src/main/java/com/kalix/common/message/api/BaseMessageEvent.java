package com.kalix.common.message.api;

import com.kalix.admin.core.api.biz.IUserBeanService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import java.util.Map;

/**
 * Created by sunlf on 2016/2/26.
 * 消息处理抽象类
 */
public abstract class BaseMessageEvent implements EventHandler {

    protected IUserBeanService userBeanService;

    protected Map<String, String> getMessage(Event event) { return null; }

    protected void sendMessage(Map<String, String> contents, String msgTitle, String constLabel, boolean isSave){ };

    public void setUserBeanService(IUserBeanService userBeanService) {
        this.userBeanService = userBeanService;
    }
}
