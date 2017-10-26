package com.minazg.controller;

import com.minazg.model.User;
import com.minazg.service.UserService;
import org.apache.commons.lang.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CommonController {

    @Autowired
    UserService userService;

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    public static final String PROFILE_PIC_PATH = "static/img/${id}.png";

    @ModelAttribute("userDetail")
    public User getUserDetail(Model model, HttpServletRequest request){

        User user = null;

        try {

            user = userService.getCurrentAuthenticatedUser();

            String[] uploadLocations = messageSourceAccessor
                    .getMessage("upload.locations").split(",");

            String imageName = user.getId() + ".png";

            File imageFile = null; //new File(picPath);

            for(String uploadLocation : uploadLocations){
                if(new File(uploadLocation).exists()){
                    imageFile = new File(uploadLocation + imageName);
                    break;
                }
            }

            if(imageFile != null && imageFile.exists()){
                model.addAttribute("hasProfPic", true);
            }

        } catch (Exception e){
            System.out.println("User not Logged In!");
        }
        return user;
    }

}
