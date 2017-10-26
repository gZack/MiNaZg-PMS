package com.minazg.service;

import java.util.List;

import com.minazg.exception.UserNotFoundException;
import com.minazg.repository.UserRepository;
import com.minazg.util.SecurityUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minazg.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;

	private final SecurityUtils securityUtils;

	@Autowired
	private AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, SecurityUtils securityUtils) {
		this.securityUtils = securityUtils;
		this.userRepository = userRepository;
	}

	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	public User findBySSO(String sso) {
		User user = userRepository.findUserBySsoId(sso);
		if(user == null){
			throw new UserNotFoundException("Unable to find user with user name: " + sso);
		}
		Hibernate.initialize(user.getUserRoles());
		return user;
	}

	public User saveUser(User user) {
		user.setPassword(securityUtils.encodePassword(user.getPassword()));
		return userRepository.save(user);
	}

	public User updateUser(User user) {

		return userRepository.save(user);

	}
	
	public void deleteUserBySSO(String sso) {
		userRepository.deleteBySsoId(sso);
	}

	public List<User> findAllUsers(Pageable pageable) {

		List<User> users = userRepository.findAll(pageable).getContent();

		Hibernate.initialize(users);

		return users;
	}

	public boolean isUserSSOUnique(Long id, String sso) {
		User user = findBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public List<User> findUsersByRoleName(String roleName) {
		return userRepository.findUsersByUserRoles_Name(roleName);
	}

	@Override
	public User getCurrentAuthenticatedUser() {

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = ((UserDetails)authentication.getPrincipal()).getUsername();

		return findBySSO(username);
	}

	@Override
	public boolean isUserAuthenticated() {

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authenticationTrustResolver.isAnonymous(authentication);

	}

	@Override
	public int totalRecord(){
		return userRepository.countAllByIdIsNotNull();
	}

	@Override
	public List<User> filterUserByCriteria(String query, Pageable pageable) {
		return userRepository
				.findBySsoIdContainingOrFirstNameContainingOrLastNameContaining(query,query,query, pageable);
	}

}
