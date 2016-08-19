package com.kalix.common.news.biz;

import cn.com.rexen.audit.core.biz.AuditBizServiceImpl;
import com.kalix.common.news.api.biz.INewsBeanService;
import com.kalix.common.news.api.dao.INewsBeanDao;
import com.kalix.common.news.entities.NewsBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.util.Assert;
import org.apache.commons.lang.StringUtils;

import javax.transaction.TransactionScoped;
import java.util.Date;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class NewsBeanServiceImpl extends AuditBizServiceImpl<INewsBeanDao, NewsBean> implements INewsBeanService {
    private JsonStatus jsonStatus = new JsonStatus();
//    private IShiroService shiroService;

    @Override
    @TransactionScoped
    public void beforeSaveEntity(NewsBean entity, JsonStatus status) {
        //添加时候，记录操作人和时间
//        if (entity.getId() == -1) {
        String userName = shiroService.getCurrentUserName();
        Assert.notNull(userName, "用户名不能为空.");
        if (StringUtils.isNotEmpty(userName)) {
            entity.setPublishPeople(userName);
            entity.setPublishDate(new Date());
        }
        super.beforeSaveEntity(entity, status);
//        throw new RuntimeException("test");
//        }
    }


    public NewsBeanServiceImpl() {
        super.init(NewsBean.class.getName());
    }

//    public void setShiroService(IShiroService shiroService) {
//        this.shiroService = shiroService;
//    }

    @Override
    public String getAppName() {
        return Const.APPLICATION_ALIAS;
    }

    @Override
    public String getFunName() {
        return Const.NEWS_MENU_TEXT;
    }
}
