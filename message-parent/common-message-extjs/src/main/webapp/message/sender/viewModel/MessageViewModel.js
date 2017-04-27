/**
 * 消息模型
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.message.sender.viewModel.MessageViewModel', {
    extend: 'kalix.viewmodel.BaseViewModel',
    alias: 'viewmodel.messageSenderViewModel',
    data: {
        // batchDeleteUrl为执行批量删除服务的地址
        batchDeleteUrl: CONFIG.restRoot + '/camel/rest/messages/sender/remove'
    }
});