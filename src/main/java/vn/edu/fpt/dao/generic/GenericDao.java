package vn.edu.fpt.dao.generic;

import vn.edu.fpt.common.paging.Pageable;
import vn.edu.fpt.common.paging.SearchProperty;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<ID extends Serializable, T> {
    List<T> findAll();

    T findById(ID id);

    List<T> findByProperties(Pageable pageable, List<SearchProperty> properties);

    Long countByProperties(Pageable pageable, List<SearchProperty> properties);

    T findUniqueEqual(String property, Object value);

    void save(T entity) throws Exception ;

    void update(T entity) throws Exception;

    void delete(T entity) throws Exception;

    void deleteById(ID id) throws Exception;
}
