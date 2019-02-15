package vn.edu.fpt.dao.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.common.paging.SearchProperty;
import vn.edu.fpt.dao.StaffDao;
import vn.edu.fpt.dao.generic.ActiveEntityDaoImpl;
import vn.edu.fpt.entity.StaffEntity;

import java.util.Arrays;
import java.util.List;

@Repository
public class StaffDaoImpl extends ActiveEntityDaoImpl<Integer, StaffEntity> implements StaffDao {
    @Override
    public StaffEntity findByCode(String code) {
        return this.findUniqueEqual("code", code);
    }

    @Override
    public List<StaffEntity> findAllByName(String name) {
        SearchProperty property = new SearchProperty("name", name, SearchProperty.MatchType.ANYWHERE);
        return this.findByProperties(null, Arrays.asList((property)));
    }
}
