package com.minazg.repository;

import com.minazg.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
    public List<Comment> findByComponentIdAndComponentType(Long componentId, String componentType);
}
