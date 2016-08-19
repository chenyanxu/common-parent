/**
 * 公司公告模型
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.common.note.viewModel.NoteViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.noteViewModel',
    data: {
        rec: null,
        validation: {},  //验证错误信息
        icon: '',
        title: '',
        view_operation: false,
        view_title: '查看公司公告',
        add_title: '添加公司公告',
        edit_title: '修改公司公告',
        add_image_path: CONFIG.restRoot + '/common/note/resources/images/note_add.png',
        view_image_path: CONFIG.restRoot + '/common/note/resources/images/note_view.png',
        edit_image_path: CONFIG.restRoot + '/common/note/resources/images/note_edit.png',
    }
});