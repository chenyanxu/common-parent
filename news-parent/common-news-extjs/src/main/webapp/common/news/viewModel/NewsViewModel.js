/**
 * 公司新闻模型
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.common.news.viewModel.NewsViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.newsViewModel',
    data: {
        rec: null,
        validation: {},  //验证错误信息
        icon: '',
        title: '',
        view_operation: false,
        view_title: '查看公司新闻',
        add_title: '添加公司新闻',
        edit_title: '修改公司新闻',
        add_image_path: CONFIG.restRoot + '/common/news/resources/images/news_add.png',
        view_image_path: CONFIG.restRoot + '/common/news/resources/images/news_view.png',
        edit_image_path: CONFIG.restRoot + '/common/news/resources/images/news_edit.png',
    }
});