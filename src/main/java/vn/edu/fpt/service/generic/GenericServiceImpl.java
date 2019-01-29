package vn.edu.fpt.service.generic;

import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.generic.GenericDao;

import java.io.Serializable;
import java.util.List;

@Transactional
public class GenericServiceImpl<ID extends Serializable, T> implements GenericService<ID, T> {

    private GenericDao<ID, T> genericDao;

    public GenericServiceImpl(GenericDao<ID, T> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return genericDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(ID id) {
        return genericDao.findById(id);
    }

    @Override
    public void save(T entity) {
        genericDao.save(entity);
    }

    @Override
    public void update(T entity) {
        genericDao.update(entity);
    }

    @Override
    public void delete(T entity) {
        genericDao.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        genericDao.deleteById(id);
    }
}
