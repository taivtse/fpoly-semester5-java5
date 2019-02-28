package vn.edu.fpt.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final String prefixPath = "admin/";

    @GetMapping
    public ModelAndView displayLoginForm(){
        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("login"));
        return modelAndView;
    }
}
