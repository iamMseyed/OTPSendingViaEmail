package com.seyed.otpsending.service;

import com.seyed.otpsending.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {
    private final UserService userService;

    public EmailVerificationService(UserService userService, EmailSendingService emailSendingService) {
        this.userService = userService;
    }
    public static final Map<String, String> emailOTPMapping = new HashMap<>(); //to store otp temporarily
    public Map<String, String> verifyOTP(String email, String otp) {
        String storedOTP = emailOTPMapping.get(email);

        Map<String, String> response = new HashMap<>();
        //check whether user is registered
        User user = null;
        user = userService.getUserByEmail(email);
        if(user!=null){
            //check if storedOTP is set and equals to otp which is passed by used
            if (storedOTP != null && storedOTP.equals(otp)) {
                user = userService.getUserByEmail(email);
                emailOTPMapping.remove(email); //remove particular entry from map which is storing otp associated with email
                userService.verifyEmail(user);
                response.put("status", "success");
                response.put("message", "Email verified successfully!");
            } else {
                response.put("status", "error");
                response.put("message", "Invalid OTP!");
            }
        }else {
            response.put("status", "error");
            response.put("message", "Email not registered!");
        }


        return response;
    }
}