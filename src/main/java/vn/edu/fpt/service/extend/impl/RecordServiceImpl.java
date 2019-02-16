package vn.edu.fpt.service.extend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.extend.RecordDao;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.dto.RecordDto;
import vn.edu.fpt.mapper.AbstractMapper;
import vn.edu.fpt.mapper.RecordMapper;
import vn.edu.fpt.service.extend.RecordService;
import vn.edu.fpt.service.generic.impl.GenericServiceImpl;

@Service
@Transactional
public class RecordServiceImpl extends GenericServiceImpl<Integer, RecordDto> implements RecordService {

    private RecordMapper mapper;
    private RecordDao recordDao;

    @Autowired
    public RecordServiceImpl(@Qualifier("recordDaoImpl") GenericDao genericDao,
                             @Qualifier("recordMapper") AbstractMapper abstractMapper) {
        super(genericDao, abstractMapper);
        this.mapper = (RecordMapper) abstractMapper;
        this.recordDao = (RecordDao) genericDao;
    }
}
