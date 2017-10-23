package com.minazg.converter;

import com.minazg.model.User;
import com.minazg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<Object, User> {

    @Autowired
    private UserService userService;

    @Override
    public User convert(Object source) {

        User user = (User)source;

        if(user != null && user.getId() != null){

            return userService.findById(user.getId());

        }

        return null;
    }
}
