package vn.edu.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.UserDao;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.dto.UserDto;
import vn.edu.fpt.mapper.UserMapper;
import vn.edu.fpt.service.UserService;
import vn.edu.fpt.service.generic.GenericServiceImpl;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<Integer, UserDto, UserMapper> implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImpl") GenericDao genericDao) {
        super(genericDao);
        this.userDao = (UserDao) genericDao;
    }
}
