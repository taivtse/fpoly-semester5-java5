package vn.edu.fpt.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.DepartDao;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.entity.DepartEntity;
import vn.edu.fpt.service.DepartService;
import vn.edu.fpt.service.generic.GenericServiceImpl;

@Service
@Transactional
public class DepartServiceImpl extends GenericServiceImpl<String, DepartEntity> implements DepartService {
    private DepartDao departDao;

    public DepartServiceImpl(@Qualifier("departDaoImpl") GenericDao<String, DepartEntity> genericDao) {
        super(genericDao);
        this.departDao = (DepartDao) genericDao;
    }
}
