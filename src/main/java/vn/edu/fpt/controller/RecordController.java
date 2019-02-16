package vn.edu.fpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/record")
public class RecordController {
    private final String prefixPath = "admin/record/";

    @GetMapping
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("home"));
        return modelAndView;
    }
}
