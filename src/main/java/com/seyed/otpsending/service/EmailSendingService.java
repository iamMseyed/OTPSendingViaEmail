package com.seyed.otpsending.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import static com.seyed.otpsending.service.EmailVerificationService.storedEmailOTP;

@Service
public class EmailSendingService {
    private final JavaMailSender javaMailSender;
    public EmailSendingService(JavaMailSender javaMailSender) {
        this.javaMailSender= javaMailSender;
    }
    public String generateOTP(){
        return String.format("%06d",new java.util.Random().nextInt(1000000));
        //generates a random value of 6 digits in the given range
    }
    public void sendOTPEmail(String email) {
        String otpMail = this.generateOTP();
        storedEmailOTP.put(email,otpMail);
        sendEmail(email,"OTP for Email verification","Please use this OTP to verify your email id: "+otpMail);
    }
    public void sendOTPLogin(String email) {
        String otpLogin = generateOTP();
        storedEmailOTP.put(email,otpLogin);
        sendEmail(email,"OTP for Login verification","Please use this OTP to verify your Login: "+otpLogin);
    }
    public void sendEmail(String to, String subject, String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }
}