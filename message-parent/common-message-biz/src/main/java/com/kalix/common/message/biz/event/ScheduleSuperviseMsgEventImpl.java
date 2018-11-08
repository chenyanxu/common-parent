package com.kalix.common.message.biz.event;

import com.kalix.common.message.api.BaseMailEvent;
import com.kalix.common.message.api.Const;
import com.kalix.common.message.api.util.MessageUtil;
import org.osgi.service.event.Event;

import java.util.Map;

/**
 * 计划任务中督办的消息进行监听处理类,负责把督办的任务发送给任务的执行人。
 * Created by sunlf on 2016/8/26.
 */
public class ScheduleSuperviseMsgEventImpl extends BaseMailEvent {

    //public static final String MSG_CONTENT = "%s,您好！\r\n  您收到了一条由%s督办的消息，任务名称为:%s，%s！";
    public static final String MSG_TITLE = "计划任务督办提醒";

    @Override
    public void handleEvent(Event event) {
        /*//负责人
        Long head = (Long) event.getProperty("head");
        UserBean userBean = userBeanService.getEntity(head);
        String headName = userBean.getName();
        //布置人
        String userName = (String) event.getProperty("userName");
        //任务名称
        String taskName = (String) event.getProperty("taskName");
        String info = (String)event.getProperty("info");
        String content = String.format(MSG_CONTENT, headName, userName, taskName,info);

        MessageBean messageBean = createMessageBean(userBean.getId(), content, MSG_TITLE);
        dao.save(messageBean);
        //add msg to stack
        Gson gson = new Gson();
        stackService.publish(String.format(Const.POLLING_MESSAGE_TOPIC_FORMAT, String.valueOf(userBean.getId())), gson.toJson(messageBean), day);*/

        Map<String, String> contents = getMessage(event);
        sendMessage(contents, MSG_TITLE, Const.POLLING_MESSAGE_TOPIC_FORMAT, true);
    }

    protected Map<String, String> getMessage(Event event) {
        return  MessageUtil.getScheduleSuperviseMessage(event);
    }
}
