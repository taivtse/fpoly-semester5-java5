package vn.edu.fpt.service;

import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.service.generic.ActiveEntityService;

public interface StaffService extends ActiveEntityService<Integer, StaffDto> {
    StaffDto getByCode(String code);
}
