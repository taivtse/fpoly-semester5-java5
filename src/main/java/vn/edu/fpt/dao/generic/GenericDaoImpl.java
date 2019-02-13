package vn.edu.fpt.dao.generic;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.fpt.constant.SystemConstant;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GenericDaoImpl<ID extends Serializable, T> implements GenericDao<ID, T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> persistenceClass;

    public GenericDaoImpl() {
        // generic < x , y > as array
        // set persistenceClass = T
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        persistenceClass = (Class) parameterizedType.getActualTypeArguments()[1];
    }

    protected Class<T> getPersistenceClass() {
        return this.persistenceClass;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<T> findAll() {
        List list;
        Session session = this.getSession();
        list = session.createCriteria(this.getPersistenceClass()).list();
        return list;
    }

    @Override
    public T findById(ID id) {
        T result;
        Session session = this.getSession();
        result = (T) session.get(this.getPersistenceClass(), id);
        return result;
    }

    private Object[] findByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit, boolean isFindApproximate) {
        List list;
        Long count;
        Session session = this.getSession();
        Criteria cr = session.createCriteria(this.getPersistenceClass());
        Criteria cr2 = session.createCriteria(this.getPersistenceClass());

//            set condition for query
        if (properties != null) {
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                if (isFindApproximate) {
                    cr.add(Restrictions.like(entry.getKey(), entry.getValue().toString(), MatchMode.ANYWHERE));
                    cr2.add(Restrictions.like(entry.getKey(), entry.getValue().toString(), MatchMode.ANYWHERE));
                } else {
                    cr.add(Restrictions.eq(entry.getKey(), entry.getValue()));
                    cr2.add(Restrictions.eq(entry.getKey(), entry.getValue()));
                }
            }
        }

//            set sort direction
        if (sortExpression != null && sortDirection != null) {
            Order order = sortDirection.equals(SystemConstant.SORT_ASC) ?
                    Order.asc(sortExpression) : Order.desc(sortExpression);
            cr.addOrder(order);
        }

//            set start position offset
        if (offset != null && offset >= 0) {
            cr.setFirstResult(offset);
        }

//            set limit row
        if (limit != null && limit > 0) {
            cr.setMaxResults(limit);
        }

        list = cr.list();

//            count total of items
//            set result is a num row of list
        cr2.setProjection(Projections.rowCount());
        count = (Long) cr2.uniqueResult();

        return new Object[]{count, list};
    }

    @Override
    public Object[] findApproximateByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        return this.findByProperties(properties, sortExpression, sortDirection, offset, limit, true);
    }

    @Override
    public Object[] findExactlyByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        return this.findByProperties(properties, sortExpression, sortDirection, offset, limit, false);
    }

    @Override
    public T findUniqueEqual(String property, Object value) {
        T result;
        Session session = this.getSession();
        Criteria cr = session.createCriteria(this.getPersistenceClass());
        cr.add(Restrictions.eq(property, value));
        result = (T) cr.uniqueResult();
        return result;
    }

    @Override
    public void save(T entity) throws Exception {
        Session session = this.getSession();
        session.save(entity);
        session.flush();
        session.refresh(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        Session session = this.getSession();
        try {
            session.update(entity);
            session.flush();
            session.refresh(entity);
        } catch (Exception e) {
            if (e instanceof NonUniqueObjectException) {
                entity = (T) session.merge(entity);
                session.update(entity);
                return;
            }
            throw e;
        }
    }

    @Override
    public void delete(T entity) throws Exception {
        Session session = this.getSession();
        try {
            session.delete(entity);
            session.flush();
        } catch (Exception e) {
            if (e instanceof NonUniqueObjectException) {
                entity = (T) session.merge(entity);
                session.delete(entity);
                return;
            }
            throw e;
        }
    }

    @Override
    public void deleteById(ID id) throws Exception {
        Session session = this.getSession();
        T entity = (T) session.get(this.getPersistenceClass(), id);
        this.delete(entity);
    }
}