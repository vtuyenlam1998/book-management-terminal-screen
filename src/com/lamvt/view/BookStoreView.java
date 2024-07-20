package com.lamvt.view;

import com.lamvt.entity.Customer;
import com.lamvt.entity.Staff;
import com.lamvt.service.impl.BookServiceImpl;
import com.lamvt.service.impl.CartServiceImpl;
import com.lamvt.service.impl.InvoiceServiceImpl;
import com.lamvt.service.impl.UserServiceImpl;
import com.lamvt.exception.InvalidChoiceException;
import com.lamvt.ulti.Input;
import com.lamvt.ulti.ReturnFunction;

public class BookStoreView {
    private static final BookStoreView bookStoreView = new BookStoreView();

    private BookStoreView() {
    }
    public static BookStoreView getInstance() {
        return bookStoreView;
    }
    UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
    BookServiceImpl bookServiceImpl = BookServiceImpl.getInstance();
    CartServiceImpl cartServiceImpl = CartServiceImpl.getInstance();
    InvoiceServiceImpl invoiceServiceImpl = InvoiceServiceImpl.getInstance();



    public void displayMainMenu() {
        do {
            try {
                System.out.print("""
                                     ஜ۩۞۩ஜ
                        【CHÀO MỪNG TỚI TIỆM SÁCH DÂM LÊ】
                        ① Đăng ký
                        ② Đăng nhập
                        ⓷ Tắt chương trình
                        """);
                int choice = Input.choiceIntegerInput("Enter your choice: ");
                switch (choice) {
                    case 1:
                        userServiceImpl.signup();
                        break;
                    case 2:
                        userServiceImpl.login();
                        if (userServiceImpl.getCurrentUser() instanceof Customer) {
                            displayUserInterface();
                            break;
                        } else if (userServiceImpl.getCurrentUser() instanceof Staff) {
                            displayStaffInterface();
                            break;
                        }
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        throw new InvalidChoiceException("Lựa chọn không hợp lệ");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (true);
    }

    public void displayStaffInterface() {
        do {
            try {
                System.out.println("""
                        Menu:
                            1. Hiển thị tất cả sách hiện có
                            2. Thêm/Sửa/Xóa sách trong cửa hàng
                            3. Xem thông tin cá nhân nhân viên
                            4. Xem thông tin khách hàng
                            5. Kiểm tra lịch sử bán sách trong tháng
                            6. Tính doanh thu/ lợi nhuận trong tháng
                            7. Log out
                            8. Đóng chương trình
                        """);
                int choice = Input.choiceIntegerInput("Nhấn phím tắt để thao tác ứng dụng: ");
                switch (choice) {
                    case 1:
                        bookServiceImpl.displayDetailOfBook();
                        break;
                    case 2:
                        bookServiceImpl.customizeBook();
                        break;
                    case 3:
                        System.out.println(userServiceImpl.getCurrentUser());
                        userServiceImpl.editAccountInformation();
                        break;
                    case 4:
                        userServiceImpl.getCustomerInfo();
                        break;
                    case 5:
                        invoiceServiceImpl.getSoldBookHistoryInMonth();
                        break;
                    case 6:
                        invoiceServiceImpl.getSaleAndProfitInMonth();
                        break;
                    case 7:
                        displayMainMenu();
                        break;
                    case 8:
                        System.exit(0);
                    default:
                        throw new InvalidChoiceException("Lựa chọn không hợp lệ!");
                }
            } catch (Exception e) {
                System.out.println("Xảy ra lỗi hệ thống/ Invalid input/ Format Unexpected " + e.getMessage());
            }
        } while (true);
    }

    public void displayUserInterface() {
        do {
            try {
                System.out.println("""
                        Menu:
                            1. Hiển thị tất cả sách hiện có
                            2. Tìm kiếm tủ sách theo thể loại
                            3. Sắp xếp tất cả sách tăng dần, giảm dần theo giá
                            4. Xem thông tin cá nhân người dùng
                            5. Hiển thị giỏ hàng
                            6. Kiểm tra lịch sử mua sách trong tháng
                            7. Log out
                            8. Đóng chương trình
                        """);
                int choice = Input.choiceIntegerInput("Nhấn phím tắt để thao tác ứng dụng: ");
                switch (choice) {
                    case 1:
                        bookServiceImpl.displayDetailOfBook();
                        break;
                    case 2:
                        String categoryBook = Input.promt("Nhập thể loại sách bạn muốn đọc: ");
                        bookServiceImpl.searchBookByType(categoryBook);
                        ReturnFunction.backPage();
                        break;
                    case 3:
                        bookServiceImpl.sortBookByPrice();
                        break;
                    case 4:
                        System.out.println(userServiceImpl.getCurrentUser());
                        userServiceImpl.editAccountInformation();
                        break;
                    case 5:
                        cartServiceImpl.checkCart();
                        break;
                    case 6:
                        invoiceServiceImpl.displayCustomerInvoiceHistory();
                        break;
                    case 7:
                        displayMainMenu();
                        break;
                    case 8:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Xảy ra lỗi hệ thống/ Invalid input/ Format Unexpected " + e.getMessage());
            }
        } while (true);
    }
}
