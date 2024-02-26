package com.seyed.otpsending.controller;

import com.seyed.otpsending.entity.User;
import com.seyed.otpsending.service.EmailSendingService;
import com.seyed.otpsending.service.EmailVerificationService;
import com.seyed.otpsending.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController

@RequestMapping("/api")
public class RegistrationController {
    private final UserService userService;
    private final EmailSendingService emailSendingService;
    private final EmailVerificationService emailVerificationService;

    public RegistrationController(UserService userService, EmailSendingService emailSendingService, EmailVerificationService emailVerificationService){
        this.userService = userService;
        this.emailSendingService = emailSendingService;
        this.emailVerificationService = emailVerificationService;
    }

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody User user){
        //to return response using Map here

        //register the user without email verification
        User registeredUser = userService.registeruser(user);

        //generate otp and send to user for email verification
        emailSendingService.sendOTPEmail(user.getEmail());
        /*
        to print stored otp inside program, no using of persistence storage (db) as otp are short-lived
        for (Map.Entry<String, String> entry : emailOTPMapping.entrySet()) {
            System.err.println(entry.getKey() + " : " + entry.getValue());
        }
        */
        Map<String, String> response = new HashMap<>();
        response.put("status","success");
        response.put("message","If email provided is valid, you will shortly receive an OTP to verify your account.");

        return response;
    }

    @PostMapping("/verify-OTP")
    public Map<String,String> verifyOTP(@RequestParam String email, @RequestParam String otp){
        return emailVerificationService.verifyOTP(email,otp);
    }
}