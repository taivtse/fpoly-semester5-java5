package vn.edu.fpt.dao;

import vn.edu.fpt.dao.generic.ActiveEntityDao;
import vn.edu.fpt.entity.StaffEntity;

import java.util.List;

public interface StaffDao extends ActiveEntityDao<Integer, StaffEntity> {
    StaffEntity findByCode(String code);
    List<StaffEntity> findAllByName(String name);
}
