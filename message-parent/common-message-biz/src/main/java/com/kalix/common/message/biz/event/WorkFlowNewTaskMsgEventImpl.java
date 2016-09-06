package com.kalix.common.message.biz.event;


import com.google.gson.Gson;
import com.kalix.admin.core.api.biz.IRoleBeanService;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.common.message.api.BaseMessageEvent;
import com.kalix.common.message.api.Const;
import com.kalix.common.message.api.dao.IMessageBeanDao;
import com.kalix.common.message.entities.MessageBean;
import org.activiti.engine.impl.util.json.JSONObject;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import java.util.List;


/**
 * 工作流中的消息进行监听处理类
 * Created by zangyanming on 2016/2/23.
 */
public class WorkFlowNewTaskMsgEventImpl extends BaseMessageEvent implements EventHandler {
    private final static String MSG_CONTENT = "%s,您好！\r\n  您有一个待办流程请尽快处理！流程号为[%s]。";
    private final static String MSG_TITLE = "待办流程提醒";

    IMessageBeanDao dao;
    IRoleBeanService roleBeanService;

    @Override
    public void handleEvent(Event event) {
        String json = (String) event.getProperty("body");
        Gson gson = new Gson();
        JSONObject taskJson = new JSONObject(json);
        String businessKey = (String) taskJson.get("businessKey");
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
            String content = String.format(MSG_CONTENT, userName, businessKey);
            MessageBean messageBean = createMessageBean(userBean.getId(), content, MSG_TITLE);
            dao.save(messageBean);
            stackService.publish(String.format(Const.POLLING_MESSAGE_TOPIC_FORMAT, userBean.getId()), gson.toJson(messageBean), day);

        }
            //获得用户名

    }

    public void setDao(IMessageBeanDao dao) {
        this.dao = dao;
    }

    public void setRoleBeanService(IRoleBeanService roleBeanService) {
        this.roleBeanService = roleBeanService;
    }
}
