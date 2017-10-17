package com.kalix.common.xmpp.biz.event;

import com.kalix.common.message.api.util.MessageUtil;
import com.kalix.common.xmpp.biz.BaseXMPPEvent;
import org.osgi.service.event.Event;

import java.util.Map;

/**
 * 计划任务中的消息进行监听处理类,负责把任务的状态修改发送给任务的布置人。
 * Created by houqj on 2016/12/06.
 */
public class ScheduleChangeMsgEventImpl extends BaseXMPPEvent {

    @Override
    public void handleEvent(Event event) {

        Map<Long, String> contents = getMessage(event);
        //向布置人发送消息
        sendMessage(contents);
    }

    protected Map<Long, String> getMessage(Event event) {
        return MessageUtil.getScheduleChangeMessage(event);
    }
}
