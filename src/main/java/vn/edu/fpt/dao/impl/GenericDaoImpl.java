package vn.edu.fpt.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.fpt.constant.SystemConstant;
import vn.edu.fpt.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GenericDaoImpl<ID extends Serializable, T> implements GenericDao<ID, T> {

    @Autowired
    private SessionFactory sessionFactory;

    private final Logger logger = Logger.getLogger(this.getClass());
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
        return sessionFactory.openSession();
    }

    @Override
    public List<T> findAll() {
        List list;
        Session session = this.getSession();
        try {
            list = session.createCriteria(this.getPersistenceClass()).list();
        } finally {
            session.close();
        }

        return list;
    }

    @Override
    public T findById(ID id) {
        T result = null;
        Session session = this.getSession();
        try {
            // note: the first parameter is class type, so we pass persistenceClass at this situation
            result = (T) session.get(this.getPersistenceClass(), id);
        } catch (HibernateException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            session.close();
        }
        return result;
    }

    private Object[] findByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit, boolean isFindApproximate) {
        List list;
        Long count;
        Session session = this.getSession();
        try {
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
        } finally {
            session.close();
        }
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
        try {
            // note: the first parameter is class type, so we pass persistenceClass at this situation
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.eq(property, value));
            result = (T) cr.uniqueResult();
        } catch (HibernateException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public T save(T entity) throws Exception {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public T update(T entity) throws Exception {
        T result;
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = (T) session.merge(entity);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            session.close();
        }
        return result;
    }


    @Override
    public void delete(T entity) throws Exception {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(entity);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(ID id) throws Exception {
        Session session = this.getSession();
        T entity = (T) session.get(this.getPersistenceClass(), id);
        this.delete(entity);
    }
}