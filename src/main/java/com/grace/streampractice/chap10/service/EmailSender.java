package com.grace.streampractice.chap10.service;

import com.grace.streampractice.chap10.model.User;

public class EmailSender{

    private EmailProvider emailProvider;

    public EmailSender setEmailProvider(EmailProvider emailProvider){
        this.emailProvider = emailProvider;
        return this;
    }

    public void sendEmail(User user){
        String email = emailProvider.getEmail(user);
        System.out.println("Sending " + email);
    }
}
