package vn.edu.fpt.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.command.SynthesisCommand;
import vn.edu.fpt.constant.SystemConstant;
import vn.edu.fpt.service.SynthesisService;

@Controller
@RequestMapping("/admin")
public class DashboardController {
    private final String prefixPath = "admin/";

    @Autowired
    private SynthesisService synthesisService;

    @RequestMapping("")
    public ModelAndView index(){
        SynthesisCommand command = new SynthesisCommand();
        command.setStaffSynthesisDtoList(synthesisService.getTopStaffSynthesis(10));
        command.setDepartSynthesisDtoList(synthesisService.getDepartSynthesis());

        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("dashboard"));
        modelAndView.addObject(SystemConstant.COMMAND, command);
        return modelAndView;
    }
}
