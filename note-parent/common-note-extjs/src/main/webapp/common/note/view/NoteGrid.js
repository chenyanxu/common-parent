/**
 * 公司公告表格
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.common.note.view.NoteGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.common.note.controller.NoteGridController',
        'kalix.common.note.store.NoteStore',
        'Ext.grid.plugin.Exporter',
        'Ext.ux.rating.Picker'
    ],
    alias: 'widget.noteGrid',
    xtype: 'noteGridPanel',
    controller: {
        type: 'noteGridController',
        storeId: 'noteStore',
        cfgForm: 'kalix.common.note.view.NoteWindow',
        cfgViewForm: 'kalix.common.note.view.NoteViewWindow',
        cfgModel: 'kalix.common.note.model.NoteModel'
    },
    store: {
        type: 'noteStore'
    },
    columns: {
        //defaults: {flex: 1},
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
            },
            {
                text: '星级',
                xtype: 'widgetcolumn',
                flex: 1,
                //disabled: true,
                //stopSelection :false,
                dataIndex: 'rating',
                //focusable: false,
                widget: {
                    xtype: 'rating',
                    style: 'color: orange;',
                    overStyle: 'color: red;',
                    /*bind: {
                     disabled:'{!employeeGrid.selection}'
                     }*/
                    focusable: false,
                    listeners: {
                        change: function (picker, value) {
                            console.log('Rating ' + value);
                            console.log('Rating new value is ' + value + ' ,olde value is ' + this.getValue());

                        },
                        onclick: function (event) {
                            var value = this.valueFromEvent(event);
                            console.log('Rating new value is ' + value + ' ,olde value is ' + this.getValue());
                            this.setValue(value);
                        }
                    }
                }
            }, {
                text: '标题',
                dataIndex: 'name',
                flex: 1
            },
            /*{
             text: '内容',
             dataIndex: 'content',
             flex: 3
             },*/ {
                text: '发布人',
                dataIndex: 'publishPeople',
                flex: 1
            }, {
                text: '发布时间',
                dataIndex: 'creationDate',
                flex: 1
            },
            {
                xtype: 'securityGridColumnRUD',
                permissions: [
                    'view',
                    'edit',
                    'delete'
                ]
            }
        ],

    },
    plugins: [
        {
            ptype: 'gridexporter'
        },
        {
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
            }
        ]
    },
    header: {
        itemPosition: 1, // after title before collapse tool
        items: [{
            ui: 'default-toolbar',
            xtype: 'button',
            text: '导出',
            iconCls: 'x-fa fa-file-excel-o',
            handler: 'exportToExcel'
        }]

    }

});
