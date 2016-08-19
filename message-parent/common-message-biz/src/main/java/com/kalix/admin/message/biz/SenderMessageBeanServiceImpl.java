package com.kalix.admin.message.biz;


import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.admin.message.api.biz.IMessageBeanService;
import com.kalix.admin.message.api.biz.ISenderMessageBeanService;
import com.kalix.admin.message.api.dao.ISenderMessageBeanDao;
import com.kalix.admin.message.entities.MessageBean;
import com.kalix.admin.message.entities.SenderMessageBean;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;

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
        long senderId = userBean.getId();
        try {
            jsonStatus.setSuccess(true);
            senderMessageBean.setSenderId(senderId);
            receiverIds = receiverIds.replaceAll(",", ":");
            receiverIds = receiverIds.replaceAll(";", ":");
            String[] ids = receiverIds.split(":");
            for (int i = 0; i < ids.length; i++) {
                MessageBean newMessageBean = new MessageBean();
                newMessageBean.setSenderId(senderId);
                newMessageBean.setSenderName(userBean.getName());
                newMessageBean.setReceiverId(Long.parseLong(ids[i]));
                newMessageBean.setCategory(1);//1 系统消息,2 建议,3 警告,4分配
                newMessageBean.setTitle(senderMessageBean.getTitle());
                newMessageBean.setContent(senderMessageBean.getContent());
                newMessageBean.setRead(false);
                newMessageBean.setState(0);
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
