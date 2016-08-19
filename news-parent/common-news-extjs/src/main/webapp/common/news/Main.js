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
        'kalix.common.news.view.NewsSearchForm',
        'kalix.common.news.viewModel.NewsViewModel'
    ],
    storeId: 'newsStore',
    viewModel: 'newsViewModel',
    items: [
        {
            title: '公司新闻查询',
            iconCls: 'x-fa fa-search',
            xtype: 'newsSearchForm'
        }, {
            xtype: 'newsGridPanel',
            id: 'newsGridPanel',
            title: '公司新闻列表',
            iconCls: 'x-fa fa-search',
            margin: 10
        }
    ]
});
