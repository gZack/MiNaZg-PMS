package com.minazg.model;

public enum UserRoleType {
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");

	String userRoleType;

	private UserRoleType(String userProfileType){
		this.userRoleType = userProfileType;
	}
	
	public String getUserRoleType(){
		return userRoleType;
	}
	
}
