package vn.edu.fpt.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.DepartDao;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.mapper.DepartMapper;
import vn.edu.fpt.service.DepartService;
import vn.edu.fpt.service.generic.GenericServiceImpl;

@Service
@Transactional
public class DepartServiceImpl extends GenericServiceImpl<String, DepartDto, DepartMapper> implements DepartService {
    private DepartDao departDao;

    public DepartServiceImpl(@Qualifier("departDaoImpl") GenericDao genericDao) {
        super(genericDao);
        this.departDao = (DepartDao) genericDao;
    }
}
