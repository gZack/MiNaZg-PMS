package com.minazg.service;

import com.minazg.model.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> findAll();
    public List<Comment> loadComment(Long componentId, String componentType);
    public void save(Comment comment);
    public void delete(Long userId, Long commentId);
    public Long countComments(Long componentId, String componentType);
}
