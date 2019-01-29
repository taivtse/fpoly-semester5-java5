package vn.edu.fpt.service.generic;

import java.io.Serializable;
import java.util.List;

public interface GenericService<ID extends Serializable, T> {
    List<T> findAll();

    T findById(ID id);

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(ID id);
}
