package vn.edu.fpt.dao.generic.extend.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.common.paging.Pageable;
import vn.edu.fpt.common.paging.SearchProperty;
import vn.edu.fpt.dao.generic.extend.StaffDao;
import vn.edu.fpt.dao.generic.impl.ActiveEntityDaoImpl;
import vn.edu.fpt.entity.StaffEntity;

import java.util.Arrays;
import java.util.List;

@Repository
public class StaffDaoImpl extends ActiveEntityDaoImpl<Integer, StaffEntity> implements StaffDao {
    @Override
    public List<StaffEntity> findAllActiveByCode(Pageable pageable, String code) {
        SearchProperty property = new SearchProperty("code", code, SearchProperty.MatchType.LIKE_START);
        SearchProperty property1 = new SearchProperty("isActive", true, SearchProperty.MatchType.EQUAL);
        return this.findAllByProperties(pageable, Arrays.asList(property, property1));
    }

    @Override
    public StaffEntity findOneActiveByCode(String code) {
        SearchProperty property = new SearchProperty("code", code, SearchProperty.MatchType.EQUAL);
        SearchProperty property1 = new SearchProperty("isActive", true, SearchProperty.MatchType.EQUAL);
        return this.findOneByProperties(Arrays.asList(property, property1));
    }

    @Override
    public List<StaffEntity> findAllActiveByName(String name) {
        SearchProperty property = new SearchProperty("name", name, SearchProperty.MatchType.LIKE_ANYWHERE);
        SearchProperty property1 = new SearchProperty("isActive", true, SearchProperty.MatchType.EQUAL);
        return this.findAllByProperties(null, Arrays.asList(property, property1));
    }
}
