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
import vn.edu.fpt.util.ResourceBundleUtil;

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
            return ResourceBundleUtil.getCommonBundle().get("label.response.success");
        } catch (ConstraintViolationException e) {
            return ResourceBundleUtil.getCommonBundle().get("label.response.duplicate");
        } catch (DataException e) {
            return ResourceBundleUtil.getCommonBundle().get("label.response.too_long");
        } catch (Exception e) {
            e.printStackTrace();
            return ResourceBundleUtil.getCommonBundle().get("label.response.error");
        }
    }

    @PostMapping("update")
    @ResponseBody
    public String update(DepartDto departDto) {
        try {
            departService.updateWithActiveStatus(departDto);
            return ResourceBundleUtil.getCommonBundle().get("label.response.success");
        } catch (StaleStateException e) {
            e.printStackTrace();
            return ResourceBundleUtil.getCommonBundle().get("label.response.primary_key");
        } catch (DataException e) {
            return ResourceBundleUtil.getCommonBundle().get("label.response.too_long");
        } catch (Exception e) {
            e.printStackTrace();
            return ResourceBundleUtil.getCommonBundle().get("label.response.error");
        }
    }

    @DeleteMapping("{departId}")
    @ResponseBody
    public String delete(@PathVariable("departId") String departId) {
        try {
            departService.updateToUnActiveById(departId);
            return ResourceBundleUtil.getCommonBundle().get("label.response.success");
        } catch (StaleStateException e) {
            e.printStackTrace();
            return ResourceBundleUtil.getCommonBundle().get("label.response.primary_key");
        } catch (Exception e) {
            e.printStackTrace();
            return ResourceBundleUtil.getCommonBundle().get("label.response.error");
        }
    }
}
