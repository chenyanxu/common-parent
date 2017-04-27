/**
 * 公司公告数据仓库
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.common.note.store.NoteStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.common.note.model.NoteModel',
    alias: 'store.noteStore',
    xtype: 'noteStore',
    storeId: 'noteStore',
    proxyUrl: CONFIG.restRoot + '/camel/rest/notes'
});