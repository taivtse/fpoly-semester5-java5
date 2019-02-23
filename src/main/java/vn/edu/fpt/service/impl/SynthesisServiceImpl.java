package vn.edu.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.SynthesisDao;
import vn.edu.fpt.dto.DepartSynthesisDto;
import vn.edu.fpt.dto.StaffSynthesisDto;
import vn.edu.fpt.service.SynthesisService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SynthesisServiceImpl implements SynthesisService {
    @Autowired
    private SynthesisDao synthesisDao;

    @Override
    public StaffSynthesisDto getStaffSynthesisByCode(String staffCode) {
        Object[] staffSynthesis = synthesisDao.getStaffSynthesisByCode(staffCode);
        if (staffSynthesis == null) {
            return null;
        }
        StaffSynthesisDto staffSynthesisDto = new StaffSynthesisDto();
        staffSynthesisDto.setStaffCode(String.valueOf(staffSynthesis[0]));
        staffSynthesisDto.setStaffName(String.valueOf(staffSynthesis[1]));
        staffSynthesisDto.setStaffPhoto(String.valueOf(staffSynthesis[2]));
        staffSynthesisDto.setTotalReward((Long) staffSynthesis[3]);
        staffSynthesisDto.setTotalPunishment((Long) staffSynthesis[4]);
        staffSynthesisDto.setTotalResult(staffSynthesisDto.getTotalReward() - staffSynthesisDto.getTotalPunishment());
        return staffSynthesisDto;
    }

    @Override
    public List<StaffSynthesisDto> getTopStaffSynthesis(Integer limit) {
        List<StaffSynthesisDto> staffSynthesisDtoList = new ArrayList<>();
        List<Object[]> staffSynthesis = synthesisDao.getTopStaffSynthesis(limit);
        staffSynthesis.forEach(synthesis -> {
            StaffSynthesisDto staffSynthesisDto = new StaffSynthesisDto();
            staffSynthesisDto.setStaffCode(String.valueOf(synthesis[0]));
            staffSynthesisDto.setStaffName(String.valueOf(synthesis[1]));
            staffSynthesisDto.setStaffPhoto(String.valueOf(synthesis[2]));
            staffSynthesisDto.setTotalReward((Long) synthesis[3]);
            staffSynthesisDto.setTotalPunishment((Long) synthesis[4]);
            staffSynthesisDto.setTotalResult(staffSynthesisDto.getTotalReward() - staffSynthesisDto.getTotalPunishment());

            staffSynthesisDtoList.add(staffSynthesisDto);
        });
        return staffSynthesisDtoList;
    }

    @Override
    public List<DepartSynthesisDto> getDepartSynthesis() {
        List<DepartSynthesisDto> departSynthesisDtoList = new ArrayList<>();
        List<Object[]> departSynthesis = synthesisDao.getDepartSynthesis();
        departSynthesis.forEach(synthesis -> {
            DepartSynthesisDto departSynthesisDto = new DepartSynthesisDto();
            departSynthesisDto.setDepartId(String.valueOf(synthesis[0]));
            departSynthesisDto.setDepartName(String.valueOf(synthesis[1]));
            departSynthesisDto.setTotalReward((Long) synthesis[2]);
            departSynthesisDto.setTotalPunishment((Long) synthesis[3]);
            departSynthesisDto.setTotalResult(departSynthesisDto.getTotalReward() - departSynthesisDto.getTotalPunishment());

            departSynthesisDtoList.add(departSynthesisDto);
        });
        return departSynthesisDtoList;
    }
}
