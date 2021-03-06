package com.kalix.common.address.dao;

import com.kalix.common.address.api.dao.IAddressBeanDao;
import com.kalix.common.address.entities.AddressBean;
import com.kalix.framework.core.impl.dao.GenericDao;

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

    @Override
    public List<AddressBean> getDefaultAddresses(Long uesrId, Long groupId, String name) {
        StringBuffer sbff = new StringBuffer();
        sbff.append("select * from common_address");
        if (uesrId != null) {
            sbff.append(" where userid= ").append(uesrId);
        }
        sbff.append(" and isagree= ").append("true");
        if (name != null && !name.isEmpty()) {
            sbff.append(" and name like '%").append(name).append("%'");
        }
        if (groupId != null) {
            sbff.append(" and groupid= ").append(groupId);
        }
        return this.findByNativeSql(sbff.toString(), AddressBean.class, null);
    }

    @Override
    public int changeAddressGroup(Long userId, Long oldGroupId, Long newGroupId) {
        return this.updateNativeQuery("update common_address set groupid="+newGroupId+" where userid="+userId+" and groupid="+oldGroupId);
    }

    @Override
    public int deleteByGroupId(Long groupId) {
        return this.updateNativeQuery("delete from common_address where groupid=" + groupId);
    }

    @Override
    public AddressBean getAddressByGroupAndUser(Long groupId, Long addressUserId) {
        String sql = "select * from common_address where groupid=" + groupId + " and addressuserid=" + addressUserId;
        List<AddressBean> addresses = this.findByNativeSql(sql, AddressBean.class, null);
        if (addresses != null && addresses.size() > 0) {
            return addresses.get(0);
        }
        return null;
    }

    @Override
    public int updateAddressAgree(Long addressUserId) {
        String sql = "update common_address set isagree=true where addressuserid="+addressUserId;
        return this.updateNativeQuery(sql);
    }

    @Override
    public int deleteAddressByGroupAndUser(Long addressUserId) {
        String sql = "delete from common_address where addressuserid=" + addressUserId;
        return this.updateNativeQuery(sql);
    }
}
