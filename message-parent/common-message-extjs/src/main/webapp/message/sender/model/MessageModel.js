/**
 * 消息模型
 *
 * @author
 * @version 1.0.0
 */


Ext.define('kalix.message.sender.model.MessageModel', {
    extend: 'kalix.model.BaseModel',

    //todo 在此修改模型定义
    fields: [
        {
            name: 'senderId',
            type: 'string'
        },
        {
            name: 'receiverNames',
            type: 'string',
            validators: [{type: 'presence'}]
        },
        {
            name: 'receiverIds',
            type: 'string',
            hidden: true
        },
        {
            name: 'category',
            type: 'string',
            defaultValue: '1'
        },
        {
            name: 'title',
            type: 'string',
            validators: [{type: 'presence'}]
        },
        {
            name: 'content',
            type: 'string',
            validators: [{type: 'presence'}]
        }
    ]//,
    ////todo 在此修改模型验证提示信息
    //validators: {
    //    category: [{
    //        type: 'presence',
    //        message: '消息类别不能为空'
    //    }],
    //    title: [{
    //        type: 'presence',
    //        message: '消息主题不能为空'
    //    }],
    //    content: [{
    //        type: 'presence',
    //        message: '消息内容不能为空'
    //    }]
    //}
});
