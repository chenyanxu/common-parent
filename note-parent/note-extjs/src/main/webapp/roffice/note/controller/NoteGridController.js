/**
 * 公司公告表格控制器
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.roffice.note.controller.NoteGridController', {
    extend: 'kalix.controller.BaseGridController',
    alias: 'controller.noteGridController',
    exportToExcel: function () {
        this.getView().saveDocumentAs({
            title: '公司公告',
            fileName: 'excelExport.xls'
        });
    }
});

