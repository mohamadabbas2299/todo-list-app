package com.abbas.todo_list_app.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    // ✅ Twilio Credentials (Get from Twilio Console)
    private static final String TWILIO_SID = "ACf9eca9859d67849a4c2b47d0f7f8009b";
    private static final String TWILIO_AUTH_TOKEN = "18887807d0d4cc65b7031a8db9028cdb";
    private static final String TWILIO_PHONE_NUMBER = "+16197244326";

    static {
        Twilio.init(TWILIO_SID, TWILIO_AUTH_TOKEN);
    }

    // ✅ Send Email Notification
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        System.out.println("✅ Email Sent to: " + to);
    }

    // ✅ Send SMS Notification
    public void sendSms(String phoneNumber, String messageBody) {
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(phoneNumber),
                new com.twilio.type.PhoneNumber(TWILIO_PHONE_NUMBER),
                messageBody
        ).create();

        System.out.println("✅ SMS Sent to: " + phoneNumber);
    }
}
