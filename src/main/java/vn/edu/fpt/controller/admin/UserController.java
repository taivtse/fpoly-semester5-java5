package vn.edu.fpt.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.command.UserCommand;
import vn.edu.fpt.constant.SystemConstant;
import vn.edu.fpt.dto.PNotifyDto;
import vn.edu.fpt.dto.UserDto;
import vn.edu.fpt.service.extend.UserService;
import vn.edu.fpt.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/admin/user"})
public class UserController {
    private final String prefixPath = "admin/user/";

    @Autowired
    private UserService userService;

    @GetMapping({"", "search"})
    public ModelAndView list(@RequestParam(value = "name", required = false) String searchName, HttpServletRequest request) {
        UserCommand command = new UserCommand();

        if (searchName != null) {
            command.setListResult(userService.findAllByFullName(searchName));
        } else {
            command.setListResult(userService.findAll());
        }

        PNotifyDto pNotifyDto = (PNotifyDto) SessionUtil.getInstance().get(request, SystemConstant.PNOTIFY);
        if (pNotifyDto != null) {
            command.setpNotifyDto(pNotifyDto);
            SessionUtil.getInstance().remove(request, SystemConstant.PNOTIFY);
        }

        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("list"));
        modelAndView.addObject(SystemConstant.COMMAND, command);
        return modelAndView;
    }

    @GetMapping({"info/{username}", "info/"})
    public ModelAndView info(@PathVariable(value = "username", required = false) String username) {
        UserCommand command = new UserCommand();

        if (username != null) {
            UserDto userDto = userService.findOneByUsername(username);
            if (userDto != null) {
                command.setPojo(userDto);
            } else {
                return new ModelAndView(SystemConstant.REDIRECT_URL.concat(prefixPath));
            }
        } else {
            command.setPojo(null);
        }

        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("edit"));
        modelAndView.addObject(SystemConstant.COMMAND, command);

        return modelAndView;
    }
}
