package cn.com.rexen.roffice.note.core.biz;

import cn.com.rexen.core.api.biz.JsonStatus;
import cn.com.rexen.core.api.security.IShiroService;
import cn.com.rexen.core.impl.biz.GenericBizServiceImpl;
import cn.com.rexen.core.util.Assert;
import cn.com.rexen.roffice.note.api.biz.INoteBeanService;
import cn.com.rexen.roffice.note.api.dao.INoteBeanDao;
import cn.com.rexen.roffice.note.entities.NoteBean;
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
        String userName = shiroService.getCurrentUserName();
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
