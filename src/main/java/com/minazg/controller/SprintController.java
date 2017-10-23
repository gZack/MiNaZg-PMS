package com.minazg.controller;

import com.minazg.model.Release;
import com.minazg.model.Sprint;
import com.minazg.model.StatusType;
import com.minazg.service.ReleaseService;
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
    ReleaseService releaseService;

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

    @RequestMapping(value = {"/list/{releaseId}"})
    public String list(@PathVariable("releaseId") String releaseId, Model model) {

        List<Sprint> sprints = null;
        try {
            sprints = sprintService.findSprintByReleaseId(Long.valueOf(releaseId));
        } catch (Exception e) {

        }

        model.addAttribute("sprints", sprintService.findSprintByReleaseId(Long.valueOf(releaseId)));
        model.addAttribute("versionNumber", releaseService.findOne(Long.valueOf(releaseId)).getVersionNumber());
        return "sprint/listSprint";
    }

    @RequestMapping(value = {"/add/{releaseId}"}, method = RequestMethod.GET)
    public String addReleaseForm(@PathVariable("releaseId") String releaseId,
                                 @ModelAttribute("newSprint") Sprint sprint,
                                 Model model) {

        model.addAttribute("versionNumber", releaseService.findOne(Long.valueOf(releaseId)).getVersionNumber());
        model.addAttribute("releaseId", releaseId);
        model.addAttribute("statusTypes", helperUtils.getStatusTypes());
        return "sprint/addSprint";
    }

    @RequestMapping(value = "/add/{releaseId}", method = RequestMethod.POST)
    public String addRelease(@Valid @ModelAttribute("newSprint") Sprint sprint, BindingResult br, Model model) {

        if (br.hasErrors()) {
            model.addAttribute("versionNumber", sprint.getRelease().getVersionNumber());
            model.addAttribute("releaseId", sprint.getRelease().getId());
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("newSprint", sprint);
            return "sprint/addSprint";
        }
        sprintService.saveSprint(sprint);

        return "redirect:/sprint/list";
    }

    @RequestMapping(value={"/edit/{sprintId}"}, method = RequestMethod.GET)
    public String editSprintForm(@PathVariable ("sprintId") String sprintId, Model model){
        Sprint sprint = null;
        try{
            sprint = sprintService.findOne(Long.valueOf(sprintId));
            if(sprint == null){
                return "sprint/notFound";
            }
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("versionNumber", sprint.getRelease().getVersionNumber());
            model.addAttribute("action", "edit");
        }
        catch(Exception e){
            System.out.println(e);
            return "sprint/notFound";
        }
        model.addAttribute("newSprint", sprint);
        return "sprint/addSprint";
    }


    @RequestMapping(value={"/edit/{sprintId}"}, method = RequestMethod.POST)
    public String editSprintForm(@Valid @ModelAttribute("newSprint") Sprint sprint, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){

            sprint = sprintService.findOne(Long.valueOf(sprint.getId()));
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("versionNumber", sprint.getRelease().getVersionNumber());
            model.addAttribute("action", "edit");
            return "sprint/addSprint";
        }

        sprintService.updateSprint(sprint);
        redirectAttributes.addFlashAttribute("flashMessage","Sprint Updated Successfully");
        return "redirect:/sprint/list";

    }


    @RequestMapping(value = "/detail/{sprintId}", method = RequestMethod.GET)
    public String showSprint(@PathVariable String sprintId, Model model) {
        try{
            model.addAttribute("sprint",sprintService.findOne(Long.valueOf(sprintId)));
            return "sprint/detail";
        }
        catch(IllegalStateException e){
            System.out.println(e);
            return "sprint/notFound";
        }
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String releaseDetail(Model model, @RequestParam(value = "releaseId", required = false) String releaseId,
                                @RequestParam(value = "sprintTitle", required = false) String sprintTitle) {

        releaseId = (releaseId != null) ? releaseId : "";
        sprintTitle = (sprintTitle != null) ? sprintTitle : "";


        if (!releaseId.equals("") && !sprintTitle.equals(""))
            model.addAttribute("sprint", sprintService.findByReleaseIdAndTitle(Long.valueOf(releaseId), sprintTitle));

        model.addAttribute("releaseNumber", releaseService.findOne(Long.valueOf(releaseId)).getVersionNumber());
        model.addAttribute("releaseId", releaseId);
        model.addAttribute("statusTypes", helperUtils.getStatusTypes());

        return "sprint/listSprint";
    }




}