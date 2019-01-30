package vn.edu.fpt.dao.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.dao.DepartDao;
import vn.edu.fpt.dao.generic.GenericDaoImpl;
import vn.edu.fpt.entity.DepartEntity;

@Repository
public class DepartDaoImpl extends GenericDaoImpl<String, DepartEntity> implements DepartDao {
}
