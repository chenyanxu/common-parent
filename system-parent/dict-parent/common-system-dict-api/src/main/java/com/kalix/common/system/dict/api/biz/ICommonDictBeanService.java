package com.kalix.common.system.dict.api.biz;

import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.common.system.dict.entities.CommonDictBean;

import java.util.List;
import java.util.Map;

public interface ICommonDictBeanService extends IBizService<CommonDictBean> {
    List<Map> getDictTypes(String query);
}
