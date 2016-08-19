/**
 * 公司公告查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.roffice.note.view.NoteSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    alias: 'widget.noteSearchForm',
    xtype: 'noteSearchForm',
    storeId: 'noteStore',
    items: [{
        xtype: 'textfield',
        fieldLabel: '标题',
        labelAlign: 'right',
        labelWidth: 60,
        width: 200,
        name: 'name'
    }]
});
