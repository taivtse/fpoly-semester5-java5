package vn.edu.fpt.controller.admin;

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
import vn.edu.fpt.service.extend.DepartService;
import vn.edu.fpt.service.extend.StaffService;
import vn.edu.fpt.util.FormUtil;
import vn.edu.fpt.util.MessageBundleUtil;
import vn.edu.fpt.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/admin/staff"})
public class StaffController {
    private final String prefixPath = "admin/staff/";
    @Autowired
    private StaffService staffService;

    @Autowired
    private DepartService departService;

    @GetMapping({"", "search"})
    public ModelAndView list(@RequestParam(value = "name", required = false) String searchName, HttpServletRequest request) {
        StaffCommand command = new StaffCommand();

        if (searchName != null) {
            command.setListResult(staffService.findAllActiveByName(searchName));
        } else {
            command.setListResult(staffService.findAllActive());
        }

        PNotifyDto pNotifyDto = (PNotifyDto) SessionUtil.getInstance().get(request, SystemConstant.PNOTIFY);
        if (pNotifyDto != null) {
            command.setpNotifyDto(pNotifyDto);
            SessionUtil.getInstance().remove(request, SystemConstant.PNOTIFY);
        }

        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("list"));
        modelAndView.addObject(SystemConstant.COMMAND, command);

        return modelAndView;
    }

    @GetMapping({"info/{code}", "info/"})
    public ModelAndView info(@PathVariable(value = "code", required = false) String code) {
        StaffCommand command = new StaffCommand();

        if (code != null) {
            StaffDto staffDto = staffService.findOneActiveByCode(code);
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

        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("edit"));
        modelAndView.addObject(SystemConstant.COMMAND, command);

        return modelAndView;
    }

    @GetMapping("live-search/info/{id}")
    @ResponseBody
    public StaffLiveSearchDto info(@PathVariable("id") Integer id) {
        return staffService.findOneActiveByIdInLiveSearch(id);
    }

    @GetMapping("live-search")
    @ResponseBody
    public List<StaffLiveSearchDto> getStaffByCodeInLiveSearch(@RequestParam("staffCode") String staffCode) {
        List<StaffLiveSearchDto> liveSearchNameList = staffService.findAllActiveByCodeInLiveSearch(staffCode);
        return liveSearchNameList;
    }

    @PostMapping
    public ModelAndView insertOrUpdate(HttpServletRequest request) {
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
        SessionUtil.getInstance().put(request, SystemConstant.PNOTIFY, pNotifyDto);

        ModelAndView modelAndView = new ModelAndView(SystemConstant.REDIRECT_URL.concat(prefixPath));
        return modelAndView;
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
