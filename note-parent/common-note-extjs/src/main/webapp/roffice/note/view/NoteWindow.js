/**
 * 公司公告添加和修改表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.roffice.note.view.NoteWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.roffice.note.viewModel.NoteViewModel',
        'kalix.controller.BaseWindowController',
        'kalix.admin.user.store.UserStore',
        'Ext.ux.form.TinyMCETextArea'
    ],
    alias: 'widget.noteWindow',
    viewModel: 'noteViewModel',
    controller: {
        type: 'baseWindowController',
        storeId: 'noteStore'
    },
    xtype: "noteWindow",
    width: 950,
    height: 520,
    items: [{
        xtype: 'baseForm',
        items: [{
            fieldLabel: '标题',
            allowBlank: false,
            bind: {
                //activeError: '{validation.name}',
                value: '{rec.name}'
            }
        }, {
            fieldLabel: '星级',
            xtype: 'fieldcontainer',
            allowBlank: false,
            //bind: {
            //    activeError: '{validation.rating}',
            //    //value: '{rec.rating}'
            //},
            items: [{
                xtype: 'rating',
                scale: '150%',
                bind: '{rec.rating}'
            }]
        }, {
            fieldLabel: '内容',
            labelAlign: 'right',
            allowBlank: false,
            //region: 'center',
            xtype: 'tinymce_textarea',
            height: 320,
            fieldStyle: 'font-family: Courier New; font-size: 12px;',
            tinyMCEConfig: {
                plugins: [
                    "advlist autolink lists link image charmap print preview hr anchor pagebreak",
                    "searchreplace wordcount visualblocks visualchars code fullscreen",
                    "insertdatetime media nonbreaking save table contextmenu directionality",
                    "emoticons template paste textcolor"
                ],
                toolbar1: "newdocument fullpage | bold italic underline strikethrough | alignleft aligncenter alignright alignjustify | styleselect formatselect fontselect fontsizeselect",
                toolbar2: "cut copy paste | searchreplace | bullist numlist | outdent indent blockquote | undo redo | link unlink anchor image media code | inserttime preview | forecolor backcolor",
                toolbar3: "table | hr removeformat | subscript superscript | charmap emoticons | print fullscreen | ltr rtl | spellchecker | visualchars visualblocks nonbreaking template pagebreak restoredraft",
                content_css: "resources/css/contents.css",
                menubar: true,
                toolbar_items_size: 'small',
                language: 'zh_CN'
            },
            listeners: {
                change: function (field, newValue, oldValue) {
                    console.log('change: ' + oldValue + ' -> ' + newValue);
                    this.findParentByType('window').viewModel.get('rec').set('content', newValue);
                },
                beforerender: function () {
                    this.setValue(this.findParentByType('window').viewModel.get('rec').get('content'));
                }
            }
        }]
    }]
});