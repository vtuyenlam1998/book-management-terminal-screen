package com.lamvt.builder;

import com.lamvt.abstraction.User;

public interface UserBuilder {
    UserBuilder setID(int id);
    UserBuilder setFullName(String fullName);
    UserBuilder setPassWord(String passWord);
    UserBuilder setEmail(String email);
    UserBuilder setPhoneNumber(String phoneNumber);
    UserBuilder setAddress(String address);
    User build();
}
