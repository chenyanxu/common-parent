package com.kalix.common.message.biz.event;

import com.google.gson.Gson;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.common.message.biz.BaseWorkflowEvent;
import com.kalix.common.message.biz.Const;
import com.kalix.common.message.entities.MessageBean;
import org.activiti.engine.impl.util.json.JSONObject;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * 工作流中的消息进行监听处理类,负责把工作流的进度发送给启动者。
 * Created by sunlf on 2016/2/23.
 */
public class WorkFlowStarterEventImpl extends BaseWorkflowEvent implements EventHandler {
    public static final String MSG_CONTENT = "%s,您好！\r\n  您流程编号为[%s]的申请已经审批，请查看！";
    public static final String MSG_TITLE = "流程审批进度提醒";

    @Override
    public void handleEvent(Event event) {
        String json = (String) event.getProperty("body");
        JSONObject taskJson = new JSONObject(json);
        String receiverId = (String) taskJson.get("startUserId");
        UserBean userBean = userBeanService.getUserBeanByLoginName(receiverId);
        String businessKey = (String) taskJson.get("businessKey");
        String content = String.format(MSG_CONTENT, userBean.getName(), businessKey);

        MessageBean messageBean = saveMessageBean(userBean.getId(), content, MSG_TITLE);
        dao.save(messageBean);
        //add msg to stack
        Gson gson = new Gson();
        stackService.publish(String.format(Const.POLLING_TOPIC_FORMAT, String.valueOf(userBean.getId())), gson.toJson(messageBean), day);
    }


}
