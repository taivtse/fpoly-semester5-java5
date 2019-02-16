package vn.edu.fpt.dao.extend;

import vn.edu.fpt.common.paging.Pageable;
import vn.edu.fpt.dao.generic.ActiveEntityDao;
import vn.edu.fpt.entity.StaffEntity;

import java.util.List;

public interface StaffDao extends ActiveEntityDao<Integer, StaffEntity> {
    List<StaffEntity> findAllByCode(Pageable pageable, String name);
    StaffEntity findByCode(String code);
    List<StaffEntity> findAllByName(String name);
}
