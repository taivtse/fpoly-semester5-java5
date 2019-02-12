package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.command.StaffCommand;
import vn.edu.fpt.constant.SystemConstant;
import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.service.DepartService;
import vn.edu.fpt.service.StaffService;
import vn.edu.fpt.util.FormUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/admin/staff/"})
public class StaffController {
    private final String prefixPath = "admin/staff/";
    @Autowired
    private StaffService staffService;

    @Autowired
    private DepartService departService;

    @GetMapping
    public ModelAndView list() {
        List<StaffDto> staffDtoList = staffService.findAll();
        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("list"));
        modelAndView.addObject("staffDtoList", staffDtoList);
        return modelAndView;
    }

    @GetMapping("info/{code}")
    public ModelAndView insertOrUpdate(@PathVariable String code) {
        StaffDto staffDto = staffService.getByCode(code);
        List<DepartDto> departDtoList = departService.findAllActive();
        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("edit"));

        StaffCommand command = new StaffCommand();
        command.setDepartDtoList(departDtoList);
        command.setPojo(staffDto);
        modelAndView.addObject(SystemConstant.COMMAND, command);

        return modelAndView;
    }

    @PostMapping("update")
    public String insertOrUpdate(HttpServletRequest request) {
//        System.out.println(staffDto.getCode());
        StaffCommand command = FormUtil.populate(StaffCommand.class, request);
        return "redirect:/".concat(prefixPath);
    }
}
