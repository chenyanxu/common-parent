/**
 * 消息模型
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.message.receiver.viewModel.MessageViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.messageReceiverViewModel',
    data: {
        // batchDeleteUrl为执行批量删除服务的地址
        batchDeleteUrl: '/kalix/camel/rest/messages/receiver/remove',
        rec: null,
        validation: {},  //验证错误信息
        icon: '',
        title: '',
        view_operation: false,
        view_title: '查看收件',
        add_title: '添加消息',
        edit_title: '修改消息',
        add_image_path: '/kalix/message/resources/images/message_add.png',
        view_image_path: '/kalix/message/resources/images/message_view.png',
        edit_image_path: '/kalix/message/resources/images/message_edit.png',
    }
});