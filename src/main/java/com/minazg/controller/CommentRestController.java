package com.minazg.controller;

import com.minazg.model.Comment;
import com.minazg.model.User;
import com.minazg.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentRestController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value="/add", method= RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    HashMap<String, Object> addCommentAjax(@RequestBody Comment comment){
        try{
            HashMap newComment = new HashMap<String, Object>();
            comment = commentService.save(comment);
            newComment.put("commentId", comment.getId());
            newComment.put("commentDate", comment.getDateCommented());
            newComment.put("commentBody", comment.getStatement());
            newComment.put("commentUserId", comment.getProposer().getId());
            newComment.put("commentFirstName", comment.getProposer().getFirstName());
            newComment.put("commentLastName", comment.getProposer().getLastName());
            return newComment;
        }
        catch(Exception e){
            HashMap<String, Object> err = new HashMap<String, Object>();
            err.put("Error", "An Error Has Occured");
            return err;
        }

    }
}
