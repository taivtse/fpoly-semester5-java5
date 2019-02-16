package vn.edu.fpt.command;

import vn.edu.fpt.dto.RecordDto;

public class RecordCommand extends AbstractCommand<RecordDto> {
    private Integer staffId;

    public RecordCommand() {
        this.pojo = new RecordDto();
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
}
