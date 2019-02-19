package vn.edu.fpt.dao.generic.extend;

import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.entity.UserEntity;

import java.util.List;

public interface UserDao extends GenericDao<Integer, UserEntity> {
    List<UserEntity> findAllByFullName(String name);
    UserEntity findOneByUsername(String username);
}
