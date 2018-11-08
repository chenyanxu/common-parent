package com.kalix.common.address.api.biz;


import com.kalix.common.address.entities.AddressGroupBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;

/**
 * @类描述：应用服务接口.
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public interface IAddressGroupBeanService extends IBizService<AddressGroupBean> {
    /**
     * 查找当前用户下所有通讯录分组信息
     * @param jsonStr
     * @return
     */
    JsonData getAllGroups(String jsonStr);

    /**
     * 修改当前组下的用户分组为我的好友，删除当前的组
     * @param id 分组ID
     * @param jsonStr 用户ID
     * @return
     */
    JsonStatus deleteGroup(String id, String jsonStr);

    JsonData getMyFriendAddresses(String currentUserId);
    JsonData getMyWorkmateAddresses(String currentUserId);
    JsonData getMyDefaultGroupAddresses(String currentUserId);
}
