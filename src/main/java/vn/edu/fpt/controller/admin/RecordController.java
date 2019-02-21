package vn.edu.fpt.controller.admin;

import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import vn.edu.fpt.util.ResourceBundleUtil;
import vn.edu.fpt.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping("/admin/record")
public class RecordController {
    private final String prefixPath = "admin/record/";

    @Autowired
    private RecordService recordService;

    @Autowired
    private MessageSource messageSource;

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
    public ModelAndView insertOrUpdate(HttpServletRequest request, Locale locale) {
        RecordCommand command = FormUtil.populate(RecordCommand.class, request);
        PNotifyDto pNotifyDto = new PNotifyDto();
        RecordDto recordDto;
        try {
            StaffDto staffDto = new StaffDto();
            staffDto.setId(command.getStaffId());
            command.getPojo().setStaffDto(staffDto);

            if (command.getPojo().getId() == null) {
                recordDto = recordService.save(command.getPojo());
                pNotifyDto.setTitle(messageSource.getMessage("label.insert.success", null, locale));
                pNotifyDto.setText(messageSource.getMessage("label.record.insert.success", null, locale));
            } else {
                recordDto = recordService.update(command.getPojo());
                pNotifyDto.setTitle(messageSource.getMessage("label.update.success", null, locale));
                pNotifyDto.setText(messageSource.getMessage("label.record.insert.success", null, locale));
            }

            pNotifyDto.setType(SystemConstant.SUCCESS);
            pNotifyDto.setText(String.format(pNotifyDto.getText(),
                    recordDto.getStaffDto().getCode(),
                    recordDto.getStaffDto().getName(),
                    messageSource.getMessage("label.record." + recordDto.getType(), null, locale)));
        } catch (Exception e) {
            e.printStackTrace();
            pNotifyDto.setTitle(messageSource.getMessage("label.error", null, locale));
            pNotifyDto.setText(messageSource.getMessage("label.error.fail", null, locale));
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
            return ResourceBundleUtil.getCommonBundle().get("label.response.success");
        } catch (StaleStateException e) {
            e.printStackTrace();
            return ResourceBundleUtil.getCommonBundle().get("label.response.primary_key");
        } catch (Exception e) {
            e.printStackTrace();
            return ResourceBundleUtil.getCommonBundle().get("label.response.error");
        }
    }
}
