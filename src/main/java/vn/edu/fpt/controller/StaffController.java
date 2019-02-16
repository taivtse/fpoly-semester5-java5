package vn.edu.fpt.controller;

import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.command.StaffCommand;
import vn.edu.fpt.constant.SystemConstant;
import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.dto.PNotifyDto;
import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.dto.StaffLiveSearchDto;
import vn.edu.fpt.service.DepartService;
import vn.edu.fpt.service.StaffService;
import vn.edu.fpt.util.FormUtil;
import vn.edu.fpt.util.MessageBundleUtil;
import vn.edu.fpt.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/admin/staff"})
public class StaffController {
    private final String prefixPath = "admin/staff";
    @Autowired
    private StaffService staffService;

    @Autowired
    private DepartService departService;

    @GetMapping
    public ModelAndView home(@RequestParam(value = "search", required = false) String search, HttpServletRequest request) {
        StaffCommand command = FormUtil.populate(StaffCommand.class, request);

        if (search != null) {
            command.setListResult(staffService.findAllByName(search));
        } else {
            command.setListResult(staffService.findAllActive());
        }

        PNotifyDto pNotifyDto = (PNotifyDto) SessionUtil.getInstance().get(request, SystemConstant.PNotify);
        if (pNotifyDto != null) {
            command.setpNotifyDto(pNotifyDto);
            SessionUtil.getInstance().remove(request, SystemConstant.PNotify);
        }

        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("/list"));
        modelAndView.addObject(SystemConstant.COMMAND, command);

        return modelAndView;
    }

    @GetMapping({"info/{code}", "info/"})
    public ModelAndView info(@PathVariable(value = "code", required = false) String code) {
        StaffCommand command = new StaffCommand();

        if (code != null) {
            StaffDto staffDto = staffService.findByCode(code);
            if (staffDto != null) {
                command.setPojo(staffDto);
            } else {
                return new ModelAndView(SystemConstant.REDIRECT_URL.concat(prefixPath));
            }
        } else {
            command.setPojo(null);
        }

        List<DepartDto> departDtoList = departService.findAllActive();
        command.setDepartDtoList(departDtoList);

        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("/edit"));
        modelAndView.addObject(SystemConstant.COMMAND, command);

        return modelAndView;
    }

    @GetMapping("live-search/info/{id}")
    @ResponseBody
    public StaffLiveSearchDto info(@PathVariable("id") Integer id) {
        return staffService.findByIdInLiveSearch(id);
    }

    @GetMapping("live-search")
    @ResponseBody
    public List<StaffLiveSearchDto> getStaffByCodeInLiveSearch(@RequestParam("staffCode") String staffCode) {
        List<StaffLiveSearchDto> liveSearchNameList = staffService.findAllNameByCodeInLiveSearch(staffCode);
        return liveSearchNameList;
    }

    @PostMapping("update")
    public String insertOrUpdate(HttpServletRequest request) {
        StaffCommand command = FormUtil.populate(StaffCommand.class, request);
        PNotifyDto pNotifyDto = new PNotifyDto();
        StaffDto staffDto;
        try {
            DepartDto departDto = new DepartDto();
            departDto.setId(command.getDepartId().trim());
            command.getPojo().setDepartDto(departDto);

            if (command.getPojo().getId() == null) {
                staffDto = staffService.saveWithActiveStatus(command.getPojo());
                pNotifyDto.setTitle(MessageBundleUtil.get("label.insert.success"));
                pNotifyDto.setText(MessageBundleUtil.get("label.staff.insert.success"));
            } else {
                staffDto = staffService.updateWithActiveStatus(command.getPojo());
                pNotifyDto.setTitle(MessageBundleUtil.get("label.update.success"));
                pNotifyDto.setText(MessageBundleUtil.get("label.staff.update.success"));
            }

            pNotifyDto.setType(SystemConstant.SUCCESS);
            pNotifyDto.setText(String.format(pNotifyDto.getText(), staffDto.getCode(), staffDto.getName()));
        } catch (Exception e) {
            e.printStackTrace();
            pNotifyDto.setTitle(MessageBundleUtil.get("label.error"));
            pNotifyDto.setText(MessageBundleUtil.get("label.error.fail"));
            pNotifyDto.setType(SystemConstant.ERROR);
        }
        SessionUtil.getInstance().put(request, SystemConstant.PNotify, pNotifyDto);
        return SystemConstant.REDIRECT_URL.concat(prefixPath);
    }

    @DeleteMapping("{staffId}")
    @ResponseBody
    public String delete(@PathVariable("staffId") Integer staffId) {
        try {
            staffService.updateToUnActiveById(staffId);
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
