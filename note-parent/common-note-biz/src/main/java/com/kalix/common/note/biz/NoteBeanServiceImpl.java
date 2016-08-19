package com.kalix.common.note.biz;

import com.kalix.common.note.api.biz.INoteBeanService;
import com.kalix.common.note.api.dao.INoteBeanDao;
import com.kalix.common.note.entities.NoteBean;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.security.IShiroService;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;
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
public class NoteBeanServiceImpl extends GenericBizServiceImpl<INoteBeanDao, NoteBean> implements INoteBeanService {
    private JsonStatus jsonStatus = new JsonStatus();
    private IShiroService shiroService;

    public NoteBeanServiceImpl() {
        super.init(NoteBean.class.getName());
    }

    @Override
    public void beforeSaveEntity(NoteBean entity, JsonStatus status) {
        String userName = shiroService.getCurrentUserRealName();
        Assert.notNull(userName, "用户名不能为空.");
        if (StringUtils.isNotEmpty(userName)) {
            entity.setPublishPeople(userName);
            entity.setPublishDate(new Date());
        }
    }

    public void setShiroService(IShiroService shiroService) {
        this.shiroService = shiroService;
    }
}
