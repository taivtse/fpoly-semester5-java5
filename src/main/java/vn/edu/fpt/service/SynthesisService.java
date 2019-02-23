package vn.edu.fpt.service;

import vn.edu.fpt.dto.DepartSynthesisDto;
import vn.edu.fpt.dto.StaffSynthesisDto;

import java.util.List;

public interface SynthesisService {
    StaffSynthesisDto getStaffSynthesisByCode(String staffCode);

    List<StaffSynthesisDto> getTopStaffSynthesis(Integer limit);

    List<DepartSynthesisDto> getDepartSynthesis();
}
