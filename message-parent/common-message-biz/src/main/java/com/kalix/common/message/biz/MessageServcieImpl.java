package com.kalix.common.message.biz;

import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.common.message.api.biz.IMessageService;
import com.kalix.middleware.xmpp.api.biz.IXmppMsgService;
import org.osgi.service.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */
public abstract class MessageServcieImpl implements IMessageService {

    protected MessageType messageType = defaultMessageType;

    protected IUserBeanService userBeanService;
    protected IXmppMsgService xmppMsgService;

    @Override
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public String processMessage(Event event) {
        String content = "";
        if (messageType.equals(MessageType.COMMON)) {
            content = "";
        }
        else if (messageType.equals(MessageType.SCHEDULE_NEW)) {
            //布置人
            String userName = (String) event.getProperty("userName");
            //任务名称
            String taskName = (String) event.getProperty("taskName");
            //负责人
            Long head = (Long) event.getProperty("head");
            //参与人
            String participant = (String) event.getProperty("participant");

            //根据用户id获取实体，进而获取到用户名称
            UserBean userBean = userBeanService.getEntity(head);
            String headName = userBean.getName();
            content = String.format(MSG_CONTENT_SCHEDULE_NEW, headName, userName, taskName);
        }
        else if (messageType.equals(MessageType.SCHEDULE_CHANGE)) {
            content = "";
        }
        else if (messageType.equals(MessageType.SCHEDULE_SUPERVISE)) {
            content = "";
        }
        else if (messageType.equals(MessageType.WORKFLOW_NEW)) {
            content = "";
        }
        else if (messageType.equals(MessageType.WORKFLOW_PROGRESS)) {
            content = "";
        }
        else {
            content = "";
        }
        return content;
    }

    @Override
    public void handleEvent(Event event) {
        String sendMsg = processMessage(event);
        if (!sendMsg.isEmpty()) {
            Long head = (Long) event.getProperty("head");
            UserBean userBean = userBeanService.getEntity(head);
            List<String> toUsers = new ArrayList<String>();
            toUsers.add(String.valueOf(userBean.getLoginName()));
            xmppMsgService.sendMessage(toUsers,sendMsg);
        }
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setUserBeanService(IUserBeanService userBeanService) {
        this.userBeanService = userBeanService;
    }

    public void setXmppMsgService(IXmppMsgService xmppMsgService) {
        this.xmppMsgService = xmppMsgService;
    }
}
