package vn.edu.fpt.service.extend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.extend.UserDao;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.dto.UserDto;
import vn.edu.fpt.entity.UserEntity;
import vn.edu.fpt.mapper.AbstractMapper;
import vn.edu.fpt.mapper.UserMapper;
import vn.edu.fpt.service.extend.UserService;
import vn.edu.fpt.service.generic.impl.GenericServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<Integer, UserDto> implements UserService {
    private UserMapper mapper;
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImpl") GenericDao genericDao,
                           @Qualifier("userMapper") AbstractMapper abstractMapper) {
        super(genericDao, abstractMapper);
        this.mapper = (UserMapper) abstractMapper;
        this.userDao = (UserDao) genericDao;
    }

    @Override
    public List<UserDto> findAllByFullName(String name) {
        List<UserDto> dtoList = new ArrayList<>();
        userDao.findAllByFullName(name).forEach(userEntity -> dtoList.add(mapper.entityToDto(userEntity)));
        return dtoList;
    }

    @Override
    public UserDto findOneByUsername(String username) {
        UserEntity entity = userDao.findOneByUsername(username);
        if (entity != null)
            return mapper.entityToDto(entity);
        return null;
    }
}
