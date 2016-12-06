package com.kalix.common.message.biz.event;

import com.google.gson.Gson;
import com.kalix.common.message.api.BaseMailEvent;
import com.kalix.common.message.api.Const;
import com.kalix.common.message.biz.util.MessageUtil;
import com.kalix.common.message.entities.MessageBean;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import java.util.Map;

/**
 * 工作流中的消息进行监听处理类
 * Created by zangyanming on 2016/2/23.
 */
public class WorkFlowNewTaskMsgEventImpl extends BaseMailEvent implements EventHandler {
    //private final static String MSG_CONTENT = "%s,您好！\r\n  您有一个待办流程请尽快处理！流程号为[%s]。";
    private final static String MSG_TITLE = "待办流程提醒";

    @Override
    public void handleEvent(Event event) {
        /*String json = (String) event.getProperty("body");
        Gson gson = new Gson();
        JSONObject taskJson = new JSONObject(json);
        String businessNo = (String) taskJson.get("businessNo");
        //查找组对应的用户id
        String groupName = (String) taskJson.get("group");
        //解析group，orgName+“-”+职位
        String str[] = groupName.split("-");
        String orgName = str[0];
        String dutyName = str[1];
        List<String> loginUserList = dutyBeanService.getUserListByOrgName(orgName, dutyName);
        for (String userloginName : loginUserList) {
            UserBean userBean = userBeanService.getUserBeanByLoginName(userloginName);
            String userName = userBean.getName();
            String content = String.format(MSG_CONTENT, userName, businessNo);
            MessageBean messageBean = createMessageBean(userBean.getId(), content, MSG_TITLE);
            dao.save(messageBean);
            stackService.publish(String.format(Const.POLLING_MESSAGE_TOPIC_FORMAT, userBean.getId()), gson.toJson(messageBean), day);

        }
            //获得用户名*/

        Map<Long, String> contents = MessageUtil.getWorkFlowNewTaskMessage(event);
        if (contents != null && !contents.isEmpty()) {
            for (Long key : contents.keySet()) {
                String content = contents.get(key);

                MessageBean messageBean = createMessageBean(key, content, MSG_TITLE);
                dao.save(messageBean);
                //add msg to stack
                Gson gson = new Gson();
                stackService.publish(String.format(Const.POLLING_MESSAGE_TOPIC_FORMAT, String.valueOf(key)), gson.toJson(messageBean), day);
            }
        }
    }
}
