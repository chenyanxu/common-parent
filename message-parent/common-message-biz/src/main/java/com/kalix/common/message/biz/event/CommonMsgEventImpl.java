package com.kalix.common.message.biz.event;

import com.kalix.common.message.api.BaseMailEvent;
import com.kalix.common.message.api.Const;
import com.kalix.common.message.biz.util.MessageUtil;
import org.osgi.service.event.Event;

import java.util.Map;

/**
 * 工作流中的消息进行监听处理类,负责把工作流的进度发送给启动者。
 * Created by sunlf on 2016/2/23.
 */
public class CommonMsgEventImpl extends BaseMailEvent {

    //public static final String MSG_CONTENT = "%s,您好！\r\n  您收到了一条新信息，请查看！";
    public static final String MSG_TITLE = "个人新信息提醒";

    @Override
    public void handleEvent(Event event) {

        /*Long userId = (Long) event.getProperty("userId");
        UserBean userBean = userBeanService.getEntity(userId);
        String content = String.format(MSG_CONTENT, userBean.getName());

        MessageBean messageBean = createMessageBean(userBean.getId(), content, MSG_TITLE);
        //临时消息提醒，不需要持久化到数据库
//        dao.save(messageBean);
        //add msg to stack
        Gson gson = new Gson();
        stackService.publish(String.format(Const.POLLING_MESSAGE_TOPIC_FORMAT, String.valueOf(userBean.getId())), gson.toJson(messageBean), day);*/

        Map<Long,String> contents = getMessage(event);
        sendMessage(contents, MSG_TITLE, Const.POLLING_MESSAGE_TOPIC_FORMAT, false);
    }

    protected Map<Long, String> getMessage(Event event) {
        return MessageUtil.getCommonMessage(event);
    }
}
