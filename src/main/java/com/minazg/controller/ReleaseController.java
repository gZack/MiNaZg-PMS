package com.minazg.controller;

import com.minazg.model.Project;
import com.minazg.model.Release;
import com.minazg.model.StatusType;
import com.minazg.service.ProjectService;
import com.minazg.service.ReleaseService;
import com.minazg.util.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/release")
public class ReleaseController {

    @Autowired
    ReleaseService releaseService;

    @Autowired
    ProjectService projectService;

    @Autowired
    HelperUtils helperUtils;


    @RequestMapping(value = {"/list"})
    public String list(@RequestParam(value = "projectId", required = false) String projectId, Model model) {

        List<Release> release = null;

        try {

            release = releaseService.findReleaseByProjectId(Long.valueOf(projectId));

        } catch (Exception e) {

        }

        model.addAttribute("projectId",projectId);
        model.addAttribute("releases", releaseService.findReleaseByProjectId(Long.valueOf(projectId)));
        model.addAttribute("projectTitle", projectService.findOne(Long.valueOf(projectId)).getName());
        return "release/listRelease";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addReleaseForm(@RequestParam(value = "projectId", required = false) Long projectId,
                                 Model model) {

        Release release = new Release();
        model.addAttribute("newRelease",release);

        if(projectId != null){

            Project project = projectService.findOne(projectId);
            release.setProject(project);

            model.addAttribute("project",project);

            model.addAttribute("projectId", projectId);

        } else {

            model.addAttribute("projects", projectService.findAll());
        }

        model.addAttribute("statusTypes", helperUtils.getStatusTypes());

        return "release/addRelease";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addRelease(@Valid @ModelAttribute("newRelease") Release release, BindingResult br,
                             @RequestParam(value = "projectId",required = false) String projectId,
                             Model model) {

        if (br.hasErrors()) {
            model.addAttribute("projectName", release.getProject().getName());
            model.addAttribute("projectId", release.getProject().getId());
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("newRelease", release);
            return "release/addRelease";
        }
        releaseService.save(release);

        return "redirect:/release/list";
    }

    @RequestMapping(value = {"/edit/{releaseId}"}, method = RequestMethod.GET)
    public String editReleaseForm(@PathVariable("releaseId") String releaseId, Model model) {
        Release release = null;
        try {
            release = releaseService.findOne(Long.valueOf(releaseId));
            model.addAttribute("projectName", release.getProject().getName());
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("action", "edit");
        } catch (Exception e) {
            return "release/notFound";
        }
        model.addAttribute("newRelease", release);
        return "release/addRelease";
    }

    @RequestMapping(value = {"/edit/{releaseId}"}, method = RequestMethod.POST)
    public String editReleaseForm(@Valid @ModelAttribute("newRelease") Release release, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            //todo
            model.addAttribute("projectName", release.getProject().getName());
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("action", "edit");
            return "release/addRelease";
        }
        releaseService.save(release);

        return "redirect:/release/list";

    }

    @RequestMapping(value = "/detail/{releaseId}")
    public String releaseDetail(@PathVariable("releaseId") String releaseId, Model model) {

        try {
            model.addAttribute("release", releaseService.findOne(Long.valueOf(releaseId)));
        } catch (Exception e) {
            return "release/notFound";
        }

        return "release/detail";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String releaseDetail(Model model, @RequestParam(value = "projectId", required = false) String projectId,
                                @RequestParam(value = "versionNumber", required = false) String versionNumber) {

        projectId = (projectId != null) ? projectId : "";
        versionNumber = (versionNumber != null) ? versionNumber : "";


        if (!projectId.equals("") && !versionNumber.equals(""))
            model.addAttribute("releases", releaseService.findByVersionNumberAndProjectId(versionNumber, Long.valueOf(projectId)));

        model.addAttribute("projectName", projectService.findOne(Long.valueOf(projectId)).getName());
        model.addAttribute("projectId", projectId);
        model.addAttribute("statusTypes", helperUtils.getStatusTypes());

        return "release/listRelease";
    }
}
