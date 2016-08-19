package com.kalix.common.note.dao;

import com.kalix.common.note.api.dao.INoteBeanDao;
import com.kalix.common.note.entities.NoteBean;
import com.kalix.framework.core.impl.dao.GenericDao;

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
