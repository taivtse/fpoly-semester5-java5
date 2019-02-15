package vn.edu.fpt.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.entity.DepartEntity;

@Component
public class DepartMapper implements AbstractMapper<DepartDto, DepartEntity> {
    @Override
    public DepartDto entityToDto(DepartEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, DepartDto.class);
    }

    @Override
    public DepartEntity dtoToEntity(DepartDto dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, DepartEntity.class);
    }
}
