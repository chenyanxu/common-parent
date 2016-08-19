/**
 *
 * Dict extJs use new base dict components
 * @author chenyanxu
 */
Ext.define('kalix.common.commonDict.Main', {
    extend: 'kalix.dict.Main',
    requires: [
        'kalix.dict.view.DictGrid',
        'kalix.dict.view.DictSearchForm',
        'kalix.common.commonDict.store.CommonDictStore'
    ]
});