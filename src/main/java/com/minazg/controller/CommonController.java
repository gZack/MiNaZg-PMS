package com.minazg.controller;

import com.minazg.model.User;
import com.minazg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class CommonController {

    @Autowired
    UserService userService;

    @ModelAttribute("userDetail")
    public User getUserDetail(){
        User user = null;
        try {
            user = userService.findBySSO(SecurityContextHolder.getContext().getAuthentication().getName());
            System.out.println("my role is " + user.getUserRoles());
            if(user.getUserRoles().contains("PROJECT_MANAGER"));{
                System.out.println("I AM PROJECT MANAGER");
            }
        }
        catch (NullPointerException e){
            System.out.println("User not Logged In!");
        }
        return user;
    }
//
//    @ModelAttribute("userRole")
//    public void getUserRoles(){
//        userService.get
//    }
}
