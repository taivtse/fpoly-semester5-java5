package vn.edu.fpt.service.extend.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.extend.DepartDao;
import vn.edu.fpt.dao.generic.ActiveEntityDao;
import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.mapper.AbstractMapper;
import vn.edu.fpt.mapper.DepartMapper;
import vn.edu.fpt.service.extend.DepartService;
import vn.edu.fpt.service.generic.impl.ActiveEntityServiceImpl;

@Service
@Transactional
public class DepartServiceImpl extends ActiveEntityServiceImpl<String, DepartDto> implements DepartService {

    private DepartMapper mapper;
    private DepartDao departDao;

    public DepartServiceImpl(@Qualifier("departDaoImpl") ActiveEntityDao activeEntityDao,
                             @Qualifier("departMapper") AbstractMapper abstractMapper) {
        super(activeEntityDao, abstractMapper);
        this.mapper = (DepartMapper) abstractMapper;
        this.departDao = (DepartDao) activeEntityDao;
    }
}
