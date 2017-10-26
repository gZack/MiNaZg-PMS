package com.minazg.repository;

import com.minazg.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    User findUserBySsoId(String ssoId);

    Integer deleteBySsoId(String ssoId);

    List<User> findUsersByUserRoles_Name(String username);

    int countAllByIdIsNotNull();

    List<User> findBySsoIdContainingOrFirstNameContainingOrLastNameContaining(String q1, String q2, String q3, Pageable pageable);
}
