package vn.edu.fpt.service.generic;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.mapper.AbstractMapper;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class GenericServiceImpl<ID extends Serializable, T, M extends AbstractMapper> implements GenericService<ID, T> {

    private final Logger logger = Logger.getLogger(this.getClass());
    private GenericDao<ID, T> genericDao;
    private AbstractMapper mapper;

    public GenericServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;

        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;

        Class<M> mapperClass = (Class) parameterizedType.getActualTypeArguments()[2];
        try {
            mapper = mapperClass.newInstance();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        List<T> dtoList = new ArrayList<>();
        for (Object entity : genericDao.findAll()) {
            dtoList.add((T) mapper.entityToDto(entity));
        }
        return dtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(ID id) {
        Object entity = genericDao.findById(id);
        return (T) mapper.entityToDto(entity);
    }

    @Override
    public void save(T dto) {
        genericDao.save(dto);
    }

    @Override
    public void update(T dto) {
        genericDao.update(dto);
    }

    @Override
    public void delete(T dto) {
        genericDao.delete(dto);
    }

    @Override
    public void deleteById(ID id) {
        genericDao.deleteById(id);
    }
}
