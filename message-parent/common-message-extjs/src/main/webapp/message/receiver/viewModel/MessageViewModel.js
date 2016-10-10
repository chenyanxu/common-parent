/**
 * 消息模型
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.message.receiver.viewModel.MessageViewModel', {
    extend: 'kalix.viewmodel.BaseViewModel',
    alias: 'viewmodel.messageReceiverViewModel',
    data: {
        // batchDeleteUrl为执行批量删除服务的地址
        batchDeleteUrl: CONFIG.restRoot + '/camel/rest/messages/receiver/remove'
    }
});