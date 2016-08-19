/**
 * 消息数据仓库
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.message.sender.store.MessageStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.message.sender.model.MessageModel',
    alias: 'store.messageSenderStore',
    xtype: 'messageSenderStore',
    storeId: "messageSenderStore",
    singleton: true,
    proxyUrl: '/kalix/camel/rest/messages/sender',
    listeners: {
        load: function (target, records, successful, eOpts) {
            Ext.app.Application.instance.getApplication().getStore('messageReceiverStore').load();
        }
    }
});