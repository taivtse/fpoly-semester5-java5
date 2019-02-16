package vn.edu.fpt.command;

import vn.edu.fpt.dto.RecordDto;

public class RecordCommand extends AbstractCommand<RecordDto> {
    private String staffId;

    public RecordCommand() {
        this.pojo = new RecordDto();
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
