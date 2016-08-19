/**
 * 公司新闻查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.roffice.news.view.NewsSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    alias: 'widget.newsSearchForm',
    xtype: 'newsSearchForm',
    storeId: 'newsStore',
    items: [
        {
            xtype: 'textfield',
            fieldLabel: '标题',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: 'title'
        }]
});
