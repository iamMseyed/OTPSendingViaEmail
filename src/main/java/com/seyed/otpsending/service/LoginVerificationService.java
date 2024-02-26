package com.seyed.otpsending.service;

import com.seyed.otpsending.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.seyed.otpsending.service.EmailVerificationService.storedEmailOTP;

@Service
public class LoginVerificationService {
    private final UserService userService;
    private final EmailSendingService emailSendingService;
    public LoginVerificationService(UserService userService, EmailSendingService emailSendingService){
        this.userService = userService;
        this.emailSendingService = emailSendingService;
    }
    public Map<String, String> sendOTPtoLogin(String email) {
        Map<String, String> response = new HashMap<>();
        User user = null;
        user = userService.getUserByEmail(email);
        if(user!=null){
            if(userService.isEmailVerified(email)){
                String otp = emailSendingService.generateOTP();

                storedEmailOTP.put(email,otp);

                //send otp to user's email id
                emailSendingService.sendOTPLogin(email);
                response.put("status","success");
                response.put("message","OTP sent successfully!");
            }else{
                response.put("status","error");
                response.put("message","Email not verified!");
            }
        }else{
            response.put("status","error");
            response.put("message","Email not registered!");
        }

        return response;
    }

    public Map<String, String> verifyOTPtoLogin(String email, String otp) {
        String storedOTP = storedEmailOTP.get(email);

        Map<String,String> response  = new HashMap<>();

        User user = null;
        user = userService.getUserByEmail(email);
        if(user!=null){
            if(userService.isEmailVerified(email)){
                if(storedOTP!=null && storedOTP.equals(otp)){
//            valid otp
                    storedEmailOTP.remove(email); //once verified otp, remove from otp generated map
                    response.put("status","success");
                    response.put("message","OTP verified successfully!");
                }else {
//            invalid otp
                    response.put("status","error");
                    response.put("message","Invalid OTP!");
                }
            }else{
                response.put("status","error");
                response.put("message","Email not verified!");
            }
        }else{
            response.put("status","error");
            response.put("message","Email not registered!");
        }
        return  response;
    }
}