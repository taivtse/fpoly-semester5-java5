package vn.edu.fpt.service.generic;

import java.io.Serializable;
import java.util.List;

public interface GenericService<ID extends Serializable, T> {
    List<T> findAll();

    T findById(ID id);

    void save(T dto) throws Exception;

    void update(T dto) throws Exception;

    void saveOrUpdate(T dto) throws Exception;

    void delete(T dto) throws Exception;

    void deleteById(ID id) throws Exception;
}
