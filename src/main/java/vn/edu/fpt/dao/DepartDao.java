package vn.edu.fpt.dao;

import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.entity.DepartEntity;

import java.util.List;

public interface DepartDao extends GenericDao<String, DepartEntity> {
    List<DepartEntity> findAllActive();
}
