package vn.edu.fpt.service.generic;

import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.generic.ActiveEntityDao;
import vn.edu.fpt.dto.generic.ActiveGenericDto;
import vn.edu.fpt.mapper.AbstractMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class ActiveEntityServiceImpl<ID extends Serializable, T extends ActiveGenericDto> extends GenericServiceImpl<ID, T> implements ActiveEntityService<ID, T> {

    public ActiveEntityServiceImpl(ActiveEntityDao activeEntityDao, AbstractMapper abstractMapper) {
        super(activeEntityDao, abstractMapper);
    }

    private ActiveEntityDao getActiveEntityDao() {
        return (ActiveEntityDao) this.getGenericDao();
    }

    @Override
    public List<T> findAllActive() {
        List<T> dtoList = new ArrayList<>();
        this.getActiveEntityDao().findAllActive().forEach(entity -> {
            dtoList.add((T) this.getMapper().entityToDto(entity));
        });
        return dtoList;
    }

    @Override
    public void updateToUnActive(T dto) throws Exception {
        dto.setActive(false);

        this.getActiveEntityDao().update(this.getMapper().dtoToEntity(dto));
    }

    @Override
    public void updateToUnActiveById(ID id) throws Exception {
        Object entity = this.getGenericDao().findById(id);

        this.updateToUnActive((T) this.getMapper().entityToDto(entity));
    }

    @Override
    public T saveWithActiveStatus(T dto) throws Exception {
        dto.setActive(true);
        return super.save(dto);
    }

    @Override
    public T updateWithActiveStatus(T dto) throws Exception {
        dto.setActive(true);
        return super.update(dto);
    }
}
