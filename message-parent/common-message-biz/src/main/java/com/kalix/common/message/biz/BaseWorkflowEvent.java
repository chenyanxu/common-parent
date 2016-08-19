package com.kalix.common.message.biz;


import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.common.message.api.dao.IMessageBeanDao;
import com.kalix.common.message.entities.MessageBean;
import com.kalix.framework.core.api.system.IStackService;

/**
 * Created by sunlf on 2016/2/26.
 * 工作流消息处理抽象类
 */
public abstract class BaseWorkflowEvent {
    protected IMessageBeanDao dao;
    protected IStackService stackService;
    protected IUserBeanService userBeanService;
    protected static final int day = 24 * 60 * 60 * 1000;
    protected static final int ADMIN_USER_ID = 5601;
    protected static final String ADMIN_USER_NAME = "系统管理员";

    protected MessageBean saveMessageBean(long receiverId, String content, String title) {
        MessageBean messageBean = new MessageBean();
        messageBean.setSenderId(ADMIN_USER_ID);
        messageBean.setSenderName(ADMIN_USER_NAME);
        messageBean.setReceiverId(receiverId);
        messageBean.setCategory(1);//1、系统消息；2、建议；3、警告；4、分配；
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

    public void setUserBeanService(IUserBeanService userBeanService) {
        this.userBeanService = userBeanService;
    }
}
