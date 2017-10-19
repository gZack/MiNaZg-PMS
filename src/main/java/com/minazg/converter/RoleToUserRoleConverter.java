package com.minazg.converter;

import com.minazg.model.UserRole;
import com.minazg.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserRoleConverter implements Converter<Object, UserRole>{

	@Autowired
	UserRoleService userRoleService;

	/**
	 * Gets UserProfile by Id
	 * @see Converter#convert(Object)
	 */
	public UserRole convert(Object element) {
		String roleName = (String)element;
		UserRole userRole= userRoleService.findByRoleName(roleName);
		System.out.println("Profile : "+userRole);
		return userRole;
	}
	
}