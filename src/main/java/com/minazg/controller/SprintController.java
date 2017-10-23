package com.minazg.controller;

import com.minazg.model.Release;
import com.minazg.model.Sprint;
import com.minazg.model.StatusType;
import com.minazg.service.SprintService;
import com.minazg.service.UserService;
import com.minazg.util.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

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
    public String list(Model model, @RequestParam(value="q", required = false) String q) {
        List<Sprint> sprints = null;
//        model.addAttribute("sprints", sprintService.findAll());
//       String q = Long.toString(p);
        q = (q != null) ? q : "";
        model.addAttribute("q", q);
        sprints = sprintService.findSprintByTitle(q);
//        sprints = sprintService.findSprintByReleaseId(Long.valueOf(q));
        model.addAttribute("sprints", sprints);
        return "sprint/listSprint";
    }


    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String getAddNewSprintForm(@ModelAttribute("newSprint") Sprint newSprint, Model model) {
        model.addAttribute("statusTypes", helperUtils.getStatusTypes());
//        model.addAttribute("projectManagerList",userService.findUsersByRoleName("PROJECT_MANAGER"));
//        model.addAttribute("clientList",userService.findUsersByRoleName("CLIENT"));
        return "sprint/addSprint";
    }

    @RequestMapping(value={"/add"}, method = RequestMethod.POST)
    public String saveSprint(@Valid @ModelAttribute("newSprint") Sprint sprint, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            // TODO, remove redundant code
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("projectManagerList",userService.findUsersByRoleName("PROJECT_MANAGER"));
            model.addAttribute("clientList",userService.findUsersByRoleName("CLIENT"));
            return "sprint/addSprint";
        }
        sprintService.saveSprint(sprint);
        redirectAttributes.addFlashAttribute("flashMessage","Sprint Added Successfully");
        return "redirect:/sprint/list";
    }

    @RequestMapping(value={"/edit/{sid}"}, method = RequestMethod.GET)
    public String editSprintForm(@PathVariable ("sid") String sid, Model model){
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
    public String editSprintForm(@Valid @ModelAttribute("newSprint") Sprint sprint, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){

            sprint = sprintService.findById(Long.valueOf(sprint.getId()));
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("projectManagerList",userService.findUsersByRoleName("PROJECT_MANAGER"));
            model.addAttribute("clientList",userService.findUsersByRoleName("CLIENT"));
            model.addAttribute("action", "edit");
            return "sprint/addSprint";
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