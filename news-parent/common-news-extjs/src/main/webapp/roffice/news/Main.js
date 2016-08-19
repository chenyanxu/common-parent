/**
 * 公司新闻首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.roffice.news.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.roffice.news.view.NewsGrid',
        'kalix.roffice.news.view.NewsSearchForm',
        'kalix.roffice.news.viewModel.NewsViewModel'
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
