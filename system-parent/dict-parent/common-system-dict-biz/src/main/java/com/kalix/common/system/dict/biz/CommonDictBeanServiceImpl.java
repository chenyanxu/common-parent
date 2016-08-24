package com.kalix.common.system.dict.biz;

import com.kalix.common.system.dict.api.biz.ICommonDictBeanService;
import com.kalix.common.system.dict.api.dao.ICommonDictBeanDao;
import com.kalix.common.system.dict.entities.CommonDictBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.system.BaseDictServiceImpl;

public class CommonDictBeanServiceImpl extends BaseDictServiceImpl<ICommonDictBeanDao, CommonDictBean>
        implements ICommonDictBeanService {

    @Override
    public JsonStatus saveEntity(CommonDictBean entity) {
        Integer maxValue = dao.getFieldMaxValue("value","type='"+entity.getType()+"'");

        maxValue=maxValue+1;

        entity.setValue(maxValue);

        return super.saveEntity(entity);
    }
}
