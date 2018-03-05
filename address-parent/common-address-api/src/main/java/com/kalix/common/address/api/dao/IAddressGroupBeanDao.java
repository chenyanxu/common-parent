package com.kalix.common.address.api.dao;


import com.kalix.common.address.entities.AddressGroupBean;
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
public interface IAddressGroupBeanDao extends IGenericDao<AddressGroupBean, Long> {
    /**
     * 获取当前用户下所有的分组信息
     * @param userId
     * @return
     */
    public List<AddressGroupBean> getAllGroupsByUser(Long userId);

    /**
     *
     * @param userId
     * @param groupName
     * @return
     */
    public AddressGroupBean getGroupByName(Long userId, String groupName);
}
