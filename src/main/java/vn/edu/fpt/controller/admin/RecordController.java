package vn.edu.fpt.controller.admin;

import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.command.RecordCommand;
import vn.edu.fpt.constant.SystemConstant;
import vn.edu.fpt.dto.PNotifyDto;
import vn.edu.fpt.dto.RecordDto;
import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.service.generic.extend.RecordService;
import vn.edu.fpt.util.FormUtil;
import vn.edu.fpt.util.MessageSourceUtil;
import vn.edu.fpt.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/record")
public class RecordController {
    private final String prefixPath = "admin/record/";

    @Autowired
    private RecordService recordService;

    @GetMapping
    public ModelAndView list(HttpServletRequest request) {
        RecordCommand command = FormUtil.populate(RecordCommand.class, request);
        command.setListResult(recordService.findAll());

        PNotifyDto pNotifyDto = (PNotifyDto) SessionUtil.getInstance().get(request, SystemConstant.PNOTIFY);
        if (pNotifyDto != null) {
            command.setpNotifyDto(pNotifyDto);
            SessionUtil.getInstance().remove(request, SystemConstant.PNOTIFY);
        }

        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("list"));
        modelAndView.addObject(SystemConstant.COMMAND, command);
        return modelAndView;
    }

    @GetMapping({"info/{id}", "info/"})
    public ModelAndView info(@PathVariable(value = "id", required = false) Integer id) {
        RecordCommand command = new RecordCommand();
        if (id != null) {
            RecordDto recordDto = recordService.findOneById(id);
            if (recordDto != null) {
                command.setPojo(recordDto);
            } else {
                return new ModelAndView(SystemConstant.REDIRECT_URL.concat(prefixPath));
            }
        } else {
            command.setPojo(null);
        }

        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("edit"));
        modelAndView.addObject(SystemConstant.COMMAND, command);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView insertOrUpdate(HttpServletRequest request) {
        RecordCommand command = FormUtil.populate(RecordCommand.class, request);
        PNotifyDto pNotifyDto = new PNotifyDto();
        RecordDto recordDto;
        try {
            StaffDto staffDto = new StaffDto();
            staffDto.setId(command.getStaffId());
            command.getPojo().setStaffDto(staffDto);

            if (command.getPojo().getId() == null) {
                recordDto = recordService.save(command);
                pNotifyDto.setTitle(MessageSourceUtil.get("label.insert.success", command.getLocale()));
                pNotifyDto.setText(MessageSourceUtil.get("label.record.insert.success", command.getLocale()));
            } else {
                recordDto = recordService.update(command);
                pNotifyDto.setTitle(MessageSourceUtil.get("label.update.success", command.getLocale()));
                pNotifyDto.setText(MessageSourceUtil.get("label.record.insert.success", command.getLocale()));
            }

            pNotifyDto.setType(SystemConstant.SUCCESS);
            pNotifyDto.setText(String.format(pNotifyDto.getText(),
                    recordDto.getStaffDto().getCode(),
                    recordDto.getStaffDto().getName(),
                    MessageSourceUtil.get("label.record." + recordDto.getType(), command.getLocale())));
        } catch (Exception e) {
            e.printStackTrace();
            pNotifyDto.setTitle(MessageSourceUtil.get("label.error", command.getLocale()));
            pNotifyDto.setText(MessageSourceUtil.get("label.error.fail", command.getLocale()));
            pNotifyDto.setType(SystemConstant.ERROR);
        }
        SessionUtil.getInstance().put(request, SystemConstant.PNOTIFY, pNotifyDto);

        ModelAndView modelAndView = new ModelAndView(SystemConstant.REDIRECT_URL.concat(prefixPath));
        return modelAndView;
    }

    @DeleteMapping("{recordId}")
    @ResponseBody
    public String delete(@PathVariable("recordId") Integer recordId) {
        try {
            recordService.deleteById(recordId);
            return MessageSourceUtil.get("label.response.success", null);
        } catch (StaleStateException e) {
            e.printStackTrace();
            return MessageSourceUtil.get("label.response.primary_key", null);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageSourceUtil.get("label.response.error", null);
        }
    }
}
