package com.kalix.common.xmpp.biz.event;

import com.kalix.common.message.api.BaseXMPPEvent;
import com.kalix.common.message.api.util.MessageUtil;
import org.osgi.service.event.Event;

import java.util.Map;

/**
 * 工作流中的消息进行监听处理类
 * Created by houqj on 2016/12/06.
 */
public class WorkFlowNewTaskMsgEventImpl extends BaseXMPPEvent {

    @Override
    public void handleEvent(Event event) {

        Map<Long, String> contents = getMessage(event);
        sendMessage(contents);
    }

    protected Map<Long, String> getMessage(Event event) {
        return MessageUtil.getWorkFlowNewTaskMessage(event);
    }
}
