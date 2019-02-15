package vn.edu.fpt.dao.generic;

import java.io.Serializable;
import java.util.List;

public interface ActiveEntityDao<ID extends Serializable, T> extends GenericDao<ID, T> {
    List<T> findAllActive();
}
