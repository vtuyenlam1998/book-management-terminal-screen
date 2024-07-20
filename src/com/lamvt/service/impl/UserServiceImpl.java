package com.lamvt.service.impl;

import com.lamvt.abstraction.User;
import com.lamvt.constant.UserConstant;
import com.lamvt.entity.Customer;
import com.lamvt.entity.Staff;
import com.lamvt.exception.InvalidAccountException;
import com.lamvt.exception.InvalidChoiceException;
import com.lamvt.ulti.Input;
import com.lamvt.ulti.ReadFileUlti;
import com.lamvt.ulti.WriteFileUlti;
import com.lamvt.factory.UserFactory;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements com.lamvt.service.UserService {
    private static final UserServiceImpl USER_SERVICE_IMPL = new UserServiceImpl();
    private boolean checkCustomer =false;
    private boolean checkStaff = false;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return USER_SERVICE_IMPL;
    }

    private static final List<Customer> CUSTOMER_LIST = new ArrayList<>();
    private static final List<Staff> STAFF_LIST = new ArrayList<>();
    private static final List<User> USER_LIST = new ArrayList<>();
    private User currentUser;

    static {
        List<Customer> customerList = ReadFileUlti.fileReadingCustomer(UserConstant.CUSTOMER_FILE_PATH);
        CUSTOMER_LIST.addAll(customerList);
        List<Staff> staffList = ReadFileUlti.fileReadingStaff(UserConstant.STAFF_FILE_PATH);
        STAFF_LIST.addAll(staffList);
        USER_LIST.addAll(STAFF_LIST);
        USER_LIST.addAll(CUSTOMER_LIST);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<User> findAll() {
        return USER_LIST;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(User user) {

    }

    public List<Customer> findAllCustomer() {
        return CUSTOMER_LIST;
    }

    public List<Staff> getStaffList() {
        return STAFF_LIST;
    }

    public boolean isCheckCustomer() {
        return checkCustomer;
    }

    public boolean isCheckStaff() {
        return checkStaff;
    }

    public void login() {
        do {
            try {
                String userName = Input.promt("Nhập tài khoản: ");
                String passWord = Input.promt("Nhập mật khẩu: ");
                String logIn = Input.promt("Xác nhận đăng nhập (Y/N): ");
                if (logIn.equalsIgnoreCase("Y")) {
                    for (User user : USER_LIST) {
                        if (userName.equals(user.getUserName())) {
                            for (Customer customer : CUSTOMER_LIST) {
                                if (userName.equals(customer.getUserName())) {
                                    if (passWord.equals(customer.getPassWord())) {
                                        System.out.println("Đăng nhập thành công! Chào mừng khách hàng: " + userName + " trở lại ");
                                        currentUser = customer;
                                        checkCustomer = true;
                                        return;
                                    }
                                }
                            }
                            for (Staff staff : STAFF_LIST) {
                                if (userName.equals(staff.getUserName())) {
                                    if (passWord.equals(staff.getPassWord())) {
                                        System.out.println("Đăng nhập thành công! Chào mừng nhân viên: " + userName + " trở lại");
                                        currentUser = staff;
                                        checkStaff = true;
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    throw new InvalidAccountException("Sai tài khoản / mật khẩu ");
                } else return;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (true);
    }
    public void signup() {
        System.out.println("""
                1. Bạn là nhân viên
                2. Bạn là người dùng
                3. Quay lại
                """);
        int choice = Input.choiceIntegerInput("Nhập lựa chọn: ");
        do {
            try {
                User newUser;
                switch (choice) {
                    case 1:
                        String staffName = Input.prompt("Nhập tài khoản: ", "USER_NAME");
                        boolean checkExistStaff = false;
                        for (User user : STAFF_LIST) {
                            if (staffName.equals(user.getUserName())) {
                                checkExistStaff = true;
                                break;
                            }
                        }
                        if (checkExistStaff) {
                            throw new InvalidAccountException("Tài khoản này đã tồn tại");
                        } else {
                            String passWord = Input.prompt("Nhập mật khẩu (ít nhất @,viết hoa,thường,số,từ 8 chữ trở lên): ", "PASSWORD");
                            String fullName = Input.promt("Nhập tên họ đầy đủ: ");
                            String phoneNumber = Input.prompt("Nhập số điện thoại (+840xxxx hoặc 0xxxx,10 chữ số): ", "PHONE_NUMBER");
                            String email = Input.prompt("Nhập Email: ", "EMAIL");
                            String address = Input.promt("Nhập địa chỉ: ");
                            UserFactory userFactory = UserFactory.getInstance();
                            newUser = userFactory.getUser(staffName, fullName, phoneNumber, email, passWord, address, "staff");
                            WriteFileUlti.writeFileUser("src\\com\\lamvt\\data\\staff.csv", newUser);
                            STAFF_LIST.add((Staff) newUser);
                            USER_LIST.add(newUser);
                            System.out.println("Chúc mừng bạn đã tạo tài khoản thành công!");
                        }
                        return;
                    case 2:
                        String userName = Input.prompt("Nhập tài khoản: ", "USER_NAME");
                        boolean checkExistUser = false;
                        for (User user : CUSTOMER_LIST) {
                            if (userName.equals(user.getUserName())) {
                                checkExistUser = true;
                                break;
                            }
                        }
                        if (checkExistUser) {
                            throw new InvalidAccountException("Tài khoản này đã tồn tại");
                        } else {
                            String passWord = Input.prompt("Nhập mật khẩu (ít nhất @,viết hoa,thường,số,từ 8 chữ trở lên): ", "PASSWORD");
                            String fullName = Input.promt("Nhập tên họ đầy đủ: ");
                            String phoneNumber = Input.prompt("Nhập số điện thoại (+840xxxx hoặc 0xxxx,10 chữ số): ", "PHONE_NUMBER");
                            String email = Input.prompt("Nhập Email: ", "EMAIL");
                            String address = Input.promt("Nhập địa chỉ: ");
                            UserFactory userFactory = UserFactory.getInstance();
                            newUser = userFactory.getUser(userName, fullName, phoneNumber, email, passWord, address, "customer");
                            WriteFileUlti.writeFileUser("src\\com\\lamvt\\data\\customer.csv", newUser);
                            CUSTOMER_LIST.add((Customer) newUser);
                            USER_LIST.add(newUser);
                            System.out.println("Chúc mừng bạn đã tạo tài khoản thành công!");
                        }
                        return;
                    case 3:
                        return;
                    default:
                        throw new InvalidChoiceException("Nhập đoàng hoàng cái coi!");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (true);
    }

    public void getCustomerInfo() {
        do {
            try {
                CUSTOMER_LIST.forEach(Customer -> System.out.println("ID: " + Customer.getID() + ". " + Customer.getFullName()));
                int ID = Input.choiceIntegerInput("Nhập ID khách hàng muốn xem: ");
                if (ID <= CUSTOMER_LIST.size()) {
                    CUSTOMER_LIST.stream().filter(ele -> ele.getID() == ID).forEach(System.out::println);
                    editCustomerAccountInformation(ID);
                    return;
                } else throw new InvalidChoiceException("Nhập đoàng hoàng cái coi!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public void editAccountInformation() {
        do {
            try {
                System.out.println("""
                        Bạn muốn sửa thông tin nào ?
                        1. Tên họ đầy đủ
                        2. Số điện thoại
                        3. Địa chỉ email
                        4. Địa chỉ nhà
                        5. Mật khẩu
                        6. Quay lại Main Menu
                        """);
                int choice = Input.choiceIntegerInput("Nhập lựa chọn: ");
                switch (choice) {
                    case 1:
                        System.out.println(currentUser.getFullName());
                        String editUserName = Input.promt("Nhập tên mới cho người dùng: ");
                        currentUser.setFullName(editUserName);
                        System.out.println("Bạn đã chỉnh sửa thành công");
                        break;
                    case 2:
                        System.out.println(currentUser.getPhoneNumber());
                        String editPhoneNumber = Input.prompt("Nhập số điện thoại mới cho người dùng: ", "PHONE_NUMBER");
                        currentUser.setPhoneNumber(editPhoneNumber);
                        System.out.println("Bạn đã chỉnh sửa thành công");
                        break;
                    case 3:
                        System.out.println(currentUser.getEmail());
                        String editUserEmail = Input.prompt("Nhập tên mới cho người dùng: ", "EMAIL");
                        currentUser.setEmail(editUserEmail);
                        System.out.println("Bạn đã chỉnh sửa thành công");
                        break;
                    case 4:
                        System.out.println(currentUser.getAddress());
                        String editUserAddress = Input.promt("Nhập tên mới cho người dùng: ");
                        currentUser.setAddress(editUserAddress);
                        System.out.println("Bạn đã chỉnh sửa thành công");
                        break;
                    case 5:
                        String oldPassWordVerify = Input.promt("Nhập lại mật khẩu cũ: ");
                        if (oldPassWordVerify.equals(currentUser.getPassWord())) {
                            String editUserPassword = Input.prompt("Nhập mật khẩu mới cho người dùng: ", "PASSWORD");
                            currentUser.setPassWord(editUserPassword);
                            System.out.println("Bạn đã chỉnh sửa thành công");
                            break;
                        }
                        throw new InvalidAccountException("Sai mật khẩu nhé! Cảnh cáo nhé!");
                    case 6:
                        return;
                    default:
                        throw new InvalidChoiceException("Có nhập đoàng hoàng không thì bảo!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public void editCustomerAccountInformation(int ID) {
        do {
            try {
                System.out.println("""
                        Bạn muốn sửa thông tin nào ?
                        1. Tên họ đầy đủ
                        2. Số điện thoại
                        3. Địa chỉ email
                        4. Địa chỉ nhà
                        5. Mật khẩu
                        6. Quay lại Main Menu
                        """);
                int choice = Input.choiceIntegerInput("Nhập lựa chọn: ");
                switch (choice) {
                    case 1:
                        for (Customer customer : CUSTOMER_LIST) {
                            if (customer.getID() == ID) {
                                System.out.println(customer.getFullName());
                                String editUserName = Input.promt("Nhập tên mới cho người dùng: ");
                                customer.setFullName(editUserName);
                                System.out.println("Bạn đã chỉnh sửa thành công");
                                break;
                            }
                        }
                        break;
                    case 2:
                        for (Customer customer : CUSTOMER_LIST) {
                            if (customer.getID() == ID) {
                                System.out.println(customer.getPhoneNumber());
                                String editPhoneNumber = Input.prompt("Nhập số điện thoại mới cho người dùng: ", "PHONE_NUMBER");
                                customer.setPhoneNumber(editPhoneNumber);
                                System.out.println("Bạn đã chỉnh sửa thành công");
                                break;
                            }
                        }
                        break;
                    case 3:
                        for (Customer customer : CUSTOMER_LIST) {
                            if (customer.getID() == ID) {
                                System.out.println(customer.getEmail());
                                String editUserEmail = Input.prompt("Nhập email mới cho người dùng: ", "EMAIL");
                                customer.setEmail(editUserEmail);
                                System.out.println("Bạn đã chỉnh sửa thành công");
                                break;
                            }
                        }
                        break;
                    case 4:
                        for (Customer customer : CUSTOMER_LIST) {
                            if (customer.getID() == ID) {
                                System.out.println(customer.getAddress());
                                String editUserAddress = Input.promt("Nhập địa chỉ mới cho người dùng: ");
                                customer.setAddress(editUserAddress);
                                System.out.println("Bạn đã chỉnh sửa thành công");
                                break;
                            }
                        }
                        break;
                    case 5:
                        for (Customer customer : CUSTOMER_LIST) {
                            if (customer.getID() == ID) {
                                String oldPassWordVerify = Input.promt("Nhập lại mật khẩu cũ: ");
                                if (oldPassWordVerify.equals(customer.getPassWord())) {
                                    String editUserPassword = Input.prompt("Nhập mật khẩu mới cho người dùng: ", "PASSWORD");
                                    customer.setPassWord(editUserPassword);
                                    System.out.println("Bạn đã chỉnh sửa thành công");
                                    break;
                                } else  { throw new InvalidAccountException("Sai mật khẩu nhé! Cảnh cáo nhé!"); }
                            }
                        }
                        break;
                    case 6:
                        return;
                    default:
                        throw new InvalidChoiceException("Có nhập đoàng hoàng không thì bảo!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
