package vn.edu.fpt.dao;

import vn.edu.fpt.dao.generic.ActiveEntityDao;
import vn.edu.fpt.entity.StaffEntity;

public interface StaffDao extends ActiveEntityDao<Integer, StaffEntity> {
    StaffEntity getByCode(String code);
}
