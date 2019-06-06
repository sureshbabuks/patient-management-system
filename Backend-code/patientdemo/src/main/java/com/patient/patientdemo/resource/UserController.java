package com.patient.patientdemo.resource;

import com.patient.patientdemo.model.User;
import com.patient.patientdemo.model.UserResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {


    @MessageMapping("/greeting")
    @SendTo("/topic/bp")
    public UserResponse getUser(User user) {

        return new UserResponse("Hi " + user.getName());
    }
}