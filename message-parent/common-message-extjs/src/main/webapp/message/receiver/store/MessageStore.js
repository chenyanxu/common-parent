/**
 * 消息数据仓库
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.message.receiver.store.MessageStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.message.receiver.model.MessageModel',
    alias: 'store.messageReceiverStore',
    xtype: 'messageReceiverStore',
    storeId: "messageReceiverStore",
    singleton: true,
    proxyUrl: '/kalix/camel/rest/messages/receiver'
});