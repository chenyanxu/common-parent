/**
 * 消息查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.message.sender.view.MessageSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    requires: [
        'kalix.admin.adminDict.component.AdminDictCombobox',
        'kalix.message.sender.controller.MessageSearchFormController'
    ],
    alias: 'widget.messageSenderSearchForm',
    xtype: 'messageSenderSearchForm',
    controller: {
        type: 'messageSearchFormController'
    },
    storeId: 'messageSenderStore',
    items: [
        {
            xtype: 'textfield',
            fieldLabel: '收件人',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: '%receiverNames%'
        },
        //{
        //    xtype: 'dictCombobox',
        //    fieldLabel: '消息类别',
        //    dictType: 'category',
        //    name: 'category',
        //    bind: {
        //        value: '{rec.category}'
        //    }
        //},
        {
            xtype: 'textfield',
            fieldLabel: '消息主题',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            margin: '0 0 0 20',
            name: '%title%'
        },
        {
            xtype: 'datefield',
            format: 'Y-m-d', formatText:'格式为YYYY-mm-dd',
            fieldLabel: '发件时间:',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            margin: '0 0 0 20',
            name: 'creationDate:begin:gt'
        },
        {
            xtype: 'label',
            text: '-',
            margin: '5 5 0 5'
        },
        {
            xtype: 'datefield',
            format: 'Y-m-d', formatText:'格式为YYYY-mm-dd',
            headLabel: true,
            labelAlign: 'right',
            width: 140,
            name: 'creationDate:end:lt'
        }
    ]
});
