package com.minazg.repository;

import com.minazg.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

    User findUserBySsoId(String ssoId);

    Integer deleteBySsoId(String ssoId);

    List<User> findUsersByUserRoles_Name(String username);
}
