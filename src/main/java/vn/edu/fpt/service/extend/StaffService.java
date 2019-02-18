package vn.edu.fpt.service.extend;

import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.dto.StaffLiveSearchDto;
import vn.edu.fpt.service.generic.ActiveEntityService;

import java.util.List;

public interface StaffService extends ActiveEntityService<Integer, StaffDto> {
    StaffDto findOneActiveByCode(String code);
    List<StaffDto> findAllActiveByName(String name);
    List<StaffLiveSearchDto> findAllActiveByCodeInLiveSearch(String staffCode);
    StaffLiveSearchDto findOneActiveByIdInLiveSearch(Integer id);
}
