package com.minazg.service;

import java.util.List;

import com.minazg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minazg.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	public User findBySSO(String sso) {
		return userRepository.findUserBySsoId(sso);
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		//User entity = dao.findById(user.getId());
		User entity = userRepository.findOne(user.getId());
		if(entity!=null){
			entity.setSsoId(user.getSsoId());
			entity.setPassword(user.getPassword());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setUserRoles(user.getUserRoles());
		}

	}
	
	public void deleteUserBySSO(String sso) {
		userRepository.deleteBySsoId(sso);
	}

	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	public boolean isUserSSOUnique(Long id, String sso) {
		User user = findBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}
	
}
