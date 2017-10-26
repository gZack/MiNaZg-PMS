package com.minazg.service;

import java.util.List;

import com.minazg.model.User;
import org.springframework.data.domain.Pageable;

public interface UserService {
	
	User findById(Long id);
	
	User findBySSO(String sso);
	
	User saveUser(User user);
	
	User updateUser(User user);
	
	void deleteUserBySSO(String sso);

	List<User> findAllUsers(Pageable pageable);
	
	boolean isUserSSOUnique(Long id, String sso);

	/**
	 * Finds user by having a role of @roleName
	 * @param roleName
	 * @return @{@link List<User>}
	 */
	List<User> findUsersByRoleName(String roleName);

	/**
	 * Returns currently authenticated user
	 * @return @{@link User}
	 */
	User getCurrentAuthenticatedUser();

	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 * @return boolean
	 */
	boolean isUserAuthenticated();


	int totalRecord();

	List<User> filterUserByCriteria(String query, Pageable pageable);

}