package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fpt.entity.UserEntity;
import vn.edu.fpt.service.UserService;

import java.util.List;

@Controller
//@RequestMapping(value = {"/user"})
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/"})
    public String index(){
        List<UserEntity> list = userService.findAll();
        return "admin/user/list";
    }
}
