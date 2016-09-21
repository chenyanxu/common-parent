/**
 * 公司公告添加和修改表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.common.note.view.NoteWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    alias: 'widget.noteWindow',
    xtype: "noteWindow",
    width: 910,
    items: [{
        items: [{
            fieldLabel: '标题',
            allowBlank: false,
            bind: {
                value: '{rec.name}'
            }
        }, {
            fieldLabel: '星级',
            xtype: 'fieldcontainer',
            allowBlank: false,
            items: [{
                xtype: 'rating',
                scale: '150%',
                bind: '{rec.rating}'
            }]
        }, {
            fieldLabel: '内容',
            allowBlank: false,
            xtype: 'htmleditor',
            height: 300,
            bind:{
                value:'{rec.content}'
            }
        }]
    }]
});