/**
 * 公司新闻首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.common.news.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.common.news.view.NewsGrid',
        'kalix.common.news.view.NewsSearchForm'
    ],
    items: [
        {
            title: '公司新闻查询',
            xtype: 'newsSearchForm'
        }, {
            xtype: 'newsGridPanel',
            id: 'newsGridPanel',
            title: '公司新闻列表',
        }
    ]
});
