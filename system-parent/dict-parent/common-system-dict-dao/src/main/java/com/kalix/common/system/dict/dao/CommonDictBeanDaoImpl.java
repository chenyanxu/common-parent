package com.kalix.common.system.dict.dao;

import com.kalix.framework.core.impl.dao.GenericDao;
import com.kalix.common.system.dict.api.dao.ICommonDictBeanDao;
import com.kalix.common.system.dict.entities.CommonDictBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CommonDictBeanDaoImpl extends GenericDao<CommonDictBean, String> implements ICommonDictBeanDao {
    @Override
    @PersistenceContext(unitName = "common-system-dict-unit")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
