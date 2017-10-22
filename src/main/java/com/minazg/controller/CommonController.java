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
            user = userService.getCurrentAuthenticatedUser();
        }
        catch (Exception e){
            System.out.println("User not Logged In!");
        }
        return user;
    }

}
