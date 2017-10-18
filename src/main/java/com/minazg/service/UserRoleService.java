package com.minazg.service;

import com.minazg.model.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole findByRoleName(String type);

    List<UserRole> findAll();

    List<UserRole> findRoleByUserId(Long userId);
}
