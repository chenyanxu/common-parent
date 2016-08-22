/**
 * 字典数据仓库
 *
 * @author chenyanxu
 */
Ext.define('kalix.common.commonDict.store.CommonDictCacheStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.dict.model.DictModel',
    alias: 'store.commonDictCacheStore',
    xtype: 'commonDictCacheStore',
    storeId: 'commonDictCacheStore',
    pageSize:0,
    autoLoad: true,
    singleton: true,
    proxyUrl: CONFIG.restRoot + '/camel/rest/common/dicts/cache/list'
});