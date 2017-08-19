package com.kalix.common.news.biz;

import com.kalix.common.news.api.biz.INewsBeanService;
import com.kalix.common.news.api.dao.INewsBeanDao;
import com.kalix.common.news.entities.NewsBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class NewsBeanServiceImpl extends ShiroGenericBizServiceImpl<INewsBeanDao, NewsBean> implements INewsBeanService {
    @Override
    public void beforeSaveEntity(NewsBean entity, JsonStatus status) {
        String userName = shiroService.getCurrentUserRealName();
        Assert.notNull(userName, "用户名不能为空.");
        if (StringUtils.isNotEmpty(userName)) {
            entity.setPublishPeople(userName);
            entity.setPublishDate(new Date());
        }
    }
}
