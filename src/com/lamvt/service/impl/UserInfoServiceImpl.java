package com.lamvt.service.impl;

import com.lamvt.abstraction.User;
import com.lamvt.entity.Customer;
import com.lamvt.entity.Staff;
import com.lamvt.ulti.ReadFileUlti;

import java.util.ArrayList;
import java.util.List;

public class UserInfoServiceImpl {
    private static final UserInfoServiceImpl USER_INFO_SERVICE_IMPL = new UserInfoServiceImpl();

    private UserInfoServiceImpl() {
    }

    public static UserInfoServiceImpl getInstance() {
        return USER_INFO_SERVICE_IMPL;
    }

    private static final List<Customer> CUSTOMER_LIST = new ArrayList<>();
    private static final List<Staff> STAFF_LIST = new ArrayList<>();
    private static final List<User> USER_LIST = new ArrayList<>();
    private User currentUser;

    static {
        List<Customer> customerList = ReadFileUlti.fileReadingCustomer("src\\com\\lamvt\\data\\customer.csv");
        CUSTOMER_LIST.addAll(customerList);
        List<Staff> staffList = ReadFileUlti.fileReadingStaff("src\\com\\lamvt\\data\\staff.csv");
        STAFF_LIST.addAll(staffList);
        USER_LIST.addAll(STAFF_LIST);
        USER_LIST.addAll(CUSTOMER_LIST);
    }
}
