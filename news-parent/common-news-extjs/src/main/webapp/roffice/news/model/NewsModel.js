/**
 * 用户模型
 *
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.roffice.news.model.NewsModel', {
    extend: 'kalix.model.BaseModel',
    fields: [{
        name: 'title',
        type: 'string'
    }, {
        name: 'content',
        type: 'string'
    }, {
        name: 'publishPeople',
        type: 'string'
    }, {
        name: 'publishDate',
        type: 'date',
        dateFormat: 'Y-m-d H:i:s'
    }],

    validators: {
        title: [
            {
                type: 'length',
                max: '20',
                min: '4',
                bothMessage: '长度不能小于4个字符，不能超过20个字符！'
            }
        ],
        content: [{
            type: 'presence',
            message: '内容不能为空!'
        }
        ],
    }
});
