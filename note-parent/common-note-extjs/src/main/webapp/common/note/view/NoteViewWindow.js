/**
 * 公司公告查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.common.note.view.NoteViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    alias: 'widget.noteViewWindow',
    xtype: 'noteViewWindow',
    width: 910,
    items: [
        {
            defaults: {readOnly: true},
            items: [

                {
                    fieldLabel: '标题',
                    bind: {
                        value: '{rec.name}'
                    }
                },
                {
                    fieldLabel: '星级',
                    disabled: true,
                    xtype: 'fieldcontainer',
                    items: [{
                        xtype: 'rating',
                        scale: '150%',
                        bind: '{rec.rating}'
                    }]
                },
                {
                    fieldLabel: '内容',
                    allowBlank: false,
                    xtype: 'htmleditor',
                    height: 300,
                    bind: {
                        value: '{rec.content}'
                    }
                }
            ]
        }
    ]
});