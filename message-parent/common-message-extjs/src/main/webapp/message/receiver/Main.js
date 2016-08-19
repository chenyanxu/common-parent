/**
 * 消息首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.message.receiver.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.message.receiver.view.MessageGrid',
        'kalix.message.receiver.view.MessageSearchForm',
        'kalix.message.receiver.viewModel.MessageViewModel',
        'kalix.message.sender.store.MessageStore'
    ],
    storeId: 'messageReceiverStore',
    viewModel: 'messageReceiverViewModel',
    items: [
        {
            title: '收件查询',
            xtype: 'messageReceiverSearchForm'
        }, {
            xtype: 'messageReceiverGridPanel',
            id: 'messageReceiverGridPanel',
            title: '收件列表',
            margin: 10
        }
    ]
});
