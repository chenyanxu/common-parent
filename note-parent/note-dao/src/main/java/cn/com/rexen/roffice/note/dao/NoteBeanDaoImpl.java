package cn.com.rexen.roffice.note.dao;

import cn.com.rexen.core.impl.persistence.GenericDao;
import cn.com.rexen.roffice.note.api.dao.INoteBeanDao;
import cn.com.rexen.roffice.note.entities.NoteBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class NoteBeanDaoImpl extends GenericDao<NoteBean, Long> implements INoteBeanDao {
    @Override
    @PersistenceContext(unitName = "note-cm")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }

}
