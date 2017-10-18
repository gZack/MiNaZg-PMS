package com.minazg.service;

import java.util.List;

import com.minazg.model.UserProfile;


public interface UserProfileService {

	UserProfile findById(Integer id);
	
	List<UserProfile> findAll();
	
}
