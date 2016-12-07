package com.kalix.common.message.biz.xmppevent;

import com.kalix.common.message.api.BaseXMPPEvent;
import com.kalix.common.message.biz.util.MessageUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import java.util.List;

/**
 * 计划任务中督办的消息进行监听处理类,负责把督办的任务发送给任务的执行人。
 * Created by houqj on 2016/12/06.
 */
public class ScheduleSuperviseMsgEventImpl extends BaseXMPPEvent implements EventHandler {

    @Override
    public void handleEvent(Event event) {

        //负责人
        Long head = (Long) event.getProperty("head");
        String content = MessageUtil.getScheduleSuperviseMessage(event);

        sendMessage(head, content);
    }
}
