package vn.edu.fpt.service;

import vn.edu.fpt.dto.SynthesisDto;

import java.util.List;

public interface SynthesisService {
    List<SynthesisDto> getStaffSynthesis();

    SynthesisDto getStaffSynthesisByCode(String staffCode);
}
