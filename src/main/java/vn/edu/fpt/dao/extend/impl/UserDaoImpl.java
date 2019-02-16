package vn.edu.fpt.dao.extend.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.dao.extend.UserDao;
import vn.edu.fpt.dao.generic.impl.GenericDaoImpl;
import vn.edu.fpt.entity.UserEntity;

@Repository
public class UserDaoImpl extends GenericDaoImpl<Integer, UserEntity> implements UserDao {
}
