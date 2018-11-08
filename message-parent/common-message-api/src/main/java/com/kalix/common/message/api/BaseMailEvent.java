package com.kalix.common.message.api;

import com.google.gson.Gson;
import com.kalix.admin.duty.api.biz.IDutyBeanService;
import com.kalix.common.message.api.dao.IMessageBeanDao;
import com.kalix.common.message.entities.MessageBean;
import com.kalix.framework.core.api.system.IStackService;

import java.util.Date;
import java.util.Map;

/**
 * Created by sunlf on 2016/2/26.
 * 消息处理抽象类
 */
public abstract class BaseMailEvent extends BaseMessageEvent {

    protected IMessageBeanDao dao;
    protected IStackService stackService;
    protected IDutyBeanService dutyBeanService;
    //消息在stack保留的时间
    protected static final int day = 24 * 60 * 60 * 1000;
    protected static final int ADMIN_USER_ID = 1;
    protected static final String ADMIN_USER_NAME = "管理员";

    protected void sendMessage(Map<String, String> contents, String msgTitle, String constLabel, boolean isSave) {
        if (contents != null && !contents.isEmpty()) {
            for (String key : contents.keySet()) {
                String content = contents.get(key);

                MessageBean messageBean = createMessageBean(key, content, msgTitle);
                if (isSave) {
                    dao.save(messageBean);
                }
                //add msg to stack
                Gson gson = new Gson();
                stackService.publish(String.format(constLabel, String.valueOf(key)), gson.toJson(messageBean), day);
            }
        }
    }

    protected MessageBean createMessageBean(String receiverId, String content, String title) {
        MessageBean messageBean = new MessageBean();
        messageBean.setCreationDate(new Date());
        messageBean.setSenderId(String.valueOf(ADMIN_USER_ID));
        messageBean.setSenderName(ADMIN_USER_NAME);
        messageBean.setReceiverId(receiverId);
        messageBean.setCategory(MessageCategories.SYSTEM.getId());//0 系统消息,1 流程消息， 2 个人消息,3 计划任务消息
        messageBean.setTitle(title);
        messageBean.setContent(content);
        messageBean.setRead(false);//未读的消息
        messageBean.setState(1);//未通知
        messageBean.setSign(0);
        return messageBean;
    }

    public void setDao(IMessageBeanDao dao) {
        this.dao = dao;
    }

    public void setStackService(IStackService stackService) {
        this.stackService = stackService;
    }

    public void setDutyBeanService(IDutyBeanService dutyBeanService) {
        this.dutyBeanService = dutyBeanService;
    }
}
