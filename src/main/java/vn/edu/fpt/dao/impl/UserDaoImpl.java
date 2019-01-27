package vn.edu.fpt.dao.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.dao.UserDao;
import vn.edu.fpt.entity.UserEntity;

@Repository
public class UserDaoImpl extends GenericDaoImpl<Integer, UserEntity> implements UserDao {

}
