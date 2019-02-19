package vn.edu.fpt.controller.admin;

import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.command.UserCommand;
import vn.edu.fpt.constant.SystemConstant;
import vn.edu.fpt.dto.PNotifyDto;
import vn.edu.fpt.dto.UserDto;
import vn.edu.fpt.service.generic.extend.UserService;
import vn.edu.fpt.util.FormUtil;
import vn.edu.fpt.util.MessageBundleUtil;
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

    @PostMapping
    public ModelAndView insertOrUpdate(HttpServletRequest request) {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        PNotifyDto pNotifyDto = new PNotifyDto();
        UserDto userDto;
        try {
            if (command.getPojo().getId() == null) {
                userDto = userService.save(command.getPojo());
                pNotifyDto.setTitle(MessageBundleUtil.get("label.insert.success"));
                pNotifyDto.setText(MessageBundleUtil.get("label.user.insert.success"));
            } else {
                userDto = userService.update(command.getPojo());
                pNotifyDto.setTitle(MessageBundleUtil.get("label.update.success"));
                pNotifyDto.setText(MessageBundleUtil.get("label.user.update.success"));
            }

            pNotifyDto.setType(SystemConstant.SUCCESS);
            pNotifyDto.setText(String.format(pNotifyDto.getText(), userDto.getUsername(), userDto.getFullName()));
        } catch (Exception e) {
            e.printStackTrace();
            pNotifyDto.setTitle(MessageBundleUtil.get("label.error"));
            pNotifyDto.setText(MessageBundleUtil.get("label.error.fail"));
            pNotifyDto.setType(SystemConstant.ERROR);
        }
        SessionUtil.getInstance().put(request, SystemConstant.PNOTIFY, pNotifyDto);

        ModelAndView modelAndView = new ModelAndView(SystemConstant.REDIRECT_URL.concat(prefixPath));
        return modelAndView;
    }

    @DeleteMapping("{userId}")
    @ResponseBody
    public String delete(@PathVariable("userId") Integer userId) {
        try {
            userService.deleteById(userId);
            return MessageBundleUtil.get("label.response.success");
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() instanceof StaleStateException) {
                return MessageBundleUtil.get("label.response.primary_key");
            }
            return MessageBundleUtil.get("label.response.error");
        }
    }
}
