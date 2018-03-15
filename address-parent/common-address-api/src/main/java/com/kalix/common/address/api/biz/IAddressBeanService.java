package com.kalix.common.address.api.biz;


import com.kalix.common.address.entities.AddressBean;
import com.kalix.common.message.entities.MessageBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;

import java.util.List;

/**
 * @类描述：应用服务接口.
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public interface IAddressBeanService extends IBizService<AddressBean> {
    JsonData getDefaultAddressList(String jsonStr, String otherStr);
    void changeToDefaultGroup(Long userId, Long oldGroupId, Long newGroupId);
    void deleteAddressByGroup(Long groupId);
    List<AddressBean> getAddressListByGroup(Long userId, Long groupId);
    JsonStatus saveNewAddress(AddressBean entity);
    JsonStatus agreeAddFriend(MessageBean entity);
    JsonStatus degreeAddFriend(MessageBean entity);
    JsonData getAddressUsers(String userId);
}
