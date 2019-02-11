package vn.edu.fpt.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.dao.DepartDao;
import vn.edu.fpt.dao.generic.GenericDaoImpl;
import vn.edu.fpt.entity.DepartEntity;

import java.util.List;

@Repository
public class DepartDaoImpl extends GenericDaoImpl<String, DepartEntity> implements DepartDao {
    @Override
    public List<DepartEntity> findAllActive() {
        Session session = this.getSession();
        Criteria cr = session.createCriteria(this.getPersistenceClass());
        cr.add(Restrictions.eq("isActive", true));
        return cr.list();
    }
}
