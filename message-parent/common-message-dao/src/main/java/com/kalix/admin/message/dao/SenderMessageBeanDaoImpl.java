package com.kalix.admin.message.dao;

import com.kalix.framework.core.impl.dao.GenericDao;
import com.kalix.admin.message.api.dao.ISenderMessageBeanDao;
import com.kalix.admin.message.entities.SenderMessageBean;


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
