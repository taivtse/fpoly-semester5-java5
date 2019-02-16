package vn.edu.fpt.dao.generic.impl;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.fpt.common.paging.Pageable;
import vn.edu.fpt.common.paging.SearchProperty;
import vn.edu.fpt.dao.generic.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

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

    final protected Class<T> getPersistenceClass() {
        return this.persistenceClass;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    final public List<T> findAll() {
        List list;
        Session session = this.getSession();
        list = session.createCriteria(this.getPersistenceClass()).list();
        return list;
    }

    @Override
    final public T findById(ID id) {
        T result;
        Session session = this.getSession();
        result = (T) session.get(this.getPersistenceClass(), id);
        return result;
    }

    @Override
    public List<T> findByProperties(Pageable pageable, List<SearchProperty> properties) {
        Session session = this.getSession();
        Criteria cr = session.createCriteria(this.getPersistenceClass());

        if (pageable != null) {
//            set start position offset
            if (pageable.getOffset() != null && pageable.getOffset() >= 0) {
                cr.setFirstResult(pageable.getOffset());
            }

//            set limit row
            if (pageable.getLimit() != null && pageable.getLimit() >= 0) {
                cr.setMaxResults(pageable.getLimit());
            }

//            set sorter
            if (pageable.getSorter() != null
                    && !pageable.getSorter().getPropertyName().isEmpty()
                    && !pageable.getSorter().getDirection().isEmpty()) {
                cr.addOrder(pageable.getSorter().getOrder());
            }
        }

//        set properties search
        if (properties != null) {
            properties.forEach(searchProperty -> cr.add(searchProperty.getCriterion()));
        }

        return cr.list();
    }

    @Override
    public Long countByProperties(Pageable pageable, List<SearchProperty> properties) {
        Session session = this.getSession();
        Criteria cr = session.createCriteria(this.getPersistenceClass());

        if (pageable != null) {
//            set start position offset
            if (pageable.getOffset() != null && pageable.getOffset() >= 0) {
                cr.setFirstResult(pageable.getOffset());
            }

//            set limit row
            if (pageable.getLimit() != null && pageable.getLimit() >= 0) {
                cr.setMaxResults(pageable.getLimit());
            }

//            set sorter
            if (pageable.getSorter() != null) {
                cr.addOrder(pageable.getSorter().getOrder());
            }
        }

//        set properties search
        if (properties != null) {
            properties.forEach(searchProperty -> cr.add(searchProperty.getCriterion()));
        }

        cr.setProjection(Projections.rowCount());
        return (Long) cr.uniqueResult();
    }

    @Override
    final public T findUniqueEqual(String property, Object value) {
        T result;
        Session session = this.getSession();
        Criteria cr = session.createCriteria(this.getPersistenceClass());
        cr.add(Restrictions.eq(property, value));
        result = (T) cr.uniqueResult();
        return result;
    }

    @Override
    final public void save(T entity) throws Exception {
        Session session = this.getSession();
        session.save(entity);
        session.flush();
        session.refresh(entity);
    }

    @Override
    final public void update(T entity) throws Exception {
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
    final public void delete(T entity) throws Exception {
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
    final public void deleteById(ID id) throws Exception {
        Session session = this.getSession();
        T entity = (T) session.get(this.getPersistenceClass(), id);
        this.delete(entity);
    }
}