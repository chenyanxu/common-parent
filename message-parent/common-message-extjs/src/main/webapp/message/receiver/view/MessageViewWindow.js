/**
 * 消息查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.message.receiver.view.MessageViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.message.receiver.viewModel.MessageViewModel',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.messageRecieverViewWindow',
    viewModel: 'messageReceiverViewModel',
    xtype: "messageRecieverViewWindow",
    width: 400,
    //todo 在此修改查看字段
    items: [{
        defaults: {readOnly: true},
        xtype: 'baseForm',
        items: [
            {
                fieldLabel: '发件人',
                allowBlank: false,
                bind: {
                    value: '{rec.senderName}'
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
                fieldLabel: '收件时间',
                allowBlank: false,
                xtype: 'datefield',
                format: 'Y-m-d H:i:s',
                bind: {
                    value: '{rec.creationDate}'
                }
            },
            {
                fieldLabel: '是否已读',
                allowBlank: false,
                xtype: 'combo',
                displayField: 'name',
                valueField: 'value',
                store: {data: [{name: '已读', value: true}, {name: '未读', value: false}]},
                bind: {
                    value: '{rec.read}'
                }
            }
            //,
            //{
            //    fieldLabel: '消息状态',
            //
            //    xtype: 'combobox',
            //    valueField: 'key',
            //    displayField: 'name',
            //    store: {
            //        data: [
            //            {'name': '未通知', 'key': 1},
            //            {'name': '已通知', 'key': 0}
            //        ]
            //    },
            //    name: 'state',
            //    bind: {
            //        value: '{rec.state}'
            //    }
            //}
            //,
            //{
            //    fieldLabel: '消息标识',
            //    allowBlank: false,
            //    xtype: 'numberfield',
            //    bind: {
            //        value: '{rec.sign}'
            //    }
            //}
        ]
    }

    ]


});