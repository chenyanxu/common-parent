/**
 * 用户模型
 *
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.common.news.model.NewsModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {
            name: 'title'
        }, {
            name: 'content'
        }, {
            name: 'publishPeople'
        }, {
            name: 'publishDate',
            type: 'date',
            dateFormat: 'Y-m-d H:i:s'
        }]
});
