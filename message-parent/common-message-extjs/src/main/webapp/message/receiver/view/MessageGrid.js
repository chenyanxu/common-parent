/**
 * 消息表格
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.message.receiver.view.MessageGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.message.receiver.controller.MessageGridController',
        'kalix.message.receiver.store.MessageStore',
        'kalix.common.commonDict.component.CommonDictGridColumn'
    ],
    alias: 'widget.messageGrid',
    xtype: 'messageReceiverGridPanel',
    controller: {
        type: 'messageReceiverGridController',
        storeId: 'messageReceiverStore',
        cfgForm: 'kalix.message.receiver.view.MessageWindow',
        cfgViewForm: 'kalix.message.receiver.view.MessageViewWindow',
        cfgModel: 'kalix.message.receiver.model.MessageModel'
    },
    store: {
        type: 'messageReceiverStore'
    },

    //todo 在此修改grid显示列
    stripeRows: true,
    manageHeight: true,
    forceFit: true,
    selModel: {selType: 'checkboxmodel', mode: "SIMPLE"},
    columns: [
        {
            xtype: "rownumberer"
        },
        {
            text: '编号',
            dataIndex: 'id',
            hidden: true
        },
        {
            text: '发件人',
            dataIndex: 'senderName'
        },
        {
            text: '消息类别',
            xtype: 'commonDictGridColumn',
            dictType: '消息类别',
            dataIndex: 'category',
            renderer: null
        },
        {
            text: '消息主题',
            dataIndex: 'title'
        },
        {
            text: '收件时间',
            dataIndex: 'creationDate'
        },
        {
            text: '是否已读',
            trueText: '已读',
            falseText: '未读',
            xtype: 'booleancolumn',
            dataIndex: 'read',
            renderer: null
        },
        {
            xtype: 'securityGridColumnCommon',
            items: [
                {
                    iconCls: 'iconfont icon-view-column',
                    permission: 'view',
                    tooltip: '查看',
                    handler: 'onView'
                },
                {
                    iconCls: 'iconfont icon-delete',
                    permission: 'delete',
                    tooltip: '删除',
                    handler: 'onDelete'
                }
            ]
        }
    ],
    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '发件',
                xtype: 'button',
                permission: 'add',
                iconCls: 'iconfont icon-add',
                handler: 'onSender'
            },
            {
                text: '批量删除',
                permission: 'batchDelete',
                iconCls: 'iconfont icon-delete',
                handler: 'onBatchDelete'
            }
        ]
    }
});
