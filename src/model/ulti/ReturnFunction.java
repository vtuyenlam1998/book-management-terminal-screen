package model.ulti;

import model.Entity.User.Customer;
import model.Entity.User.Staff;
import model.Service.BookService;
import model.Service.UserService;
import model.exception.InvalidChoiceException;
import view.BookStoreView;

public class ReturnFunction {
    public static BookStoreView bookStoreView = BookStoreView.getInstance();
    public static BookService bookService = BookService.getInstance();
    public static UserService userService = UserService.getInstance();
    public static void backPage() {
        do {
            try {
                System.out.println("""
                                            
                        1. Mua sách online
                        2. Quay lại trang trước
                        3. Quay lại Main Menu
                        4. Log Out
                        5. Đóng chương trình
                                """);
                int backChoice = Input.choiceIntegerInput("Enter your choice: ");

                switch (backChoice) {
                    case 1:
                        bookService.selecBookToBuy();
                        break;
                    case 2:
                        return;
                    case 3:
                        if (userService.getCurrentUser() instanceof Customer) {
                            bookStoreView.displayUserInterface();
                        } else if (userService.getCurrentUser() instanceof Staff) {
                            bookStoreView.displayStaffInterface();
                        }
                    case 4:
                        bookStoreView.displayMainMenu();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        throw new InvalidChoiceException("Lựa chọn không phù hợp");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
