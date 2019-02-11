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

import java.util.List;

@Controller
@RequestMapping(value = {"/admin/depart"})
public class DepartController {
    private String viewRootPath = "admin/depart/";

    @Autowired
    private DepartService departService;

    @GetMapping
    public ModelAndView list() {
        List<DepartDto> departDtoList = departService.findAll();
        ModelAndView modelAndView = new ModelAndView(viewRootPath.concat("list"));
        modelAndView.addObject("departDtoList", departDtoList);
        return modelAndView;
    }

    @PostMapping("/insert")
    @ResponseBody
    public String insert(DepartDto departDto) {
        try {
            departService.save(departDto);
            return "success";
        } catch (ConstraintViolationException e) {
            return "fail_duplicate";
        } catch (DataException e) {
            return "fail_toolong";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(DepartDto departDto) {
        try {
            departService.update(departDto);
            return "success";
        } catch (StaleStateException e) {
            return "fail_primarykey";
        } catch (DataException e) {
            return "fail_toolong";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @DeleteMapping("/{departId}")
    @ResponseBody
    public String delete(@PathVariable("departId") String departId) {
        try {
            departService.deleteById(departId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
