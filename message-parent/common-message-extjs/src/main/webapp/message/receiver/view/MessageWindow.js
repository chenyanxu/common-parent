/**
 * 消息新增和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.message.receiver.view.MessageWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.message.receiver.viewModel.MessageViewModel',
        'kalix.message.receiver.controller.MessageWindowController',
        'kalix.admin.user.store.UserStore',
        'kalix.common.commonDict.component.CommonDictCombobox'
    ],
    alias: 'widget.messageReceiverWindow',
    viewModel: 'messageReceiverViewModel',
    controller: {
        type: 'messageReceiverWindowController',
        storeId: 'messageReceiverStore'
    },
    listeners: {
        show: 'onShow'
    },
    xtype: "messageReceiverWindow",
    width: 400,
    items: [
        {
            xtype: 'baseForm',
            items: [
                {
                    fieldLabel: '收件人',
                    allowBlank: false,
                    bind: {
                        value: '{rec.receiverName}'
                    }
                },
                /*                {
                    fieldLabel: '消息类别',
                    xtype: 'adminDictCombobox',
                    dictType: 'category',
                    name: 'category',
                    bind: {
                        value: '{rec.category}'
                    }
                 },*/
                {
                    fieldLabel: '消息主题',
                    allowBlank: false,
                    bind: {
                        value: '{rec.title}'
                    }
                },
                {
                    fieldLabel: '消息内容',
                    xtype: 'textareafield',
                    allowBlank: false,
                    bind: {
                        value: '{rec.content}'
                    }
                }
            ]
        }
    ]
});