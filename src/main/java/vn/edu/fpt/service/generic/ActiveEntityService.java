package vn.edu.fpt.service.generic;

import java.io.Serializable;
import java.util.List;

public interface ActiveEntityService<ID extends Serializable, T> extends GenericService<ID, T> {
    List<T> findAllActive();

    void updateToUnActive(T dto) throws Exception;

    void updateToUnActiveById(ID id) throws Exception;

    T saveWithActiveStatus(T dto) throws Exception;

    T updateWithActiveStatus(T dto) throws Exception;
}
