package vn.edu.fpt.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        List<DepartDto> departDtoList = departService.findAll();
        ModelAndView modelAndView = new ModelAndView(viewRootPath.concat("list"));
        modelAndView.addObject("departDtoList", departDtoList);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String add(DepartDto departDto) {
        try {
            departService.save(departDto);
            return "success";
        } catch (ConstraintViolationException e) {
            return "fail_duplicate";
        } catch (DataException e) {
            return "fail_toolong";
        } catch (Exception e) {
            return "fail";
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public void update(DepartDto departDto) {
        try {
            departService.update(departDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(DepartDto departDto) {
        try {
            departService.delete(departDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
