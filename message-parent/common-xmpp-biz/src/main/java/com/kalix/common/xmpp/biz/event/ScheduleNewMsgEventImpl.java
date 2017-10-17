package com.kalix.common.xmpp.biz.event;

import com.kalix.common.xmpp.biz.BaseXMPPEvent;
import com.kalix.common.message.api.util.MessageUtil;
import org.osgi.service.event.Event;

import java.util.Map;

/**
 * 计划任务中的消息进行监听处理类,负责把新建的任务发送给任务的执行人。
 * Created by houqj on 2016/12/06.
 */
public class ScheduleNewMsgEventImpl extends BaseXMPPEvent {

    @Override
    public void handleEvent(Event event) {

        Map<Long, String> contents = getMessage(event);
        sendMessage(contents);
    }

    protected Map<Long, String> getMessage(Event event) {
        return MessageUtil.getScheduleNewMessage(event);
    }
}
