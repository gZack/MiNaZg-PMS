package com.minazg.repository;

import com.minazg.model.Comment;
import com.minazg.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
    public List<Comment> findByComponentIdAndComponentTypeOrderByDateCommentedDesc(Long componentId, String componentType);
    public void deleteByIdAndProposerId(Long commentId, Long userId);
    public Long countByComponentIdAndComponentType(Long componentId, String componentType);
}
