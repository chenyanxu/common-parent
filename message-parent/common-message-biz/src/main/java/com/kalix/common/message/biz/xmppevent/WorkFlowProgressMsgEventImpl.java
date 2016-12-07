package com.kalix.common.message.biz.xmppevent;

import com.kalix.common.message.api.BaseXMPPEvent;
import com.kalix.common.message.biz.util.MessageUtil;
import org.osgi.service.event.Event;

import java.util.Map;

/**
 * 工作流中的消息进行监听处理类,负责把工作流的进度发送给启动者。
 * Created by houqj on 2016/12/06.
 */
public class WorkFlowProgressMsgEventImpl extends BaseXMPPEvent {

    @Override
    public void handleEvent(Event event) {

        Map<Long, String> contents = MessageUtil.getWorkFlowProgressMessage(event);
        sendMessage(contents);
    }

    protected Map<Long, String> getMessages(Event event) {
        return  MessageUtil.getWorkFlowProgressMessage(event);
    }
}
