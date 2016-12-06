package com.kalix.common.message.biz.xmppevent;

import com.kalix.common.message.api.BaseXMPPEvent;
import com.kalix.common.message.biz.util.MessageUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import java.util.List;
import java.util.Map;

/**
 * 工作流中的消息进行监听处理类
 * Created by houqj on 2016/12/06.
 */
public class WorkFlowNewTaskMsgEventImpl extends BaseXMPPEvent implements EventHandler {

    @Override
    public void handleEvent(Event event) {

        Map<Long, String> contents = MessageUtil.getWorkFlowNewTaskMessage(event);
        sendMessage(contents);
    }
}
