package com.kalix.common.message.biz.xmppevent;

import com.kalix.common.message.api.BaseXMPPEvent;
import com.kalix.common.message.biz.util.MessageUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import java.util.List;

/**
 * 计划任务中的消息进行监听处理类,负责把任务的状态修改发送给任务的布置人。
 * Created by houqj on 2016/12/06.
 */
public class ScheduleChangeMsgEventImpl extends BaseXMPPEvent implements EventHandler {

    @Override
    public void handleEvent(Event event) {

        //布置人ID
        Long userId = (Long) event.getProperty("userId");
        String content = MessageUtil.getScheduleChangeMessage(event);

        //向布置人发送消息
        sendMessage(userId, content);
    }
}
