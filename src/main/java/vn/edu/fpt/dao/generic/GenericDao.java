package vn.edu.fpt.dao.generic;

import vn.edu.fpt.common.paging.Pageable;
import vn.edu.fpt.common.paging.SearchProperty;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<ID extends Serializable, T> {
    List<T> findAll();

    T findOneById(ID id);

    List<T> findAllByProperties(Pageable pageable, List<SearchProperty> properties);

    Long countByProperties(List<SearchProperty> properties);

    T findOneByProperties(List<SearchProperty> properties);

    void save(T entity) throws Exception ;

    void update(T entity) throws Exception;

    void delete(T entity) throws Exception;

    void deleteById(ID id) throws Exception;
}
