package vn.edu.fpt.dao.generic.extend.impl;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.dao.generic.extend.RecordDao;
import vn.edu.fpt.dao.generic.impl.GenericDaoImpl;
import vn.edu.fpt.entity.RecordEntity;

@Repository
public class RecordDaoImpl extends GenericDaoImpl<Integer, RecordEntity> implements RecordDao {
}
