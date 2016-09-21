/**
 * 公司公告首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.common.note.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.common.note.view.NoteGrid',
        'kalix.common.note.view.NoteSearchForm'
    ],
    items: [
        {
            title: '公司公告查询',
            xtype: 'noteSearchForm'
        }, {
            xtype: 'noteGridPanel',
            id: 'noteGridPanel',
            title: '公司公告列表'
        }]
});
