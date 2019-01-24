package vn.edu.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.UserDao;
import vn.edu.fpt.entity.UserEntity;
import vn.edu.fpt.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<UserEntity> findAll() {
        return userDao.findAll();
    }
}
