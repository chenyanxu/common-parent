/**
 * 消息查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.message.sender.view.MessageViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.message.sender.viewModel.MessageViewModel',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.messageRecieverViewWindow',
    viewModel: 'messageSenderViewModel',
    xtype: "messageRecieverViewWindow",
    width: 400,
    //todo 在此修改查看字段
    items: [{
        defaults: {readOnly: true},
        xtype: 'baseForm',
        items: [
            {
                fieldLabel: '收件人',
                allowBlank: false,
                bind: {
                    value: '{rec.receiverNames}'
                }
            },
            {
                fieldLabel: '消息类别',
                xtype: 'dictCombobox',
                dictType: 'category',
                name: 'category',
                bind: {
                    value: '{rec.category}'
                }
            },
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
            },
            {
                fieldLabel: '发件时间',
                allowBlank: false,
                xtype: 'datefield',
                format: 'Y-m-d H:i:s',
                bind: {
                    value: '{rec.creationDate}'
                }
            }
        ]
    }
    ]
});