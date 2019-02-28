package vn.edu.fpt.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.constant.SystemConstant;
import vn.edu.fpt.util.MessageSourceUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/login")
public class LoginController {
    private final String prefixPath = "admin/";

    @GetMapping
    public ModelAndView displayLoginForm(@RequestParam(value = "status", required = false) String status,
                                         HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("login"));

        if (status != null) {
            modelAndView.addObject(SystemConstant.MESSAGE_RESPONSE,
                    MessageSourceUtil.get("label.login.wrong", request.getLocale()));
        }

        return modelAndView;
    }
}
