package com.kalix.common.message.dao;

import com.kalix.common.message.api.dao.IMessageBeanDao;
import com.kalix.common.message.entities.MessageBean;
import com.kalix.framework.core.impl.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class MessageBeanDaoImpl extends GenericDao<MessageBean, String> implements IMessageBeanDao {
    @Override
    @PersistenceContext(unitName = "message-cm")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
