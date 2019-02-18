package vn.edu.fpt.service.generic;

import vn.edu.fpt.common.paging.Pageable;
import vn.edu.fpt.common.paging.SearchProperty;
import vn.edu.fpt.dto.DtoMarker;

import java.io.Serializable;
import java.util.List;

public interface GenericService<ID extends Serializable, T extends DtoMarker<ID>> {
    List<T> findAll();

    List<T> findAllByProperties(Pageable pageable, List<SearchProperty> properties);

    T findOneById(ID id);

    T save(T dto) throws Exception;

    T update(T dto) throws Exception;

    void delete(T dto) throws Exception;

    void deleteById(ID id) throws Exception;
}
