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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/release")
public class ReleaseController {

    @Autowired
    ReleaseService releaseService;

    @Autowired
    HelperUtils helperUtils;

    @RequestMapping(value = {"", "/", "/list"})
    public String list(Model model){
        model.addAttribute("releases", releaseService.findAll());
        return "release/listRelease";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addReleaseForm(@ModelAttribute("newRelease") Release release, Model model){

        model.addAttribute("statusTypes", helperUtils.getStatusTypes());
        return "release/addRelease";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addRelease(@Valid @ModelAttribute("newRelease") Release release, BindingResult br, RedirectAttributes ra){

        if (br.hasErrors()){
            return "release/addRelease";
        }
        releaseService.save(release);

        return "redirect:/release/list";
    }
}
