package vn.edu.fpt.dao.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.dao.StaffDao;
import vn.edu.fpt.dao.generic.GenericDaoImpl;
import vn.edu.fpt.entity.StaffEntity;

@Repository
public class StaffDaoImpl extends GenericDaoImpl<Integer, StaffEntity> implements StaffDao {
}
