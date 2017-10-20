package com.minazg.repository;

import com.minazg.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

    User findUserBySsoId(String ssoId);

    Integer deleteBySsoId(String ssoId);

}
