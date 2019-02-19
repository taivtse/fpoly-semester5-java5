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
import vn.edu.fpt.util.MessageBundleUtil;
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
                recordDto = recordService.save(command.getPojo());
                pNotifyDto.setTitle(MessageBundleUtil.get("label.insert.success"));
                pNotifyDto.setText(MessageBundleUtil.get("label.record.insert.success"));
            } else {
                recordDto = recordService.update(command.getPojo());
                pNotifyDto.setTitle(MessageBundleUtil.get("label.update.success"));
                pNotifyDto.setText(MessageBundleUtil.get("label.record.update.success"));
            }

            pNotifyDto.setType(SystemConstant.SUCCESS);
            pNotifyDto.setText(String.format(pNotifyDto.getText(), recordDto.getStaffDto().getCode(), recordDto.getStaffDto().getName(), MessageBundleUtil.get("label.record." + recordDto.getType())));
        } catch (Exception e) {
            e.printStackTrace();
            pNotifyDto.setTitle(MessageBundleUtil.get("label.error"));
            pNotifyDto.setText(MessageBundleUtil.get("label.error.fail"));
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
            return MessageBundleUtil.get("label.response.success");
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() instanceof StaleStateException) {
                return MessageBundleUtil.get("label.response.primary_key");
            }
            return MessageBundleUtil.get("label.response.error");
        }
    }
}
