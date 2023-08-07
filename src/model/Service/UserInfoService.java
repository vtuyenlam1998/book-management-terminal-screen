package model.Service;

import model.Entity.User.Customer;
import model.Entity.User.Staff;
import model.abstraction.User;
import model.ulti.ReadFileUlti;

import java.util.ArrayList;
import java.util.List;

public class UserInfoService {
    private static final UserInfoService userInfoService = new UserInfoService();

    private UserInfoService() {
    }

    public static UserInfoService getInstance() {
        return userInfoService;
    }

    private static final List<Customer> CUSTOMER_LIST = new ArrayList<>();
    private static final List<Staff> STAFF_LIST = new ArrayList<>();
    private static final List<User> USER_LIST = new ArrayList<>();
    private User currentUser;

    static {
        List<Customer> customerList = ReadFileUlti.fileReadingCustomer("src\\model\\data\\customer.csv");
        CUSTOMER_LIST.addAll(customerList);
        List<Staff> staffList = ReadFileUlti.fileReadingStaff("src\\model\\data\\staff.csv");
        STAFF_LIST.addAll(staffList);
        USER_LIST.addAll(STAFF_LIST);
        USER_LIST.addAll(CUSTOMER_LIST);
    }
}
