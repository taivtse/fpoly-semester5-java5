package vn.edu.fpt.service.extend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.dto.UserDto;
import vn.edu.fpt.mapper.AbstractMapper;
import vn.edu.fpt.service.extend.UserService;
import vn.edu.fpt.service.generic.impl.GenericServiceImpl;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<Integer, UserDto> implements UserService {
    private AbstractMapper mapper;
    private GenericDao userDao;

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImpl") GenericDao genericDao,
                           @Qualifier("userMapper") AbstractMapper abstractMapper) {
        super(genericDao, abstractMapper);
        this.mapper = abstractMapper;
        this.userDao = genericDao;
    }
}
