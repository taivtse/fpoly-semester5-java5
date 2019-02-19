package vn.edu.fpt.dao.generic.extend.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.common.paging.SearchProperty;
import vn.edu.fpt.dao.generic.extend.UserDao;
import vn.edu.fpt.dao.generic.impl.GenericDaoImpl;
import vn.edu.fpt.entity.UserEntity;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserDaoImpl extends GenericDaoImpl<Integer, UserEntity> implements UserDao {
    @Override
    public List<UserEntity> findAllByFullName(String name) {
        SearchProperty property = new SearchProperty("fullName", name, SearchProperty.MatchType.LIKE_ANYWHERE);
        return this.findAllByProperties(null, Arrays.asList(property));
    }

    @Override
    public UserEntity findOneByUsername(String username) {
        SearchProperty property = new SearchProperty("username", username, SearchProperty.MatchType.EQUAL);
        return this.findOneByProperties(Arrays.asList(property));
    }
}
