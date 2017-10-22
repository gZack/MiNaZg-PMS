package com.minazg.controller;

import com.minazg.model.Sprint;
import com.minazg.model.StatusType;
import com.minazg.service.SprintService;
import com.minazg.service.UserService;
import com.minazg.util.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    UserService userService;

    @ModelAttribute("StatusTypes")
    public StatusType[] getStatusTypes(){
        return helperUtils.getStatusTypes();
    }


    @RequestMapping(value = {"", "/", "/list"})
    public String list(Model model) {
        model.addAttribute("sprints", sprintService.findAll());
        return "sprint/listSprint";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String getAddNewSprintForm(@ModelAttribute("newSprint") Sprint newSprint, Model model) {
        model.addAttribute("statusTypes", helperUtils.getStatusTypes());
        model.addAttribute("projectManagerList",userService.findUsersByRoleName("PROJECT_MANAGER"));
        model.addAttribute("clientList",userService.findUsersByRoleName("CLIENT"));
        return "sprint/addSprint";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewSprintForm(@ModelAttribute("newSprint") @Valid Sprint newSprint, RedirectAttributes redirectAttributes, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("projectManagerList",userService.findUsersByRoleName("PROJECT_MANAGER"));
            model.addAttribute("clientList",userService.findUsersByRoleName("CLIENT"));
            return "sprint/addSprint";
        }

        sprintService.saveSprint(newSprint);
        redirectAttributes.addFlashAttribute("flashMessage","Sprint Added Successfully");
        return "redirect:/sprint/list";
    }

    @RequestMapping(value={"/edit/{sid}"}, method = RequestMethod.GET)
    public String editProjectForm(@PathVariable ("sid") String sid, Model model){
        Sprint sprint = null;
        try{
            sprint = sprintService.findById(Long.valueOf(sid));
            if(sprint == null){
                return "sprint/notFound";
            }
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("projectManagerList",userService.findUsersByRoleName("PROJECT_MANAGER"));
            model.addAttribute("clientList",userService.findUsersByRoleName("CLIENT"));
            model.addAttribute("action", "edit");
        }
        catch(Exception e){
            System.out.println(e);
            return "sprint/notFound";
        }
        model.addAttribute("newSprint", sprint);
        return "sprint/addSprint";
    }

    @RequestMapping(value={"/edit/{sid}"}, method = RequestMethod.POST)
    public String editProjectForm(@Valid @ModelAttribute("newSprint") Sprint sprint, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){

            sprint = sprintService.findById(Long.valueOf(sprint.getId()));
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("projectManagerList",userService.findUsersByRoleName("PROJECT_MANAGER"));
            model.addAttribute("clientList",userService.findUsersByRoleName("CLIENT"));
            model.addAttribute("action", "edit");
            model.addAttribute("newSprint", sprint);
            return "sprint/addProject";
        }

        sprintService.updateSprint(sprint);
        redirectAttributes.addFlashAttribute("flashMessage","Sprint Updated Successfully");
        return "redirect:/sprint/list";

    }

    @RequestMapping(value = "/detail/{sid}", method = RequestMethod.GET)
    public String showSprint(@PathVariable String sid, Model model) {
        try{
            model.addAttribute("sprint",sprintService.findById(Long.valueOf(sid)));
            return "sprint/detail";
        }
        catch(IllegalStateException e){
            System.out.println(e);
            return "sprint/notFound";
        }
    }


}