package com.kalix.common.message.dao;

import com.kalix.common.message.api.dao.ISenderMessageBeanDao;
import com.kalix.common.message.entities.SenderMessageBean;
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
public class SenderMessageBeanDaoImpl extends GenericDao<SenderMessageBean, Long> implements ISenderMessageBeanDao {
    @Override
    @PersistenceContext(unitName = "message-cm")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
    //todo add custom query
}
