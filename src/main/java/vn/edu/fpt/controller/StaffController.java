package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fpt.dto.StaffDto;
import vn.edu.fpt.service.StaffService;

import java.util.List;

@Controller
@RequestMapping(value = {"/staff"})
public class StaffController {
    @Autowired
    private StaffService staffService;

    @RequestMapping(value = {""})
    public String index() {
        List<StaffDto> list = staffService.findAll();
        return "admin/user/list";
    }
}
