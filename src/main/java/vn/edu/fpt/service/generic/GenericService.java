package vn.edu.fpt.service.generic;

import java.io.Serializable;
import java.util.List;

public interface GenericService<ID extends Serializable, T> {
    List<T> findAll();

    T findById(ID id);

    void save(T dto);

    void update(T dto);

    void delete(T dto);

    void deleteById(ID id);
}
