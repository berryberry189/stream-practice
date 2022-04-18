package com.grace.streampractice.chap10.service;

import com.grace.streampractice.chap10.model.User;

public interface EmailProvider {

    String getEmail(User user);
}
