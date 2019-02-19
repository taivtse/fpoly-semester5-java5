package vn.edu.fpt.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.command.SynthesisCommand;
import vn.edu.fpt.constant.SystemConstant;
import vn.edu.fpt.service.SynthesisService;

@Controller
@RequestMapping("/admin/synthesis")
public class SynthesisController {
    private final String prefixPath = "admin/synthesis/";

    @Autowired
    private SynthesisService synthesisService;

    @GetMapping
    public ModelAndView list() {
        SynthesisCommand command = new SynthesisCommand();
        command.setListResult(synthesisService.getStaffSynthesis());

        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("list"));
        modelAndView.addObject(SystemConstant.COMMAND, command);
        return modelAndView;
    }

}
