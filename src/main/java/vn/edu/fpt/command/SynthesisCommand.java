package vn.edu.fpt.command;

import vn.edu.fpt.dto.DepartSynthesisDto;
import vn.edu.fpt.dto.StaffSynthesisDto;

import java.util.List;

public class SynthesisCommand extends AbstractCommand<StaffSynthesisDto> {
    private List<StaffSynthesisDto> staffSynthesisDtoList;
    private List<DepartSynthesisDto> departSynthesisDtoList;

    public SynthesisCommand() {
        this.pojo = new StaffSynthesisDto();
    }

    public List<StaffSynthesisDto> getStaffSynthesisDtoList() {
        return staffSynthesisDtoList;
    }

    public void setStaffSynthesisDtoList(List<StaffSynthesisDto> staffSynthesisDtoList) {
        this.staffSynthesisDtoList = staffSynthesisDtoList;
    }

    public List<DepartSynthesisDto> getDepartSynthesisDtoList() {
        return departSynthesisDtoList;
    }

    public void setDepartSynthesisDtoList(List<DepartSynthesisDto> departSynthesisDtoList) {
        this.departSynthesisDtoList = departSynthesisDtoList;
    }
}
