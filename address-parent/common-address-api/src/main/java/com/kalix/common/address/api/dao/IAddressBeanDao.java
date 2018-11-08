package com.kalix.common.address.api.dao;


import com.kalix.common.address.entities.AddressBean;
import com.kalix.framework.core.api.dao.IGenericDao;

import java.util.List;

/**
 * @类描述：DAO接口
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public interface IAddressBeanDao extends IGenericDao<AddressBean, String> {
    List<AddressBean> getDefaultAddresses(String uesrId, String groupId, String name);

    /**
     * 修改当前登录用户中通讯录成员的组
     * @param userId
     * @param oldGroupId
     * @param newGroupId
     */
    int changeAddressGroup(String userId, String oldGroupId, String newGroupId);

    /**
     * 通过分组Id删除通讯录成员
     * @param groupId
     */
    int deleteByGroupId(String groupId);

    /**
     * 通过分组ID和用户ID获取通讯录信息
     * @param groupId
     * @param addressUserId
     * @return
     */
    AddressBean getAddressByGroupAndUser(String groupId, String addressUserId);

    /**
     * 修改通讯录是否同意的状态
     * @param addressUserId
     */
    int updateAddressAgree(String addressUserId);

    /**
     * 通过组和用户删除通讯录
     * @param addressUserId
     */
    int deleteAddressByGroupAndUser(String addressUserId);
}
