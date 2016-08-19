/**
 * 消息表格控制器
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.message.sender.controller.MessageWindowController', {
    extend: 'kalix.controller.BaseWindowController',
    alias: 'controller.messageSenderWindowController',
    onShow: function () {
        //this.getView().lookupViewModel().getData().rec.set('senderid', Ext.util.Cookies.get('currentUserName') || '系统管理员');
        //this.getView().lookupViewModel().getData().rec.dirty = false;

    },
    onChange: function (combo, newValue, oldValue, eOpts) {
        var records = combo.getValueRecords();
        var receiverIds = '';
        var receiverNames = '';
        records.forEach(function (record) {
            if (receiverIds == '') {
                receiverIds = record.get('id');
            }
            else {
                receiverIds += ':' + record.get('id');
            }
            if (receiverNames == '') {
                receiverNames = record.get('name');
            }
            else {
                receiverNames += ':' + record.get('name');
            }
        });

        combo.lookupViewModel().get('rec').set('receiverIds', receiverIds);
        combo.lookupViewModel().get('rec').set('receiverNames', receiverNames);
    }
});
