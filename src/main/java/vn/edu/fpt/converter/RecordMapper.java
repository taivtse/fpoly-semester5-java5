package vn.edu.fpt.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import vn.edu.fpt.dto.RecordDto;
import vn.edu.fpt.entity.RecordEntity;

@Component
public class RecordMapper implements AbstractMapper<RecordDto, RecordEntity> {
    @Override
    public RecordDto entityToDto(RecordEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<RecordEntity, RecordDto> orderMap = new PropertyMap<RecordEntity, RecordDto>() {
            protected void configure() {
                map(source.getStaffEntity(), destination.getStaffDto());
            }
        };
        modelMapper.addMappings(orderMap);
        return modelMapper.map(entity, RecordDto.class);
    }

    @Override
    public RecordEntity dtoToEntity(RecordDto dto) {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<RecordDto, RecordEntity> orderMap = new PropertyMap<RecordDto, RecordEntity>() {
            protected void configure() {
                map(source.getStaffDto(), destination.getStaffEntity());
            }
        };
        modelMapper.addMappings(orderMap);
        return modelMapper.map(dto, RecordEntity.class);
    }
}
