package com.minazg.service;

import com.minazg.model.UserRole;
import com.minazg.repository.UserRepository;
import com.minazg.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRole findByRoleName(String type) {
        return userRoleRepository.findOne(type);
    }

    @Override
    public List<UserRole> findAll() {
        return (List<UserRole>) userRoleRepository.findAll();
    }

    @Override
    public List<UserRole> findRoleByUserId(Long userId) {
        return null;
    }
}
