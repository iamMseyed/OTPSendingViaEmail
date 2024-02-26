package com.seyed.otpsending.controller;

import com.seyed.otpsending.service.LoginVerificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final LoginVerificationService loginVerificationService;
    public LoginController(LoginVerificationService loginVerificationService){
        this.loginVerificationService = loginVerificationService;
    }
    @PostMapping("/send-otp-to-login")
    public Map<String,String> sendOTPtoLogin(@RequestParam String email){
        return loginVerificationService.sendOTPtoLogin(email);
    }

    @PostMapping("/verify-otp-to-login")
    public Map<String,String> verifyOTPtoLogin(@RequestParam String email, @RequestParam String otp){
        return loginVerificationService.verifyOTPtoLogin(email,otp);
    }
}
