package com.kalix.common.message.biz;


import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.common.message.api.Const;
import com.kalix.common.message.api.biz.IMessageBeanService;
import com.kalix.common.message.api.biz.ISenderMessageBeanService;
import com.kalix.common.message.api.dao.IMessageBeanDao;
import com.kalix.common.message.entities.MessageBean;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.system.IStackService;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.impl.dao.CommonMethod;
import com.kalix.framework.core.util.StringUtils;
import com.kalix.middleware.websocket.api.biz.IWebsocketService;
import org.json.JSONObject;

import java.util.List;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class MessageBeanServiceImpl extends ShiroGenericBizServiceImpl<IMessageBeanDao, MessageBean> implements IMessageBeanService, IWebsocketService {
    private IUserBeanService userBeanService;
    private IStackService stackService;
    private ISenderMessageBeanService senderBeanService;

    public void setSenderBeanService(ISenderMessageBeanService senderBeanService) {
        this.senderBeanService = senderBeanService;
    }

    public MessageBeanServiceImpl() {
        super.init(MessageBean.class.getName());
    }

    @Override
    public JsonStatus getNewMessageCount() {
        JsonStatus jsonStatus = new JsonStatus();
        jsonStatus.setSuccess(true);
        String loginName = this.getShiroService().getSubject().getPrincipal().toString();
        UserBean userBean = userBeanService.getUserBeanByLoginName(loginName);
        List countList = this.dao.find("select mb from MessageBean mb where mb.receiverId=?1 and mb.read=false", userBean.getId());
        if (countList != null) {
            jsonStatus.setTag(String.valueOf(countList.size()));
        } else {
            jsonStatus.setTag("0");
        }

        return jsonStatus;
    }

    @Override
    public JsonData getReceiverMessage(int page, int limit, String jsonStr) {
        String loginName = this.getShiroService().getSubject().getPrincipal().toString();
        UserBean userBean = userBeanService.getUserBeanByLoginName(loginName);
        String userId = String.valueOf(userBean.getId());
        if (jsonStr.equals("") || jsonStr == null) {
            jsonStr = "{\"receiverId\":\"" + userId + "\"}";
        } else {
            jsonStr = jsonStr.replace("}", ",\"receiverId\":\"" + userId + "\"}");
        }
        /*
        // 按照read进行排序
        return super.getAllEntityByQuery(page, limit, jsonStr,"[{\"property\":\"read\",\"direction\":\"ASC\"}]");
        */
        // 按照read进行排序，再按照creationdate排序
        String condition = CommonMethod.createWhereCondition(jsonStr);
        String sql = "select t.* " +
                " from (select a.* from " + this.dao.getTableName() + " a order by a.read,a.creationdate desc) t";
        if (StringUtils.isNotEmpty(condition)) {
            sql += " where " + condition;
        }
        JsonData jsonData = this.dao.findByNativeSql(sql, page, limit, MessageBean.class);
        return jsonData;
    }

    @Override
    public JsonStatus getPollingMessage() {
        JsonStatus jsonStatus = new JsonStatus();
        try {
            jsonStatus.setSuccess(true);
            String loginName = this.getShiroService().getSubject().getPrincipal().toString();
            UserBean userBean = userBeanService.getUserBeanByLoginName(loginName);
            //轮询系统消息
            String topic = "";
            if (userBean != null)
                topic = String.format(Const.POLLING_MESSAGE_TOPIC_FORMAT, String.valueOf(userBean.getId()));
            jsonStatus.setTag(stackService.consume(topic));
            return jsonStatus;
        } catch (Exception e) {
            e.printStackTrace();
            jsonStatus.setSuccess(false);
            jsonStatus.setTag("");
            return jsonStatus;
        }
    }

    private void setJsonStatus(JsonStatus jsonStatus, String tag, String msg, Boolean isSuccess) {
        jsonStatus.setTag(tag);
        jsonStatus.setMsg(msg);
        jsonStatus.setSuccess(isSuccess);
    }

    public void setUserBeanService(IUserBeanService userBeanService) {
        this.userBeanService = userBeanService;
    }

    public void setStackService(IStackService stackService) {
        this.stackService = stackService;
    }

    @Override
    public JSONObject getData(String key) {
        JSONObject jsonObject = new JSONObject();
        String loginName = key;
        UserBean userBean = userBeanService.getUserBeanByLoginName(loginName);
        List countList = null;
        if(userBean != null) {
            countList = this.dao.find("select mb from MessageBean mb where mb.receiverId=?1 and mb.read=false", userBean.getId());
        }
        if (countList != null) {
            jsonObject.put("msgCount", countList.size());
        }
        //轮询系统消息
        String topic = "";
        if (userBean != null)
            topic = String.format(Const.POLLING_MESSAGE_TOPIC_FORMAT, String.valueOf(userBean.getId()));
        String tag = stackService.consume(topic);
        jsonObject.put("msgTag", tag);
        return jsonObject;
    }
}
