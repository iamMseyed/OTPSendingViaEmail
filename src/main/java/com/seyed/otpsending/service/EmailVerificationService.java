package com.seyed.otpsending.service;

import com.seyed.otpsending.entity.User;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {
    private final UserService userService;

    public EmailVerificationService(UserService userService) {
        this.userService = userService;
    }
    public static final Map<String, String> storedEmailOTP = new HashMap<>(); //to store otp without using db
    public Map<String, String> verifyOTP(String email, String otp) {
        String storedOTP = storedEmailOTP.get(email);

        Map<String, String> response = new HashMap<>();
        //check whether user is registered
        User user = null;
        user = userService.getUserByEmail(email);
        if(user!=null){
            //check if storedOTP is set and equals to otp which is passed by used
            if (storedOTP != null && storedOTP.equals(otp)) {
                user = userService.getUserByEmail(email);
                storedEmailOTP.remove(email); //remove particular entry from storedEmailOTP map which is storing otp associated with email
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