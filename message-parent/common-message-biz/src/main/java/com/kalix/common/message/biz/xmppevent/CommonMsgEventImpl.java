package com.kalix.common.message.biz.xmppevent;

import com.kalix.common.message.api.BaseXMPPEvent;
import com.kalix.common.message.biz.util.MessageUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import java.util.List;

/**
 * 工作流中的消息进行监听处理类,负责把工作流的进度发送给启动者。
 * Created by houqj on 2016/12/06.
 */
public class CommonMsgEventImpl extends BaseXMPPEvent implements EventHandler {

    @Override
    public void handleEvent(Event event) {

        Long userId = (Long) event.getProperty("userId");
        String content = MessageUtil.getCommonMessage(event);

        sendMessage(userId, content);
    }
}
