package vn.edu.fpt.service;

import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.service.generic.ActiveEntityService;

import java.util.List;

public interface StaffService extends ActiveEntityService<Integer, StaffDto> {
    StaffDto findByCode(String code);
    List<StaffDto> findAllByName(String name);
}
