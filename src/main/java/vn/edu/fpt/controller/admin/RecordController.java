package vn.edu.fpt.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.command.RecordCommand;
import vn.edu.fpt.util.FormUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/record")
public class RecordController {
    private final String prefixPath = "admin/record/";

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("home"));
        return modelAndView;
    }

    @PostMapping
    public ModelAndView save(HttpServletRequest request) {
        RecordCommand command = FormUtil.populate(RecordCommand.class, request);
        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("edit"));
        return modelAndView;
    }
}
