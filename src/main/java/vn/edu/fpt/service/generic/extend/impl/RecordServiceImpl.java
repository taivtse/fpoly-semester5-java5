package vn.edu.fpt.service.generic.extend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.generic.extend.RecordDao;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.dto.RecordDto;
import vn.edu.fpt.mapper.AbstractMapper;
import vn.edu.fpt.mapper.RecordMapper;
import vn.edu.fpt.service.generic.extend.RecordService;
import vn.edu.fpt.service.generic.impl.GenericServiceImpl;

import java.util.Date;

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

    @Override
    public RecordDto save(RecordDto dto) throws Exception {
        dto.setSubmitDate(new Date());
        return super.save(dto);
    }

    @Override
    public RecordDto update(RecordDto dto) throws Exception {
        RecordDto oldDto = this.findOneById(dto.getId());
        dto.setSubmitDate(oldDto.getSubmitDate());
        return super.update(dto);
    }
}
