package com.kalix.admin.message.biz.event;


import com.kalix.admin.core.api.biz.IRoleBeanService;
import com.kalix.admin.core.entities.RoleBean;
import com.kalix.admin.core.entities.RoleUserBean;
import com.kalix.admin.message.api.dao.IMessageBeanDao;
import com.kalix.admin.message.biz.Const;
import com.kalix.admin.message.biz.BaseWorkflowEvent;

import com.google.gson.Gson;
import com.kalix.admin.message.entities.MessageBean;
import com.kalix.framework.core.util.Assert;
import org.activiti.engine.impl.util.json.JSONObject;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import java.util.List;


/**
 * 工作流中的消息进行监听处理类
 * Created by zangyanming on 2016/2/23.
 */
public class WorkFlowMessageEventImpl extends BaseWorkflowEvent implements EventHandler {
    private final static String MSG_TITLE = "待办流程提醒";
    private final static String MSG_CONTENT = "%s,您好！\r\n  您有一个待办流程请尽快处理！流程号为[%s]。";

    IMessageBeanDao dao;
    IRoleBeanService roleBeanService;

    @Override
    public void handleEvent(Event event) {
        String json = (String) event.getProperty("body");
        Gson gson = new Gson();
        JSONObject taskJson = new JSONObject(json);
        String businessKey = (String) taskJson.get("businessKey");
        //查找组对应的用户id
        String roleName = (String) taskJson.get("group");
        RoleBean roleBean = roleBeanService.queryByRoleName(roleName);
        Assert.notNull(roleBean);
        List<RoleUserBean> userList = roleBeanService.getUserList(roleBean);
        for (RoleUserBean roleUserBean : userList) {
            //获得用户名
            String userName = userBeanService.getEntity(roleUserBean.getUserId()).getName();
            String content = String.format(MSG_CONTENT, userName, businessKey);
            MessageBean messageBean = saveMessageBean(roleUserBean.getUserId(), content, MSG_TITLE);
            dao.save(messageBean);
            stackService.publish(String.format(Const.POLLING_TOPIC_FORMAT, roleUserBean.getUserId()), gson.toJson(messageBean), day);
        }
    }

    public void setDao(IMessageBeanDao dao) {
        this.dao = dao;
    }

    public void setRoleBeanService(IRoleBeanService roleBeanService) {
        this.roleBeanService = roleBeanService;
    }
}
