package com.minazg.controller;

import com.minazg.model.Sprint;
import com.minazg.service.SprintService;
import com.minazg.util.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping("/sprint")
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @Autowired
    HelperUtils helperUtils;


    @RequestMapping(value = {"", "/", "/list"})
    public String list(Model model) {
        model.addAttribute("sprints", sprintService.findAll());
        return "sprint/listSprint";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewSprintForm(@ModelAttribute("newSprint") Sprint newSprint, Model model) {
        model.addAttribute("statusTypes", helperUtils.getStatusTypes());
        return "sprint/addSprint";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewSprintForm(@ModelAttribute("newSprint") @Valid Sprint newSprint, BindingResult result) {
        if(result.hasErrors()) {
            return "sprint/addSprint";
        }

        sprintService.saveSprint(newSprint);
        return "redirect:/sprint/list";
    }


}