package vn.edu.fpt.service.generic.extend;

import vn.edu.fpt.command.RecordCommand;
import vn.edu.fpt.dto.RecordDto;
import vn.edu.fpt.service.generic.GenericService;

public interface RecordService extends GenericService<Integer, RecordDto> {
    RecordDto save(RecordCommand command) throws Exception;

    RecordDto update(RecordCommand command) throws Exception;
}
