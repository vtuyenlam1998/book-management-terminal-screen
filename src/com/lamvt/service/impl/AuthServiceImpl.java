//package java.main.service.impl;
//
//import java.main.abstraction.User;
//import java.main.entity.Customer;
//import java.main.entity.Staff;
//import java.main.exception.InvalidAccountException;
//import java.main.exception.InvalidChoiceException;
//import java.main.factory.UserFactory;
//import java.main.service.AuthService;
//import java.main.ulti.Input;
//import java.main.ulti.WriteFileUlti;
//
//public class AuthServiceImpl implements AuthService {
//
//    @Override
//    public void login() {
//        do {
//            try {
//                String username = Input.promt("Nhập tài khoản: ");
//                String password = Input.promt("Nhập mật khẩu: ");
//                String confirm = Input.promt("Xác nhận đăng nhập (Y/N): ");
//                if (confirm.equalsIgnoreCase("Y")) {
//                    for (User user : USER_LIST) {
//                        if (username.equals(user.getUserName())) {
//                            for (Customer customer : CUSTOMER_LIST) {
//                                if (username.equals(customer.getUserName())) {
//                                    if (password.equals(customer.getPassWord())) {
//                                        System.out.println("Đăng nhập thành công! Chào mừng khách hàng: " + username + " trở lại ");
//                                        currentUser = customer;
//                                        checkCustomer = true;
//                                        return;
//                                    }
//                                }
//                            }
//                            for (Staff staff : STAFF_LIST) {
//                                if (username.equals(staff.getUserName())) {
//                                    if (password.equals(staff.getPassWord())) {
//                                        System.out.println("Đăng nhập thành công! Chào mừng nhân viên: " + username + " trở lại");
//                                        currentUser = staff;
//                                        checkStaff = true;
//                                        return;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    throw new InvalidAccountException("Sai tài khoản / mật khẩu ");
//                } else return;
//            } catch (Exception e) {
//                System.err.println(e.getMessage());
//            }
//        } while (true);
//    }
//
//    @Override
//    public void signup() {
//        System.out.println("""
//                1. Bạn là nhân viên
//                2. Bạn là người dùng
//                3. Quay lại
//                """);
//        int choice = Input.choiceIntegerInput("Nhập lựa chọn: ");
//        do {
//            try {
//                User newUser;
//                switch (choice) {
//                    case 1:
//                        String staffName = Input.prompt("Nhập tài khoản: ", "USER_NAME");
//                        boolean checkExistStaff = false;
//                        for (User user : STAFF_LIST) {
//                            if (staffName.equals(user.getUserName())) {
//                                checkExistStaff = true;
//                                break;
//                            }
//                        }
//                        if (checkExistStaff) {
//                            throw new InvalidAccountException("Tài khoản này đã tồn tại");
//                        } else {
//                            String passWord = Input.prompt("Nhập mật khẩu (ít nhất @,viết hoa,thường,số,từ 8 chữ trở lên): ", "PASSWORD");
//                            String fullName = Input.promt("Nhập tên họ đầy đủ: ");
//                            String phoneNumber = Input.prompt("Nhập số điện thoại (+840xxxx hoặc 0xxxx,10 chữ số): ", "PHONE_NUMBER");
//                            String email = Input.prompt("Nhập Email: ", "EMAIL");
//                            String address = Input.promt("Nhập địa chỉ: ");
//                            UserFactory userFactory = UserFactory.getInstance();
//                            newUser = userFactory.getUser(staffName, fullName, phoneNumber, email, passWord, address, "staff");
//                            WriteFileUlti.writeFileUser("src\\com\\lamvt\\data\\staff.csv", newUser);
//                            STAFF_LIST.add((Staff) newUser);
//                            USER_LIST.add(newUser);
//                            System.out.println("Chúc mừng bạn đã tạo tài khoản thành công!");
//                        }
//                        return;
//                    case 2:
//                        String userName = Input.prompt("Nhập tài khoản: ", "USER_NAME");
//                        boolean checkExistUser = false;
//                        for (User user : CUSTOMER_LIST) {
//                            if (userName.equals(user.getUserName())) {
//                                checkExistUser = true;
//                                break;
//                            }
//                        }
//                        if (checkExistUser) {
//                            throw new InvalidAccountException("Tài khoản này đã tồn tại");
//                        } else {
//                            String passWord = Input.prompt("Nhập mật khẩu (ít nhất @,viết hoa,thường,số,từ 8 chữ trở lên): ", "PASSWORD");
//                            String fullName = Input.promt("Nhập tên họ đầy đủ: ");
//                            String phoneNumber = Input.prompt("Nhập số điện thoại (+840xxxx hoặc 0xxxx,10 chữ số): ", "PHONE_NUMBER");
//                            String email = Input.prompt("Nhập Email: ", "EMAIL");
//                            String address = Input.promt("Nhập địa chỉ: ");
//                            UserFactory userFactory = UserFactory.getInstance();
//                            newUser = userFactory.getUser(userName, fullName, phoneNumber, email, passWord, address, "customer");
//                            WriteFileUlti.writeFileUser("src\\com\\lamvt\\data\\customer.csv", newUser);
//                            CUSTOMER_LIST.add((Customer) newUser);
//                            USER_LIST.add(newUser);
//                            System.out.println("Chúc mừng bạn đã tạo tài khoản thành công!");
//                        }
//                        return;
//                    case 3:
//                        return;
//                    default:
//                        throw new InvalidChoiceException("Nhập đoàng hoàng cái coi!");
//                }
//            } catch (Exception e) {
//                System.err.println(e.getMessage());
//            }
//        } while (true);
//    }
//}
