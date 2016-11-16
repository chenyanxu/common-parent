/**
 * 消息表格控制器
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.message.sender.controller.MessageSearchFormController', {
    extend: 'kalix.controller.BaseSearchFormController',
    alias: 'controller.messageSearchFormController',
    onSearch: function (target, event) {
        var view=this.getView();
        var beginDate = view.items.getAt(2).rawValue;
        var endDate = view.items.getAt(4).rawValue;

        if(beginDate != null && endDate != null && beginDate != '' && endDate != ''){
            if(beginDate>endDate){
                Ext.Msg.alert(CONFIG.ALTER_TITLE_INFO, '发件开始时间不能大于发件结束时间');
                return;
            }
        }
        this.callParent(arguments);
    }
});
