package vn.edu.fpt.dao.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.dao.DepartDao;
import vn.edu.fpt.dao.generic.ActiveEntityDaoImpl;
import vn.edu.fpt.entity.DepartEntity;

@Repository
public class DepartDaoImpl extends ActiveEntityDaoImpl<String, DepartEntity> implements DepartDao {
}
