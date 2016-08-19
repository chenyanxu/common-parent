/**
 * 公司新闻数据仓库
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.roffice.news.store.NewsStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.roffice.news.model.NewsModel',
    alias: 'store.newsStore',
    xtype: 'newsStore',
    storeId: "newsStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/newss'
});