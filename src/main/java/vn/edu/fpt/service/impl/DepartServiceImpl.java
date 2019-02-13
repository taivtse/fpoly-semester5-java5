package vn.edu.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.DepartDao;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.entity.DepartEntity;
import vn.edu.fpt.mapper.DepartMapper;
import vn.edu.fpt.service.DepartService;
import vn.edu.fpt.service.generic.GenericServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DepartServiceImpl extends GenericServiceImpl<String, DepartDto, DepartMapper> implements DepartService {

    @Autowired
    private DepartMapper mapper;

    private DepartDao departDao;

    public DepartServiceImpl(@Qualifier("departDaoImpl") GenericDao genericDao) {
        super(genericDao);
        this.departDao = (DepartDao) genericDao;
    }

    @Override
    public List<DepartDto> findAllActive() {
        List<DepartDto> dtoList = new ArrayList<>();
        departDao.findAllActive().forEach(departEntity -> {
            dtoList.add(mapper.entityToDto(departEntity));
        });
        return dtoList;
    }

    @Override
    public DepartDto save(DepartDto dto) throws Exception {
//        processing before save
        dto.setActive(true);

        DepartEntity entity = mapper.dtoToEntity(dto);
        departDao.save(entity);
        return dto;
    }

    @Override
    public DepartDto update(DepartDto dto) throws Exception {
//        processing before update
        dto.setActive(true);

        DepartEntity entity = mapper.dtoToEntity(dto);
        departDao.update(entity);
        return dto;
    }

    @Override
    public void delete(DepartDto dto) throws Exception {
//        mark as deleted
        dto.setActive(false);

        DepartEntity entity = mapper.dtoToEntity(dto);
        departDao.update(entity);
    }

    @Override
    public void deleteById(String id) throws Exception {
        DepartEntity entity = departDao.findById(id);
        DepartDto dto = mapper.entityToDto(entity);
        this.delete(dto);
    }
}
