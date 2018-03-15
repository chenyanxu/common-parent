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
public interface IAddressBeanDao extends IGenericDao<AddressBean, Long> {
    List<AddressBean> getDefaultAddresses(Long uesrId, Long groupId, String name);

    /**
     * 修改当前登录用户中通讯录成员的组
     * @param userId
     * @param oldGroupId
     * @param newGroupId
     */
    int changeAddressGroup(Long userId, Long oldGroupId, Long newGroupId);

    /**
     * 通过分组Id删除通讯录成员
     * @param groupId
     */
    int deleteByGroupId(Long groupId);

    /**
     * 通过分组ID和用户ID获取通讯录信息
     * @param groupId
     * @param addressUserId
     * @return
     */
    AddressBean getAddressByGroupAndUser(Long groupId, Long addressUserId);

    /**
     * 修改通讯录是否同意的状态
     * @param addressUserId
     */
    int updateAddressAgree(Long addressUserId);

    /**
     * 通过组和用户删除通讯录
     * @param addressUserId
     */
    int deleteAddressByGroupAndUser(Long addressUserId);
}
