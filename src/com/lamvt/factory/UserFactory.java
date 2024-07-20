package com.lamvt.factory;

import com.lamvt.abstraction.User;
import com.lamvt.entity.Customer;
import com.lamvt.entity.Staff;

public class UserFactory {
    private static final UserFactory userFactory = new UserFactory();
    private UserFactory(){}
    public static UserFactory getInstance(){
        return userFactory;
    }
    public User getUser(String userName, String fullName, String phoneNumber, String email, String passWord, String address, String typeUser) {
        String typeUserToUpperCase = typeUser.toUpperCase();
        return switch (typeUserToUpperCase) {
            case "CUSTOMER" -> new Customer(userName,fullName, phoneNumber, email, passWord, address);
            case "STAFF" -> new Staff(userName,fullName, phoneNumber, email, passWord,address);
            default -> null;
        };
    }
}
