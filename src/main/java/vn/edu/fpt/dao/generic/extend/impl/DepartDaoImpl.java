package vn.edu.fpt.dao.generic.extend.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.dao.generic.extend.DepartDao;
import vn.edu.fpt.dao.generic.impl.ActiveEntityDaoImpl;
import vn.edu.fpt.entity.DepartEntity;

@Repository
public class DepartDaoImpl extends ActiveEntityDaoImpl<String, DepartEntity> implements DepartDao {
}
