package com.kalix.common.address.dao;

import com.kalix.common.address.api.dao.IAddressBeanDao;
import com.kalix.common.address.entities.AddressBean;
import com.kalix.framework.core.impl.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AddressBeanDaoImpl extends GenericDao<AddressBean, Long> implements IAddressBeanDao {
    private String uuid;
    public AddressBeanDaoImpl() {
        uuid = UUID.randomUUID().toString();
    }
    @Override
    @PersistenceContext(unitName = "address-cm")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

}
