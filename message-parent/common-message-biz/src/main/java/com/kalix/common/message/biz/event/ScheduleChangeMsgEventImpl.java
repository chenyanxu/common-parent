package com.kalix.common.message.biz.event;

import com.kalix.common.message.api.BaseMailEvent;
import com.kalix.common.message.api.Const;
import com.kalix.common.message.api.util.MessageUtil;
import org.osgi.service.event.Event;

import java.util.Map;

/**
 * 计划任务中的消息进行监听处理类,负责把任务的状态修改发送给任务的布置人。
 * Created by sunlf on 2016/8/26.
 */
public class ScheduleChangeMsgEventImpl extends BaseMailEvent {

    //public static final String MSG_CONTENT = "%s,您好！\r\n  您布置的任务[%s]状态已经由[%s]修改,状态从[%s]修改为:[%s]，请查看！";
    public static final String MSG_TITLE = "计划任务状态修改提醒";

    @Override
    public void handleEvent(Event event) {
        /*//布置人ID
        Long userId = (Long) event.getProperty("userId");
        //布置人
        String userName = (String) event.getProperty("userName");
        //任务名称
        String taskName = (String) event.getProperty("taskName");
        //负责人
        Long head = (Long) event.getProperty("head");
        UserBean headUserBean = userBeanService.getEntity(head);
        String headName = headUserBean.getName();
        //任务状态
        String state = (String) event.getProperty("state");
        //任务旧状态
        String oldState = (String) event.getProperty("oldState");

        //格式化向布置人发送消息
        String content = String.format(MSG_CONTENT, userName, taskName, headName, oldState, state);

        MessageBean messageBean = createMessageBean(userId, content, MSG_TITLE);
        dao.save(messageBean);
        //add msg to stack
        Gson gson = new Gson();
        //向布置人发送消息
        stackService.publish(String.format(Const.POLLING_MESSAGE_TOPIC_FORMAT,
                String.valueOf(userId)), gson.toJson(messageBean), day);*/

        Map<String, String> contents = getMessage(event);
        //向布置人发送消息
        sendMessage(contents, MSG_TITLE, Const.POLLING_MESSAGE_TOPIC_FORMAT, true);
    }

    protected Map<String, String> getMessage(Event event) {
        return MessageUtil.getScheduleChangeMessage(event);
    }
}
