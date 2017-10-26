package com.minazg.controller;

import com.minazg.model.StatusType;
import com.minazg.model.User;
import com.minazg.model.UserRoleType;
import com.minazg.model.WorkOrder;
import com.minazg.service.TaskService;
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
import java.util.stream.Collectors;

@ControllerAdvice
public class CommonController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService TaskService;

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

    @ModelAttribute("developerTasks")
    public HashMap<String, List<WorkOrder>> getTasks(){
        try{
            User user = userService.getCurrentAuthenticatedUser();
            //if(user.getUserRoles().contains(UserRoleType.DEVELOPER.toString())){
                HashMap<String, List<WorkOrder>> developerTasks = new HashMap<String, List<WorkOrder>>();
                List<WorkOrder> tasks = TaskService.getMyTasks();
                //developerTasks.put("tasks", tasks);

                List<WorkOrder> newTasks =
                        tasks.stream()
                                .filter( t-> t.getStatus().equals(StatusType.CREATED.toString()))
                                .limit(5)
                                .collect(Collectors.toList());
                developerTasks.put("newTasks", newTasks);

                return developerTasks;
            //}
           // return null;
        }
        catch(Exception e){
            return null;
        }

    }
}
