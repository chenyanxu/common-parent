package com.kalix.common.message.api;


import com.google.gson.Gson;
import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.admin.duty.api.biz.IDutyBeanService;
import com.kalix.common.message.api.dao.IMessageBeanDao;
import com.kalix.common.message.entities.MessageBean;
import com.kalix.framework.core.api.system.IStackService;

import java.util.Date;

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

    protected MessageBean createMessageBean(Long receiverId, String content, String title) {
        MessageBean messageBean = new MessageBean();
        messageBean.setCreationDate(new Date());
        messageBean.setSenderId(Long.valueOf(ADMIN_USER_ID));
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

    protected void sendMessage(Long userId, String content, String msgTitle, String constLabel, boolean isSave) {
        MessageBean messageBean = createMessageBean(userId, content, msgTitle);
        Gson gson = new Gson();
        stackService.publish(String.format(constLabel, String.valueOf(userId)), gson.toJson(messageBean), day);
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
