package vn.edu.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.StaffDao;
import vn.edu.fpt.dao.generic.ActiveEntityDao;
import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.entity.StaffEntity;
import vn.edu.fpt.mapper.AbstractMapper;
import vn.edu.fpt.mapper.StaffMapper;
import vn.edu.fpt.service.StaffService;
import vn.edu.fpt.service.generic.ActiveEntityServiceImpl;

@Service
@Transactional
public class StaffServiceImpl extends ActiveEntityServiceImpl<Integer, StaffDto> implements StaffService {

    private StaffMapper mapper;
    private StaffDao staffDao;

    @Autowired
    public StaffServiceImpl(@Qualifier("staffDaoImpl") ActiveEntityDao activeEntityDao,
                            @Qualifier("staffMapper") AbstractMapper abstractMapper) {
        super(activeEntityDao, abstractMapper);
        this.mapper = (StaffMapper) abstractMapper;
        this.staffDao = (StaffDao) activeEntityDao;
    }

    @Override
    public StaffDto getByCode(String code) {
        StaffEntity entity = staffDao.getByCode(code);
        if (entity != null)
            return mapper.entityToDto(entity);
        return null;
    }
}
