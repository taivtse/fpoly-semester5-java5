package vn.edu.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.dao.SynthesisDao;
import vn.edu.fpt.dto.SynthesisDto;
import vn.edu.fpt.service.SynthesisService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SynthesisServiceImpl implements SynthesisService {
    @Autowired
    private SynthesisDao synthesisDao;

    @Override
    public List<SynthesisDto> getStaffSynthesis() {
        List<SynthesisDto> synthesisDtoList = new ArrayList<>();
        List<Object[]> staffSynthesis = synthesisDao.getStaffSynthesis();
        staffSynthesis.forEach(synthesis -> {
            SynthesisDto synthesisDto = new SynthesisDto();
            synthesisDto.setStaffCode(String.valueOf(synthesis[0]));
            synthesisDto.setTotalReward((Integer) synthesis[1]);
            synthesisDto.setTotalPunishment((Integer) synthesis[2]);
            synthesisDto.setTotalPunishment(synthesisDto.getTotalReward() - synthesisDto.getTotalPunishment());

            synthesisDtoList.add(synthesisDto);
        });
        return synthesisDtoList;
    }
}
