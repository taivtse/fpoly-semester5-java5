package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.service.DepartService;

import java.util.List;

@Controller
@RequestMapping(value = {"/admin/depart"})
public class DepartController {
    @Autowired
    private DepartService departService;

    @RequestMapping(value = {""})
    public String index() {
//        List<DepartDto> list = departService.findAll();
        return "admin/depart/list";
    }
}
