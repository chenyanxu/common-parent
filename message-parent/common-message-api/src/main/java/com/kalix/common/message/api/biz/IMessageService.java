package com.kalix.common.message.api.biz;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * Created by Administrator on 2016/12/5.
 */
public interface IMessageService extends EventHandler {

    static final String MSG_CONTENT_COMMON = "%s,您好！\r\n  您收到了一条新信息，请查看！";
    static final String MSG_CONTENT_SCHEDULE_NEW = "%s,您好！\r\n  您收到了一条由%s布置的新任务，任务名称为:%s，请查看！";
    static final String MSG_CONTENT_SCHEDULE_CHANGE = "%s,您好！\r\n  您布置的任务[%s]状态已经由[%s]修改,状态从[%s]修改为:[%s]，请查看！";
    static final String MSG_CONTENT_SCHEDULE_SUPERVISE = "%s,您好！\r\n  您收到了一条由%s督办的消息，任务名称为:%s，%s！";
    static final String MSG_CONTENT_WORKFLOW_NEW = "%s,您好！\r\n  您有一个待办流程请尽快处理！流程号为[%s]。";
    static final String MSG_CONTENT_WORKFLOW_PROGRESS = "%s,您好！\r\n  您流程编号为[%s]的申请已经审批，请查看！";

    static enum MessageType {
        COMMON,
        SCHEDULE_NEW,
        SCHEDULE_CHANGE,
        SCHEDULE_SUPERVISE,
        WORKFLOW_NEW,
        WORKFLOW_PROGRESS
    }

    static final MessageType defaultMessageType = MessageType.COMMON;

    void setMessageType(MessageType messageType);

    public String processMessage(Event event);
}
