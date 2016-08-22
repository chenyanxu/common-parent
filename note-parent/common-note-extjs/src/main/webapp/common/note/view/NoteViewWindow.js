/**
 * 公司公告查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.common.note.view.NoteViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.admin.user.store.UserStore',
        'Ext.ux.form.TinyMCETextArea'
    ],
    alias: 'widget.noteViewWindow',
    xtype: "noteViewWindow",
    width: 950,
    height: 520,
    items: [{
        defaults: {readOnly: true},
        xtype: 'form',
        defaultType: 'displayfield',
        bodyPadding: 10,
        buttonAlign: "center",
        items: [
            {
                fieldLabel: '星级',
                labelAlign: 'right',
                disabled: true,
                xtype: 'fieldcontainer',
                items: [{
                    xtype: 'rating',
                    scale: '150%',
                    bind: '{rec.rating}'
                }]
            },
            {
                fieldLabel: '标题',
                labelAlign: 'right',
                bind: {
                    value: '{rec.name}'
                }
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
            }, {
                fieldLabel: '发布人',
                labelAlign: 'right',
                bind: {
                    value: '{rec.publishPeople}'
                }
            }, {
                fieldLabel: '发布时间',
                labelAlign: 'right',
                format: 'Y-m-d',
                bind: {
                    value: '{rec.publishDate}'
                },
                renderer: function (value) {
                    var createDate = new Date(value);
                    return createDate.format("yyyy-MM-dd hh:mm:ss");
                }
            }]
    }]
});