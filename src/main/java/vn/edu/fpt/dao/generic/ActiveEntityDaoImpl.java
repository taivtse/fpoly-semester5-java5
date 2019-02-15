package vn.edu.fpt.dao.generic;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

public class ActiveEntityDaoImpl<ID extends Serializable, T> extends GenericDaoImpl<ID, T> implements ActiveEntityDao<ID, T> {

    @Override
    public List<T> findAllActive() {
        Session session = this.getSession();
        Criteria cr = session.createCriteria(this.getPersistenceClass());
        cr.add(Restrictions.eq("isActive", true));
        return cr.list();
    }
}
