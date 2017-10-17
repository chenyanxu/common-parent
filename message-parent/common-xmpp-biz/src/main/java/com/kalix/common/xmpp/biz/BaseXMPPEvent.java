package com.kalix.common.xmpp.biz;

import com.kalix.admin.core.entities.UserBean;
import com.kalix.common.message.api.BaseMessageEvent;
import com.kalix.middleware.xmpp.api.biz.IXMPPService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by houqj on 2016/12/06.
 * 消息处理抽象类
 */
public abstract class BaseXMPPEvent extends BaseMessageEvent {

    protected IXMPPService xmppService;

    protected void sendMessage(Map<Long, String> contents, String msgTitle, String constLabel, boolean isSave){
        sendMessage(contents);
    };

    protected void sendMessage(Map<Long, String> contents) {

        if (contents != null && !contents.isEmpty()) {
            for (Long key : contents.keySet()) {
                String content = contents.get(key);

                if (!content.isEmpty()) {
                    List<String> receivers = getReceiverLoginName(key);
                    xmppService.sendMessage(receivers, content);
                }
            }
        }
    }

    protected List<String> getReceiverLoginName(Long userId) {
        List<String> receivers = new ArrayList<String>();
        UserBean userBean = userBeanService.getEntity(userId);
        receivers.add(userBean.getLoginName());
        return receivers;
    }

    public void setXmppService(IXMPPService xmppService) {
        this.xmppService = xmppService;
    }
}
