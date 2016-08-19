/**
 * @author chenyanxu
 */
Ext.define('kalix.common.commonDict.component.CommonDictCombobox', {
    extend: 'kalix.dict.component.DictCombobox',
    alias: 'widget.commonDictCombobox',
    xtype: 'commonDictCombobox',
    storeClass:'kalix.common.commonDict.store.CommonDictCacheStore'
});