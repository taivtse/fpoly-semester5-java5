package vn.edu.fpt.controller.admin;

import org.hibernate.StaleStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.command.DepartCommand;
import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.service.generic.extend.DepartService;
import vn.edu.fpt.util.MessageSourceUtil;

import java.util.List;

@Controller
@RequestMapping(value = {"/admin/depart"})
public class DepartController {
    private final String prefixPath = "admin/depart/";

    @Autowired
    private DepartService departService;

    @GetMapping
    public ModelAndView list() {
        DepartCommand command = new DepartCommand();
        List<DepartDto> departDtoList = departService.findAllActive();
        command.setListResult(departDtoList);

        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("list"));
        modelAndView.addObject("command", command);
        return modelAndView;
    }

    @PostMapping("insert")
    @ResponseBody
    public String insert(DepartDto departDto) {
        try {
            departService.saveWithActiveStatus(departDto);
            return MessageSourceUtil.get("label.response.success", null);
        } catch (ConstraintViolationException e) {
            return MessageSourceUtil.get("label.response.duplicate", null);
        } catch (DataException e) {
            return MessageSourceUtil.get("label.response.too_long", null);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageSourceUtil.get("label.response.error", null);
        }
    }

    @PostMapping("update")
    @ResponseBody
    public String update(DepartDto departDto) {
        try {
            departService.updateWithActiveStatus(departDto);
            return MessageSourceUtil.get("label.response.success", null);
        } catch (StaleStateException e) {
            e.printStackTrace();
            return MessageSourceUtil.get("label.response.primary_key", null);
        } catch (DataException e) {
            return MessageSourceUtil.get("label.response.too_long", null);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageSourceUtil.get("label.response.error", null);
        }
    }

    @DeleteMapping("{departId}")
    @ResponseBody
    public String delete(@PathVariable("departId") String departId) {
        try {
            departService.updateToUnActiveById(departId);
            return MessageSourceUtil.get("label.response.success", null);
        } catch (StaleStateException e) {
            e.printStackTrace();
            return MessageSourceUtil.get("label.response.primary_key", null);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageSourceUtil.get("label.response.error", null);
        }
    }
}
