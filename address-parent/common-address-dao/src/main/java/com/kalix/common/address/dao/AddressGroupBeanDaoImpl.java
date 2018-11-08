package com.kalix.common.address.dao;

import com.kalix.common.address.api.dao.IAddressGroupBeanDao;
import com.kalix.common.address.entities.AddressGroupBean;
import com.kalix.framework.core.impl.dao.GenericDao;
//import com.kalix.admin.core.api.dao.IOrganizationUserBeanDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AddressGroupBeanDaoImpl extends GenericDao<AddressGroupBean, String> implements IAddressGroupBeanDao {
//    private IOrganizationUserBeanDao orgUserBeanDao;
    private String uuid;
    public AddressGroupBeanDaoImpl() {
        uuid = UUID.randomUUID().toString();
    }
    @Override
    @PersistenceContext(unitName = "address-cm")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

    @Override
    public List<AddressGroupBean> getAllGroupsByUser(String userId) {
        return this.findByNativeSql("select * from common_address_group where userid='" + userId + "'", AddressGroupBean.class, null);
    }

    @Override
    public AddressGroupBean getGroupByName(String userId, String groupName) {
        List<AddressGroupBean> groupBeans = this.findByNativeSql("select * from common_address_group where userid='" + userId + "' and groupname = '" + groupName + "'", AddressGroupBean.class, null);
        if (groupBeans == null || groupBeans.isEmpty()) {
            return null;
        }
        return groupBeans.get(0);
    }

    @Override
    public List<AddressGroupBean> getAllGroupsByDefault(String userId) {
        return this.findByNativeSql("select * from common_address_group where userid='" + userId + "' and isdefault = true", AddressGroupBean.class, null);
    }


//    public void setOrgUserBeanDao(IOrganizationUserBeanDao orgUserBeanDao) {
//        this.orgUserBeanDao = orgUserBeanDao;
//    }
}
