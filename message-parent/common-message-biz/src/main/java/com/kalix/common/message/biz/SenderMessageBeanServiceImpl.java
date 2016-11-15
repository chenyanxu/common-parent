package com.kalix.common.message.biz;


import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.common.message.api.Const;
import com.kalix.common.message.api.MessageCategories;
import com.kalix.common.message.api.biz.IMessageBeanService;
import com.kalix.common.message.api.biz.ISenderMessageBeanService;
import com.kalix.common.message.api.dao.ISenderMessageBeanDao;
import com.kalix.common.message.entities.MessageBean;
import com.kalix.common.message.entities.SenderMessageBean;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.JNDIHelper;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import java.io.IOException;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class SenderMessageBeanServiceImpl extends ShiroGenericBizServiceImpl<ISenderMessageBeanDao, SenderMessageBean> implements ISenderMessageBeanService {
    private IUserBeanService userBeanService;
    private IMessageBeanService messageBeanService;
    private EventAdmin eventAdmin;

    public SenderMessageBeanServiceImpl() {
        super.init(SenderMessageBean.class.getName());
    }

    @Override
    public JsonData getSenderMessage(int page, int limit, String jsonStr) {
        String loginName = this.getShiroService().getSubject().getPrincipal().toString();
        UserBean userBean = userBeanService.getUserBeanByLoginName(loginName);
        String userId = String.valueOf(userBean.getId());
        if (jsonStr == null) {
            jsonStr = "{\"senderId\":\"" + userId + "\"}";
        } else {
            jsonStr = jsonStr.replace("}", ",\"senderId\":\"" + userId + "\"}");
        }
        return super.getAllEntityByQuery(page, limit, jsonStr);
    }

    @Override
    public JsonStatus saveAllEntities(SenderMessageBean senderMessageBean) {
        JsonStatus jsonStatus = new JsonStatus();
        String receiverIds = senderMessageBean.getReceiverIds();
        String receiverNames = senderMessageBean.getReceiverNames();
        String loginName = this.getShiroService().getSubject().getPrincipal().toString();
        UserBean userBean = userBeanService.getUserBeanByLoginName(loginName);
        Long senderId = userBean.getId();
        try {
            jsonStatus.setSuccess(true);
            senderMessageBean.setSenderId(senderId);
            receiverIds = receiverIds.replaceAll(",", ":");
            receiverIds = receiverIds.replaceAll(";", ":");
            String[] ids = receiverIds.split(":");
            for (int i = 0; i < ids.length; i++) {
                MessageBean newMessageBean = new MessageBean();
                newMessageBean.setCreationDate(new Date());
                newMessageBean.setSenderId(senderId);
                newMessageBean.setSenderName(userBean.getName());
                newMessageBean.setReceiverId(Long.parseLong(ids[i]));
                newMessageBean.setCategory(MessageCategories.COMMON.getId());//0 系统消息,1 流程消息， 2 个人消息,3 计划任务消息
                newMessageBean.setTitle(senderMessageBean.getTitle());
                newMessageBean.setContent(senderMessageBean.getContent());
                newMessageBean.setRead(false);
                newMessageBean.setState(0);
                //发送消息通知
                postCommonEvent(newMessageBean);
                // 保存收件信息
                messageBeanService.saveEntity(newMessageBean);
            }
            // 保存发件信息
            saveEntity(senderMessageBean);
            jsonStatus.setTag("");
            jsonStatus.setMsg("发件成功");
            return jsonStatus;
        } catch (Exception e) {
            e.printStackTrace();
            jsonStatus.setSuccess(false);
            jsonStatus.setTag("SenderMessageBeanServiceImpl.java:saveAllEntities异常");
            jsonStatus.setMsg("发件失败");
            return jsonStatus;
        }
    }

    /**
     * 发送消息通知
     *
     * @param msgBean
     */
    private void postCommonEvent(MessageBean msgBean) {
        try {
            eventAdmin = JNDIHelper.getJNDIServiceForName("org.osgi.service.event.EventAdmin");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Dictionary properties = new Hashtable();
        properties.put("userId", msgBean.getReceiverId());
        Event osgi_event = new Event(Const.COMMON_MESSAGE_TOPIC, properties);
        System.out.println("User name: " + msgBean.getReceiverId() + " message is sent!");
        eventAdmin.postEvent(osgi_event);
    }

    public IUserBeanService getUserBeanService() {
        return userBeanService;
    }

    public void setUserBeanService(IUserBeanService userBeanService) {
        this.userBeanService = userBeanService;
    }

    public IMessageBeanService getMessageBeanService() {
        return messageBeanService;
    }

    public void setMessageBeanService(IMessageBeanService messageBeanService) {
        this.messageBeanService = messageBeanService;
    }
}
