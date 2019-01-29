package vn.edu.fpt.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<ID extends Serializable, T> {
    List<T> findAll();

    T findById(ID id);

    Object[] findApproximateByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit);

    Object[] findExactlyByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, Integer offset, Integer limit);

    T findUniqueEqual(String property, Object value);

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(ID id);
}
