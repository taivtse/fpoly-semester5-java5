package vn.edu.fpt.service.generic;

import java.io.Serializable;
import java.util.List;

public interface GenericService<ID extends Serializable, T> {
    List<T> findAll();

    T findById(ID id);

    T save(T dto) throws Exception;

    T update(T dto) throws Exception;

    void delete(T dto) throws Exception;

    void deleteById(ID id) throws Exception;
}
