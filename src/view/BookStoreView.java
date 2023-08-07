package view;

import model.Entity.User.Customer;
import model.Entity.User.Staff;
import model.Service.BookService;
import model.Service.CartService;
import model.Service.InvoiceService;
import model.Service.UserService;
import model.exception.InvalidChoiceException;
import model.ulti.Input;
import model.ulti.ReturnFunction;

public class BookStoreView {
    private static final BookStoreView bookStoreView = new BookStoreView();

    private BookStoreView() {
    }
    public static BookStoreView getInstance() {
        return bookStoreView;
    }
    UserService userService = UserService.getInstance();
    BookService bookService = BookService.getInstance();
    CartService cartService = CartService.getInstance();
    InvoiceService invoiceService = InvoiceService.getInstance();



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
                        userService.signUp();
                        break;
                    case 2:
                        userService.logIn();
                        if (userService.getCurrentUser() instanceof Customer) {
                            displayUserInterface();
                            break;
                        } else if (userService.getCurrentUser() instanceof Staff) {
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
                        bookService.displayDetailOfBook();
                        break;
                    case 2:
                        bookService.customizeBook();
                        break;
                    case 3:
                        System.out.println(userService.getCurrentUser());
                        userService.editAccountInformation();
                        break;
                    case 4:
                        userService.getCustomerInfo();
                        break;
                    case 5:
                        invoiceService.getSoldBookHistoryInMonth();
                        break;
                    case 6:
                        invoiceService.getSaleAndProfitInMonth();
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
                        bookService.displayDetailOfBook();
                        break;
                    case 2:
                        String categoryBook = Input.promt("Nhập thể loại sách bạn muốn đọc: ");
                        bookService.searchBookByType(categoryBook);
                        ReturnFunction.backPage();
                        break;
                    case 3:
                        bookService.sortBookByPrice();
                        break;
                    case 4:
                        System.out.println(userService.getCurrentUser());
                        userService.editAccountInformation();
                        break;
                    case 5:
                        cartService.checkCart();
                        break;
                    case 6:
                        invoiceService.displayCustomerInvoiceHistory();
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
