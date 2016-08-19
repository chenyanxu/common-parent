/**
 * 字典数据仓库
 *
 * @author chenyanxu
 */
Ext.define('kalix.common.commonDict.store.CommonDictStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.dict.model.DictModel',
    alias: 'store.commonDictStore',
    xtype: 'commonDictStore',
    storeId: 'commonDictStore',
    proxyUrl: CONFIG.restRoot + '/camel/rest/common/dicts'
});