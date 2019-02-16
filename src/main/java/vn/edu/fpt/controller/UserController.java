package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.dto.UserDto;
import vn.edu.fpt.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = {"/admin/user/"})
public class UserController {
    private final String prefixPath = "admin/user/";

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView home() {
        List<UserDto> userDtoList = userService.findAll();
        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("list"));
        modelAndView.addObject("userDtoList", userDtoList);
        return modelAndView;
    }
}
