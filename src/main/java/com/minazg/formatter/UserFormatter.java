package com.minazg.formatter;

import com.minazg.model.User;
import com.minazg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class UserFormatter implements Formatter<User> {

    @Autowired
    private UserService userService;

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        if(text != null && !text.isEmpty()){
            return userService.findById(Long.parseLong(text));
        }
        return null;
    }

    @Override
    public String print(User object, Locale locale) {
        if(object != null && object.getId() != null){
            return String.valueOf(object.getId());
        }
        return null;
    }
}
