package com.kalix.common.address.api.biz;


import com.kalix.common.address.entities.AddressGroupBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonData;

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
}
