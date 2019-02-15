package vn.edu.fpt.dao.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.dao.StaffDao;
import vn.edu.fpt.dao.generic.ActiveEntityDaoImpl;
import vn.edu.fpt.entity.StaffEntity;

@Repository
public class StaffDaoImpl extends ActiveEntityDaoImpl<Integer, StaffEntity> implements StaffDao {
    @Override
    public StaffEntity getByCode(String code) {
        return this.findUniqueEqual("code", code);
    }
}
