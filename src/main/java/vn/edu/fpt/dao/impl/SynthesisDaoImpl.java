package vn.edu.fpt.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.dao.SynthesisDao;

import java.util.List;

@Repository
public class SynthesisDaoImpl implements SynthesisDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Object[]> getStaffSynthesis() {
        Session session = this.getSession();
        String hql = "SELECT r.staffEntity.code, " +
                "SUM(CASE WHEN r.type='reward' THEN 1 ELSE 0 END), " +
                "SUM(CASE WHEN r.type='punishment' THEN 1 ELSE 0 END) " +
                "FROM RecordEntity r GROUP BY r.staffEntity.id";
        return session.createQuery(hql).list();
    }

    @Override
    public Object[] getStaffSynthesisByCode(String staffCode) {
        Session session = this.getSession();
        String hql = "SELECT r.staffEntity.code, " +
                "SUM(CASE WHEN r.type='reward' THEN 1 ELSE 0 END), " +
                "SUM(CASE WHEN r.type='punishment' THEN 1 ELSE 0 END) " +
                "FROM RecordEntity r WHERE r.staffEntity.code = ? " +
                "GROUP BY r.staffEntity.id";
        Query query = session.createQuery(hql);
        query.setString(0, staffCode);
        return (Object[]) query.uniqueResult();
    }
}
