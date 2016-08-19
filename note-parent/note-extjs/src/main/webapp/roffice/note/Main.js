/**
 * 公司公告首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.roffice.note.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.roffice.note.view.NoteGrid',
        'kalix.roffice.note.view.NoteSearchForm',
        'kalix.roffice.note.viewModel.NoteViewModel'
    ],
    storeId: 'noteStore',
    viewModel: 'noteViewModel',
    items: [
        {
            title: '公司公告查询',
            iconCls: 'x-fa fa-search',
            xtype: 'noteSearchForm'
        }, {
            xtype: 'noteGridPanel',
            id: 'noteGridPanel',
            title: '公司公告列表',
            iconCls: 'x-fa fa-comment',
            margin: 10
        }]
});
