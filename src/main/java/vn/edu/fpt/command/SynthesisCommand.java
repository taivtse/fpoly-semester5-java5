package vn.edu.fpt.command;

import vn.edu.fpt.dto.SynthesisDto;

public class SynthesisCommand extends AbstractCommand<SynthesisDto> {
    public SynthesisCommand() {
        this.pojo = new SynthesisDto();
    }
}
