package vn.edu.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.StaffDao;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.entity.StaffEntity;
import vn.edu.fpt.mapper.StaffMapper;
import vn.edu.fpt.service.StaffService;
import vn.edu.fpt.service.generic.GenericServiceImpl;

@Service
@Transactional
public class StaffServiceImpl extends GenericServiceImpl<Integer, StaffDto, StaffMapper> implements StaffService {

    @Autowired
    private StaffMapper mapper;

    private StaffDao staffDao;

    @Autowired
    public StaffServiceImpl(@Qualifier("staffDaoImpl") GenericDao genericDao) {
        super(genericDao);
        this.staffDao = (StaffDao) genericDao;
    }

    @Override
    public StaffDto getByCode(String code) {
        StaffEntity entity = this.staffDao.findUniqueEqual("code", code);
        return mapper.entityToDto(entity);
    }
}
