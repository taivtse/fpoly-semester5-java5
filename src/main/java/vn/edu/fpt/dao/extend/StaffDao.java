package vn.edu.fpt.dao.extend;

import vn.edu.fpt.common.paging.Pageable;
import vn.edu.fpt.dao.generic.ActiveEntityDao;
import vn.edu.fpt.entity.StaffEntity;

import java.util.List;

public interface StaffDao extends ActiveEntityDao<Integer, StaffEntity> {
    List<StaffEntity> findAllActiveByCode(Pageable pageable, String code);
    StaffEntity findOneActiveByCode(String code);
    List<StaffEntity> findAllActiveByName(String name);
}
