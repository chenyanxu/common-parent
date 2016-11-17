/**
 * 用户表格
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.common.news.view.NewsGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.common.news.controller.NewsGridController',
        'kalix.common.news.store.NewsStore'
    ],
    alias: 'widget.newsGrid',
    xtype: 'newsGridPanel',
    controller: {
        type: 'newsGridController',
        storeId: 'newsStore',
        cfgForm: 'kalix.common.news.view.NewsWindow',
        cfgViewForm: 'kalix.common.news.view.NewsViewWindow',
        cfgModel: 'kalix.common.news.model.NewsModel'
    },
    store: {
        type: 'newsStore'
    },
    columns: {
        items: [
            /*{
             xtype: "rownumberer",
             text: "行号",
             width: 50,
             align: 'center'
             },*/
            {
                text: '编号',
                dataIndex: 'id',
                hidden: true,
                flex: 1
                //width: 40
            }, {
                text: '标题',
                dataIndex: 'title',
                flex: 1
            }, {
                text: '发布人',
                dataIndex: 'publishPeople',
                flex: 1
            }, {
                text: '发布时间',
                dataIndex: 'creationDate',
                flex: 1
            },{
                text: '修改时间',
                dataIndex: 'updateDate',
                flex: 1
            },
            {
                xtype: 'securityGridColumnRUD',
                flex: 1,
                permissions: [
                    'view',
                    'edit',
                    'delete'
                ]
            }]
    },
    plugins: [{
        ptype: 'rowexpander',
        rowBodyTpl: new Ext.XTemplate(
            '<p><b>内容:</b> {content}</p>',
            {
                formatChange: function (v) {
                    var color = v >= 0 ? 'green' : 'red';
                    return '<span style="color: ' + color + ';">' + Ext.util.Format.usMoney(v) + '</span>';
                }
            })
    }],
    collapsible: true,
    animCollapse: true,

    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '添加',
                xtype: 'button',
                permission: 'add',
                iconCls: 'iconfont icon-add',
                handler: 'onAdd'
            }]
    }
});
