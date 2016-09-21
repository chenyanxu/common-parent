/**
 * 公司新闻添加和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.common.news.view.NewsWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    alias: 'widget.newsWindow',
    xtype: 'newsWindow',
    width: 910,
    items: [{
        items: [{
            fieldLabel: '标题',
            allowBlank: false,
            bind: {
                value: '{rec.title}'
            }
        }, {
            fieldLabel: '内容',
            allowBlank: false,
            xtype: 'htmleditor',
            height:300,
            bind:{
                value:'{rec.content}'
            }
        }]
    }]
});