package com.minazg.controller;

import com.minazg.model.Comment;
import com.minazg.service.CommentService;
import com.minazg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@SessionAttributes("redirectUrl")
public class CommentController {

    @Autowired
    CommentService commentService;
    @RequestMapping(value="/comment/add", method= RequestMethod.POST)
    public String addComment(@Valid @ModelAttribute("newComment") Comment comment,
                             BindingResult bindingResult,
                             Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("commentMessage", "Error: Unable to add Comment");
            return "redirect:"+model.asMap().get("redirectUrl");
        }

        commentService.save(comment);
        redirectAttributes.addFlashAttribute("commentMessage", "Success: Comment Added Successfully");
        return "redirect:"+model.asMap().get("redirectUrl");
    }
}
