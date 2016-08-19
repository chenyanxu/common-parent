package com.kalix.admin.message.api.biz;


import com.kalix.admin.message.entities.SenderMessageBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;

/**
 * @类描述：应用服务接口.
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public interface ISenderMessageBeanService extends IBizService<SenderMessageBean> {
    //在此添加新的业务方法
    JsonData getSenderMessage(int page, int limit, String jsonStr);

    JsonStatus saveAllEntities(SenderMessageBean senderMessageBean);
}
