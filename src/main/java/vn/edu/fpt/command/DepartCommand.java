package vn.edu.fpt.command;

import vn.edu.fpt.dto.DepartDto;

public class DepartCommand extends AbstractCommand<DepartDto> {

    public DepartCommand() {
        this.pojo = new DepartDto();
    }
}
