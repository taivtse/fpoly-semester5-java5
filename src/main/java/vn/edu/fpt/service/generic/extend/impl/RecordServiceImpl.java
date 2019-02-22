package vn.edu.fpt.service.generic.extend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.command.RecordCommand;
import vn.edu.fpt.dao.generic.GenericDao;
import vn.edu.fpt.dao.generic.extend.RecordDao;
import vn.edu.fpt.dto.RecordDto;
import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.mapper.AbstractMapper;
import vn.edu.fpt.mapper.RecordMapper;
import vn.edu.fpt.service.EmailSender;
import vn.edu.fpt.service.generic.extend.RecordService;
import vn.edu.fpt.service.generic.extend.StaffService;
import vn.edu.fpt.service.generic.impl.GenericServiceImpl;
import vn.edu.fpt.util.MessageSourceUtil;

import java.util.Date;
import java.util.Locale;

@Service
@Transactional
public class RecordServiceImpl extends GenericServiceImpl<Integer, RecordDto> implements RecordService {

    private RecordMapper mapper;
    private RecordDao recordDao;

    @Autowired
    private StaffService staffService;

    @Autowired
    public RecordServiceImpl(@Qualifier("recordDaoImpl") GenericDao genericDao,
                             @Qualifier("recordMapper") AbstractMapper abstractMapper) {
        super(genericDao, abstractMapper);
        this.mapper = (RecordMapper) abstractMapper;
        this.recordDao = (RecordDao) genericDao;
    }

    @Override
    public RecordDto update(RecordDto dto) throws Exception {
        RecordDto oldDto = this.findOneById(dto.getId());
        dto.setSubmitDate(oldDto.getSubmitDate());
        return super.update(dto);
    }

    @Override
    public RecordDto save(RecordCommand command) throws Exception {
        RecordDto recordDto = command.getPojo();
        recordDto.setSubmitDate(new Date());
        recordDto = super.save(recordDto);

        RecordDto tempRecordDto = recordDto;
        new Thread(() -> {
            StaffDto staffDto = staffService.findOneById(tempRecordDto.getStaffDto().getId());
            String emailSubject = MessageSourceUtil.get("label.email.record.subject", null);
            String emailBody = MessageSourceUtil.get("label.email.record.body", null);
            String recordType = MessageSourceUtil.get("label.record." + tempRecordDto.getType(), new Locale("vi", "VN"));

            emailBody = String.format(emailBody, staffDto.getName(), recordType, tempRecordDto.getReason());

            try {
                EmailSender.send(staffDto.getEmail(), emailSubject, emailBody);
            } catch (MailException e) {
                e.printStackTrace();
            }
        }).start();

        return recordDto;
    }

    @Override
    public RecordDto update(RecordCommand command) throws Exception {
        RecordDto updateDto = command.getPojo();
        RecordDto oldDto = this.findOneById(updateDto.getId());
        updateDto.setSubmitDate(oldDto.getSubmitDate());
        return super.update(updateDto);
    }
}
