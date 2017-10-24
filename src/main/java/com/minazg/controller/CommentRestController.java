package com.minazg.controller;

import com.minazg.model.Comment;
import com.minazg.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/comment")
public class CommentRestController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value="/add", method= RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    HashMap<String, Object> addCommentAjax(@Valid @RequestBody Comment comment){
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
}
