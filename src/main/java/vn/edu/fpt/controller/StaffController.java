package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.service.StaffService;

import java.util.List;

@Controller
@RequestMapping(value = {"/admin/staff/"})
public class StaffController {
    private final String prefixPath = "admin/staff/";
    @Autowired
    private StaffService staffService;

    @GetMapping
    public ModelAndView list() {
        List<StaffDto> staffDtoList = staffService.findAll();
        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("list"));
        modelAndView.addObject("staffDtoList", staffDtoList);
        return modelAndView;
    }

    @GetMapping("/${code}")
    public ModelAndView edit(@PathVariable(required = false) String code) {
        StaffDto staffDto = staffService.getByCode(code);
        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("edit"));
        modelAndView.addObject("staffDto", staffDto);
        return modelAndView;
    }

}
