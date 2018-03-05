package com.kalix.common.address.biz;

import com.kalix.common.address.api.biz.IAddressBeanService;
import com.kalix.common.address.api.dao.IAddressBeanDao;
import com.kalix.common.address.entities.AddressBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.security.IShiroService;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AddressBeanServiceImpl extends GenericBizServiceImpl<IAddressBeanDao, AddressBean> implements IAddressBeanService {
    public AddressBeanServiceImpl() {
        super.init(AddressBean.class.getName());
    }
}
