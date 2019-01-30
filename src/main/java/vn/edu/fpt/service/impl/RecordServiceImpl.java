package vn.edu.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.RecordDao;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.dto.RecordDto;
import vn.edu.fpt.mapper.RecordMapper;
import vn.edu.fpt.service.RecordService;
import vn.edu.fpt.service.generic.GenericServiceImpl;

@Service
@Transactional
public class RecordServiceImpl extends GenericServiceImpl<Integer, RecordDto, RecordMapper> implements RecordService {
    private RecordDao recordDao;

    @Autowired
    public RecordServiceImpl(@Qualifier("recordDaoImpl") GenericDao genericDao) {
        super(genericDao);
        this.recordDao = (RecordDao) genericDao;
    }
}
