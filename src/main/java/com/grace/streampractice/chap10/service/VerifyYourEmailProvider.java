package com.grace.streampractice.chap10.service;

import com.grace.streampractice.chap10.model.User;

public class VerifyYourEmailProvider implements EmailProvider {

    @Override
    public String getEmail(User user) {
        return "'Verify Your Email Address' email for " + user.getName();
    }
}
