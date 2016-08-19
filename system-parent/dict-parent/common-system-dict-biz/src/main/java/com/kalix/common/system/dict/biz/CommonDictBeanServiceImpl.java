package com.kalix.common.system.dict.biz;

import com.kalix.framework.core.api.cache.ICacheManager;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.ConfigUtil;
import com.kalix.framework.core.util.SerializeUtil;
import com.kalix.common.system.dict.api.biz.ICommonDictBeanService;
import com.kalix.common.system.dict.api.dao.ICommonDictBeanDao;
import com.kalix.common.system.dict.entities.CommonDictBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonDictBeanServiceImpl extends ShiroGenericBizServiceImpl<ICommonDictBeanDao, CommonDictBean> implements ICommonDictBeanService {
    
    private ICacheManager cacheManager=null;
    private List<Map> dictTypes=null;
    public static final String DICT_CACHE = "common_dict_cache";
    public static final String CONFIG_DICT = "ConfigCommonDict";

    @Override
    public JsonStatus saveEntity(CommonDictBean entity) {
        Integer maxValue = dao.getFieldMaxValue("value","type='"+entity.getType()+"'");

        maxValue=maxValue+1;

        entity.setValue(maxValue);

        return super.saveEntity(entity);
    }

    @Override
    public List getAllEntity() {
        List rtn = null;

        if (this.cacheManager.exists(DICT_CACHE)) {
            rtn = SerializeUtil.unserialize(cacheManager.getObj(DICT_CACHE));
        } else {
            Object obj = ConfigUtil.getConfigProp("dict_cache_timeout", CONFIG_DICT);
            int cacheTimeout = obj == null ? 600 : new Integer(obj.toString());

            rtn = super.getAllEntity();
            
            this.cacheManager.save(DICT_CACHE, rtn, cacheTimeout);
        }

        return rtn;
    }

    public void setCacheManager(ICacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public List<Map> getDictTypes(String query) {
        if(dictTypes==null){
            dictTypes=new ArrayList<>();
            Map map=null;
            Object obj = ConfigUtil.getConfigProp("dict_types", CONFIG_DICT);

            for (String str :obj.toString().split(",")) {
                map=new HashMap<>();
                map.put("name",str);
                dictTypes.add(map);
            }
        }

        if(query!=null && !query.trim().equals("")){
            List<Map> rtn=new ArrayList<>();

            for(Map map:dictTypes){
                if(map.get("name").toString().contains(query.trim())){
                    rtn.add(map);
                }
            }

            return rtn;
        }

        return dictTypes;
    }
}
