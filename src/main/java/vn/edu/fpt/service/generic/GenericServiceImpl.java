package vn.edu.fpt.service.generic;

import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.mapper.AbstractMapper;
import vn.edu.fpt.common.paging.Pageable;
import vn.edu.fpt.common.paging.SearchProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class GenericServiceImpl<ID extends Serializable, T> implements GenericService<ID, T> {

    private AbstractMapper mapper;
    private GenericDao<ID, Object> genericDao;

    public GenericServiceImpl(GenericDao genericDao, AbstractMapper abstractMapper) {
        this.mapper = abstractMapper;
        this.genericDao = genericDao;
    }

    protected GenericDao<ID, Object> getGenericDao() {
        return genericDao;
    }

    protected AbstractMapper getMapper() {
        return mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        List<T> dtoList = new ArrayList<>();
        genericDao.findAll().forEach(entity -> dtoList.add((T) mapper.entityToDto(entity)));
        return dtoList;
    }

    @Override
    public List<T> findByProperties(Pageable pageable, List<SearchProperty> properties) {
        List<T> dtoList = new ArrayList<>();
        genericDao.findByProperties(pageable, properties).forEach(entity -> dtoList.add((T) mapper.entityToDto(entity)));
        return dtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(ID id) {
        Object entity = genericDao.findById(id);
        return (T) mapper.entityToDto(entity);
    }

    @Override
    public T save(T dto) throws Exception {
        Object entity = mapper.dtoToEntity(dto);
        genericDao.save(entity);

        return (T) mapper.entityToDto(entity);
    }

    @Override
    public T update(T dto) throws Exception {
        Object entity = mapper.dtoToEntity(dto);
        genericDao.update(entity);

        return (T) mapper.entityToDto(entity);
    }

    @Override
    public void delete(T dto) throws Exception {
        Object entity = mapper.dtoToEntity(dto);
        genericDao.delete(entity);
    }

    @Override
    public void deleteById(ID id) throws Exception {
        genericDao.deleteById(id);
    }
}
