/**
 * 公司新闻查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.common.news.view.NewsViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    alias: 'widget.newsViewWindow',
    xtype: 'newsViewWindow',
    width: 910,
    items: [{
        defaults: {readOnly: true},
        xtype: 'baseForm',
        items: [{
            fieldLabel: '标题',
            labelAlign: 'right',
            allowBlank: false,
            bind: {
                value: '{rec.title}'
            }
        }, {
            fieldLabel: '内容',
            labelAlign: 'right',
            allowBlank: false,
            xtype: 'htmleditor',
            height:300,
            bind:{
                value:'{rec.content}'
            }
        }]
    }]

});