package vn.edu.fpt.dao.extend.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.common.paging.Pageable;
import vn.edu.fpt.common.paging.SearchProperty;
import vn.edu.fpt.dao.extend.StaffDao;
import vn.edu.fpt.dao.generic.impl.ActiveEntityDaoImpl;
import vn.edu.fpt.entity.StaffEntity;

import java.util.Arrays;
import java.util.List;

@Repository
public class StaffDaoImpl extends ActiveEntityDaoImpl<Integer, StaffEntity> implements StaffDao {
    @Override
    public List<StaffEntity> findAllByCode(Pageable pageable, String name) {
        SearchProperty property = new SearchProperty("code", name, SearchProperty.MatchType.START);
        return this.findByProperties(pageable, Arrays.asList((property)));
    }

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
