package com.minazg.service;

import java.util.List;

import com.minazg.dao.UserProfileDao;
import com.minazg.model.UserProfile;
import com.minazg.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
//@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
    UserProfileDao dao;

	private UserRoleRepository roleRepository;

	@Override
	public UserProfile findById(Integer id) {
		return dao.findById(id);
	}

	public List<UserProfile> findAll() {
		return dao.findAll();
	}
}
