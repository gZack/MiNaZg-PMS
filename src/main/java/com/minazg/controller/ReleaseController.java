package com.minazg.controller;

import com.minazg.model.*;
import com.minazg.service.ProjectService;
import com.minazg.service.ReleaseService;
import com.minazg.service.UserService;
import com.minazg.util.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    UserService userService;

    @ModelAttribute("projects")
    public List<Project> collectProjects(){

        return projectService.findAll();
    }

    @RequestMapping(value = {"/list"})
    public String list(@RequestParam(value = "projectId", required = false) String projectId, Model model) {

        List<Release> release = null;

        try {

            release = releaseService.findReleaseByProjectId(Long.valueOf(projectId));

        } catch (Exception e) {

        }

        model.addAttribute("projectId", projectId);
        model.addAttribute("releases", releaseService.findReleaseByProjectId(Long.valueOf(projectId)));
        model.addAttribute("projectTitle", projectService.findOne(Long.valueOf(projectId)).getName());
        return "release/listRelease";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addReleaseForm(@RequestParam(value = "projectId", required = false) Long projectId,
                                 Model model) {

        Release release = new Release();
        model.addAttribute("newRelease", release);

        if (projectId != null) {

            Project project = projectService.findOne(projectId);
            release.setProject(project);

            model.addAttribute("project", project);

            model.addAttribute("projectId", projectId);

        } else {

            model.addAttribute("projects", projectService.findAll());
        }

        model.addAttribute("statusTypes", helperUtils.getStatusTypes());

        return "release/addRelease";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addRelease(@Valid @ModelAttribute("newRelease") Release release, BindingResult br,
                             @RequestParam(value = "projectId", required = false) String projectId,
                             Model model, RedirectAttributes ra) {

        if (br.hasErrors()) {
            model.addAttribute("projectName", release.getProject().getName());
            model.addAttribute("projectId", release.getProject().getId());
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("newRelease", release);
            return "release/addRelease";
        }

        releaseService.save(release);
        ra.addFlashAttribute("flashMessage", "Release Added Successfully");
        return "redirect:/release/list?projectId=" + projectId;
    }

    @RequestMapping(value = {"/edit/{releaseId}"}, method = RequestMethod.GET)
    public String editReleaseForm(@PathVariable("releaseId") String releaseId, Model model) {
        Release release = null;
        try {
            release = releaseService.findOne(Long.valueOf(releaseId));
            model.addAttribute("projectId", release.getProject().getId());
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
    public String editReleaseForm(@Valid @ModelAttribute("newRelease") Release release, BindingResult bindingResult, Model model,
                                  RedirectAttributes ra) {

        if (bindingResult.hasErrors()) {
            //todo
            model.addAttribute("projectName", release.getProject().getName());
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("action", "edit");
            return "release/addRelease";
        }
        releaseService.save(release);

        ra.addFlashAttribute("flashMessage", "Release Edited Successfully");
        ra.addFlashAttribute("projectId", release.getProject().getId());
        return "redirect:/release/list?projectId=" + release.getProject().getId();

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
        model.addAttribute("projectTitle", projectService.findOne(Long.valueOf(projectId)).getName());
        model.addAttribute("projectId", projectId);
        model.addAttribute("statusTypes", helperUtils.getStatusTypes());

        return "release/listRelease";
    }

    @RequestMapping(value = {"/releaseByProject"}, method = RequestMethod.GET)
    public String listPerProject(Model model, @RequestParam(value = "projects", required = false) String projects) {

        projects = (projects != null) ? projects : "";

        if (!projects.equals(""))
            model.addAttribute("releases", releaseService.findReleaseByProjectId(Long.valueOf(projects)));

        model.addAttribute("projectName", projectService.findOne(Long.valueOf(projects)).getName());
        model.addAttribute("projectTitle", projectService.findOne(Long.valueOf(projects)).getName());
        model.addAttribute("projectId", projects);
        model.addAttribute("statusTypes", helperUtils.getStatusTypes());

        return "release/listRelease";
    }
}
