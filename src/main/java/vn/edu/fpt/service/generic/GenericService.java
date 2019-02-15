package vn.edu.fpt.service.generic;

import vn.edu.fpt.common.paging.Pageable;
import vn.edu.fpt.common.paging.SearchProperty;

import java.io.Serializable;
import java.util.List;

public interface GenericService<ID extends Serializable, T> {
    List<T> findAll();

    List<T> findByProperties(Pageable pageable, List<SearchProperty> properties);

    T findById(ID id);

    T save(T dto) throws Exception;

    T update(T dto) throws Exception;

    void delete(T dto) throws Exception;

    void deleteById(ID id) throws Exception;
}
