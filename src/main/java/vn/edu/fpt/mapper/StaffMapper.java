package vn.edu.fpt.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.entity.StaffEntity;

@Component
public class StaffMapper implements AbstractMapper<StaffDto, StaffEntity> {
    @Override
    public StaffDto entityToDto(StaffEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<StaffEntity, StaffDto> orderMap = new PropertyMap<StaffEntity, StaffDto>() {
            protected void configure() {
                map(source.getDepartEntity(), destination.getDepartDto());
            }
        };
        modelMapper.addMappings(orderMap);
        return modelMapper.map(entity, StaffDto.class);
    }

    @Override
    public StaffEntity dtoToEntity(StaffDto dto) {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<StaffDto, StaffEntity> orderMap = new PropertyMap<StaffDto, StaffEntity>() {
            protected void configure() {
                map(source.getDepartDto(), destination.getDepartEntity());
            }
        };
        modelMapper.addMappings(orderMap);

        return modelMapper.map(dto, StaffEntity.class);
    }
}
