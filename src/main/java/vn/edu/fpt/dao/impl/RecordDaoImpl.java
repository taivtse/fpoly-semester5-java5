package vn.edu.fpt.dao.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.dao.RecordDao;
import vn.edu.fpt.dao.generic.GenericDaoImpl;
import vn.edu.fpt.entity.RecordEntity;

@Repository
public class RecordDaoImpl extends GenericDaoImpl<Integer, RecordEntity> implements RecordDao {
}
