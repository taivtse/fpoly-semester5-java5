package vn.edu.fpt.controller;

import org.hibernate.StaleStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.service.DepartService;
import vn.edu.fpt.util.MessageBundleUtil;

import java.util.List;

@Controller
@RequestMapping(value = {"/admin/depart"})
public class DepartController {
    private final String prefixPath = "admin/depart/";

    @Autowired
    private DepartService departService;

    @GetMapping
    public ModelAndView list() {
        List<DepartDto> departDtoList = departService.findAllActive();
        ModelAndView modelAndView = new ModelAndView(prefixPath.concat("list"));
        modelAndView.addObject("departDtoList", departDtoList);
        return modelAndView;
    }

    @PostMapping("insert")
    @ResponseBody
    public String insert(DepartDto departDto) {
        try {
            departService.save(departDto);
            return MessageBundleUtil.get("label.response.success");
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() instanceof ConstraintViolationException) {
                return MessageBundleUtil.get("label.response.duplicate");
            } else if (e.getCause() instanceof DataException) {
                return MessageBundleUtil.get("label.response.too_long");
            }
            return MessageBundleUtil.get("label.response.error");
        }
    }

    @PostMapping("update")
    @ResponseBody
    public String update(DepartDto departDto) {
        try {
            departService.update(departDto);
            return MessageBundleUtil.get("label.response.success");
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() instanceof StaleStateException) {
                return MessageBundleUtil.get("label.response.primary_key");
            } else if (e.getCause() instanceof DataException) {
                return MessageBundleUtil.get("label.response.too_long");
            }
            return MessageBundleUtil.get("label.response.error");
        }
    }

    @DeleteMapping("{departId}")
    @ResponseBody
    public String delete(@PathVariable("departId") String departId) {
        try {
            departService.deleteById(departId);
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
