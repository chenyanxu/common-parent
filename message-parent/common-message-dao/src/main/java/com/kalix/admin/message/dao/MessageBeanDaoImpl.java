package com.kalix.admin.message.dao;

import com.kalix.framework.core.impl.dao.GenericDao;
import com.kalix.admin.message.api.dao.IMessageBeanDao;
import com.kalix.admin.message.entities.MessageBean;

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
public class MessageBeanDaoImpl extends GenericDao<MessageBean, Long> implements IMessageBeanDao {
    @Override
    @PersistenceContext(unitName = "message-cm")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
