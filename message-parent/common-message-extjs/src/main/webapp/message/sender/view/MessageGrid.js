/**
 * 消息表格
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.message.sender.view.MessageGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.message.sender.controller.MessageGridController',
        'kalix.message.sender.store.MessageStore',
        'kalix.common.commonDict.component.CommonDictGridColumn'
    ],
    alias: 'widget.messageGrid',
    xtype: 'messageSenderGridPanel',
    controller: {
        type: 'messageSenderGridController',
        storeId: 'messageSenderStore',
        cfgForm: 'kalix.message.sender.view.MessageWindow',
        cfgViewForm: 'kalix.message.sender.view.MessageViewWindow',
        cfgModel: 'kalix.message.sender.model.MessageModel'
    },
    store: {
        type: 'messageSenderStore'
    },

    //todo 在此修改grid显示列
    stripeRows: true,
    manageHeight: true,
    //forceFit: true,
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
            text: '收件人',
            dataIndex: 'receiverNames'
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
            text: '发件时间',
            dataIndex: 'creationDate'
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
                text: '批量删除',
                permission: 'batchDelete',
                iconCls: 'iconfont icon-delete',
                handler: 'onBatchDelete'
            }
        ]
    }
});
