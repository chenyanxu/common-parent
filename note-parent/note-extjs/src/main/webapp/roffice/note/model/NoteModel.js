/**
 * 公司公告模型
 *
 * @author
 * @version 1.0.0
 */


Ext.define('kalix.roffice.note.model.NoteModel', {
    extend: 'kalix.model.BaseModel',

    fields: [{
        name: 'name',
        type: 'string'
    }, {
        name: 'rating',
        type: 'string',
        defaultValue: '3'
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
        content: [{
            type: 'presence',
            message: '内容不能为空!'
        }],
        name: [{
            type: 'presence',
            message: '名称不能为空!'
        }],
        rating: [{
            type: 'presence',
            message: '级别不能为空!'
        }]
    }
});
